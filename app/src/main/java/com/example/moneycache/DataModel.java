package com.example.moneycache;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
//import com.google.firebase.firestore;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataModel {
    private static final String JSON_FILE = "app/src/main/java/com/example/moneycache/bankdata.txt";
    String FILENAME = "csv_file.txt";
    static final String dataFile = "app/src/main/res/raw/bankdata.txt";
    //app/src/main/res/raw/bankdata.txt
    public static BankData appData;
    public static List<String> items;//category goals
    private String categoryFile = "app/src/main/res/raw/discretionary.txt";
    private String dataString;

    //these fields are for the (need to be) saved current category totals derived from EditDataActivity.
    Float income;
    Float bills;
    Float discretionary;
    Float debt_reduction;
    Float savings;

    //Getters and setters for the above fields to be called from other classes. (May not need all of them)
    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Float getBills() {
        return bills;
    }

    public void setBills(Float bills) {
        this.bills = bills;
    }

    public Float getDiscretionary() {
        return discretionary;
    }

    public void setDiscretionary(Float discretionary) {
        this.discretionary = discretionary;
    }

    public Float getDebt_reduction() {
        return debt_reduction;
    }

    public void setDebt_reduction(Float debt_reduction) {
        this.debt_reduction = debt_reduction;
    }

    public Float getSavings() {
        return savings;
    }

    public void setSavings(Float savings) {
        this.savings = savings;
    }

    /**
     * This data will eventually TODO: be called from Shared Preferences.
     * Right now it is hard-coded.
     * Data comes from the edited bank transactions.This is the 'spent' amount.
     * One of the first things that needs to happen when the app starts.
     * @param context of the activity calling for the data.
     */
    public void loadData(Context context){
        getCategoryAmounts(context);//category totals saved in SP
        getBudgetItems(context);//category GOALS saved in SP
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.bankdata)));
            StringBuilder sb = new StringBuilder();
            boolean done = false;
            do {
                String text = reader.readLine();
                if (text != null) {
                    sb.append(text);
                    sb.append("\n");
                } else {
                    done = true;
                }
            } while (!done);
            dataString = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences sp = context.getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("bankData", dataString);
        editor.commit();

