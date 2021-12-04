package com.example.moneycache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
//import com.google.firebase.firestore;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataModel {
    static final String dataFile = "app/src/main/java/com/example/moneycache/bankdata.txt";
    public static BankData appData;
    public static List<String> items;
    private String categoryFile = "app/src/main/res/raw/discretionary.txt";

    Float income;
    Float bills;
    Float discretionary;
    Float debt_reduction;
    Float savings;

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

    public void loadData(Context context) {
        getCategoryAmounts();
    }

    /**
     * get bank data from bankdata.txt(started as a CSV file, changed to JSON)
     * and put into object BankData (and budgetCategories?)
     * called from LoginController after login is complete
     * @param dataFile is bankdata.txt--eventually should be data from DB
     */
    //TODO: should this be on a thread? or would it be a thread from calling LoginController?
    public static void toBankDataObjects (String dataFile) {
        Gson gson = new Gson();
        appData = gson.fromJson(dataFile, BankData.class);

    }

    /**
     * Gets the String values of the User's EditBudget GOAL categories from SharedPreferences
     * and returns an ArrayList to EditBudgetController start(), and DashboardController start().
     * Listed in order of: [0]Income, [1]Bills, [2]Discretionary, [3]Debt reduction, and [4]Savings.
     * @return 'items' as Array
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
        //save goals in SharedPreferences
        SharedPreferences sp = context.getSharedPreferences("MoneyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("goal_income", String.valueOf(items.get(0)));
        editor.putString("goal_bills", String.valueOf(items.get(1)));
        editor.putString("goal_discretionary", String.valueOf(items.get(2)));
        editor.putString("goal_debtreduction", String.valueOf(items.get(3)));
        editor.putString("goal_savings", String.valueOf(items.get(4)));
        editor.commit();



//        mDatabase.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });

    }

    /**
     * comes from firebase for month selected
     * DocumentReference document = db.collection("users").document("dcravens").collection("transactByCategory").where("monthYear", "==", "December2021");
     * Brings back entire document of current spending totals per category
     *TODO: Track this in sharedPreferences with Month-Year and category. Amount is added as
     * transactions are edited and categorized
     */
    public void getCategoryAmounts(){
        //DocumentReference document = db.collection("users").document("dcravens").collection("transactByCategory").where("monthYear", "==", "December2021");
        income = 2400f;//document.income;
        bills = 950f;//document.bills;
        discretionary = 256.66f;//document.discretionary;
        debt_reduction = 200f;//document.debtReduction;
        savings = 150f;//document.savings;

    }

    /**
     * Gets values from sharedPreferences for budget GOAL categories
     * @return Float value of budget category
     * **right now it is just hardcoded in items
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