//        try {
//            //read json file bankData.txt (dataFile) into sharedPref
//            File file = new File(getResources().openRawResource(R.raw.bankdata));
//            String data = FileUtils.readFileToString(file, Charset.defaultCharset());
//            SharedPreferences sp = context.getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("bankData", data);
//            editor.commit();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * get bank data from user CSV file, saved in Downloads folder, named 'csv_file.txt'
     * it is changed into object BankData (and budgetCategories?)
     * brings new bank data into app
     * button in EditData Activity 'Upload Bank Data' starts process
     * TODO: ideally called from LoginController after login is complete
     * author: Dixie Cravens
     */
    public void userFileToString(Context context) {
        //StringBuilder newCSV = null;
        String result = null;
        //public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";
        //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File path = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path,FILENAME);
        System.out.println(file);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            result = reader.readLine();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try{
//            File folder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//            File myFile = new File(folder, FILENAME);
//            FileInputStream fstream = new FileInputStream(myFile);
//            newCSV = new StringBuilder();
//            int i;
//            while ((i = fstream.read())!= -1){
//                newCSV.append((char)i);
//            }
//            fstream.close();
//        }  catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        assert result != null;
        String csv_file = result.toString();

        //run CsvReader to change CSV to JSON
        CsvReader bankFile = new CsvReader();
        bankFile.readCSVFile(csv_file);//---this needs to open a fragment in the EditDataActivity
        bankFile.writeToJson(JSON_FILE);//this writes to the internal file that BankData.java reads
                                        // from to create the recyclerView

//        // use GSON to change JSON to BankData objects--so far nothing in app is using appData.
//        Gson gson = new Gson();
//        appData = gson.fromJson(dataFile, BankData.class);

    }

    /**
     * Gets the String values of the User's EditBudget GOAL categories from SharedPreferences
     * and returns an ArrayList 'items' to EditBudgetController start(), and DashboardController start().
     * Listed in order of: [0]Income, [1]Bills, [2]Discretionary, [3]Debt reduction, and [4]Savings.
     * @return 'items' as Array
     * @param context is the activity calling the method
     * author: Dixie Cravens
     */
    public static List<String> getBudgetItems(Context context) {

        String income;
        String bills;
        String discretionary;
        String debtReduction;
        String savings;

        //get data from SharedPreferences--have to have the "else 0" because the first time there is no data to get
        SharedPreferences sp = context.getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        if (sp.contains("goal_income")) {
            income = sp.getString("goal_income", "");
        }else {
            income = "0";
        }
        if (sp.contains("goal_bills")) {
            bills = sp.getString("goal_bills", "");
        } else {
            bills = "0";
        }
        if (sp.contains("goal_discretionary")) {
            discretionary = sp.getString("goal_discretionary", "");
        }else {
            discretionary = "0";
        }
        if (sp.contains("goal_debtreduction")) {
            debtReduction = sp.getString("goal_debtreduction", "");
        } else {
            debtReduction = "0";
        }
        if (sp.contains("goal_savings")) {
            savings = sp.getString("goal_savings", "");
        } else {
            savings = "0";
        }

        items = new ArrayList<String>(Arrays.asList(income, bills, discretionary, debtReduction, savings));
        //items = new ArrayList<>(Arrays.asList("4085.00", "2795.34", "800.00", "200.00", "200.00"));

        return items;
    }

    /**
     * updates to sharedPreferences as category GOAL values.
     * Data comes from user in EditDataActivity.
     * author: Dixie Cravens
     */
    public static void updateBudgetItems(Context context) {
        //save goals by category in SharedPreferences
        SharedPreferences sp = context.getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("goal_income", String.valueOf(items.get(0)));
        editor.putString("goal_bills", String.valueOf(items.get(1)));
        editor.putString("goal_discretionary", String.valueOf(items.get(2)));
        editor.putString("goal_debtreduction", String.valueOf(items.get(3)));
        editor.putString("goal_savings", String.valueOf(items.get(4)));
        editor.commit();


    }

    /**
     * comes from SharedPreferences (firebase/firestore) for month selected
     * (firestore)DocumentReference document = db.collection("users").document("dcravens").collection("transactByCategory").where("monthYear", "==", "December2021");
     * (firestore)Brings back entire document of current spending totals per category
     *TODO: Save this in sharedPreferences with Month-Year and category. Amount is added as
     * transactions are edited and categorized.
     */
    public void getCategoryAmounts(Context context){
        SharedPreferences sp = context.getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        if (sp.contains("income_total")) {
            income = Float.parseFloat(sp.getString("income_total", ""));
        } else {
            income = 0f;
        }
        //income = 2400f;//sp.getString( "income_total", "");
        bills = 950f;//document.bills;
        discretionary = 256.66f;//document.discretionary;
        debt_reduction = 200f;//document.debtReduction;
        savings = 150f;//document.savings;
    }


    /**
     *  get individual category TOTALS from sharedPreferences and update them
     *      * determines which KEY to use by the @param category
     *      * uses key to access correct category total saved in ShredPreferences
     *      * gets total, adds @param addAmount to it
     *      * saves new total back into SharedPreferences
     * @param category category assigned by EditData
     * @param addAmount amount of transaction
     */
    public void updateCategoryTotals(String category, Float addAmount, Context context) {
        //String key = "";
        SharedPreferences sp = context.getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //TODO: account for negative and positive amounts with Math.abs(variable)??
        switch (category) {
            case ("Income"):
                String key = "income_total";
                if (sp.contains(key)) {
                    String totalString = sp.getString(key, "");
                    income = Float.parseFloat(totalString) + addAmount;
                } else {
                    income = addAmount;
                }
                editor.putString(key, String.valueOf(income));
                editor.apply();
                break;
            case ("Bills"):
                key = "bills_total";
                if (sp.contains(key)) {
                    String totalString = sp.getString(key, "");
                    bills = Float.parseFloat(totalString) + addAmount;

                } else {
                    bills = addAmount;
                }
                editor.putString(key, String.valueOf(bills));
                editor.apply();
                break;
            case ("Discretionary"):
                key = "discretionary_total";
                if (sp.contains(key)) {
                    String totalString = sp.getString(key, "");
                    discretionary = Float.parseFloat(totalString) + addAmount;

                } else {
                    discretionary = addAmount;
                }
                editor.putString(key, String.valueOf(discretionary));
                editor.apply();
                break;
            case ("Debt Reduction"):
                key = "debtreduction_total";
                if (sp.contains(key)) {
                    String totalString = sp.getString(key, "");
                    debt_reduction = Float.parseFloat(totalString) + addAmount;

                } else {
                    debt_reduction = addAmount;
                }
                editor.putString(key, String.valueOf(debt_reduction));
                editor.apply();
                break;
            case ("Savings"):
                key = "savings_total";
                if (sp.contains(key)) {
                    String totalString = sp.getString(key, "");
                    savings = Float.parseFloat(totalString) + addAmount;

                } else {
                    savings = addAmount;
                }
                editor.putString(key, String.valueOf(savings));
                editor.apply();
                break;
        }

    }


    /**
     * Gets values from sharedPreferences for budget GOAL categories
     * @return Float value of budget category
     */
    //First call the document "budget" from db. contains all budget fields and amounts.
    //DocumentReference document = db.collection("users").document("dcravens").collection("budget");
    // then return and/or populate the budget category fields
    // these fields are also used to compare goals with actual spending

    public Float getIncomeGoal() { return Float.parseFloat(items.get(0)); }

    public Float getBillsGoal() { return Float.parseFloat(items.get(1)); }

    public Float getDiscretionaryGoal() {
        return Float.parseFloat(items.get(2));
    }

    public Float getDebtReductionGoal() {
        return Float.parseFloat(items.get(3));
    }

    public Float getSavingsGoal() {
        return Float.parseFloat(items.get(4));
    }

    public static List<String> getItems() { return items; }

}
// to call the budget category (goal) for "bills". This returns 1245 as a number (int? float?)
//DocumentReference document = db.collection("users").document("dcravens").collection("budget").orderBy("bills", "asc");
// to get to the actual amount for a particular month: (this returns the 'bills' for November and December)
//DocumentReference document = db.collection("users").document("dcravens").collection("transactByCategory").orderBy("bills", "asc");


//https://stackoverflow.com/questions/8854359/exception-open-failed-eacces-permission-denied-on-android#:~:text=In%20addition%20to%20all%20answers,if%20you%20have%20given%20any.