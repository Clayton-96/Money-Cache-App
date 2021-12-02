package com.example.moneycache;

import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataModel {
    static final String dataFile = "app/src/main/java/com/example/moneycache/bankdata.txt";
    public static BankData appData;
    public static List<String> items;
    private String categoryFile = "app/src/main/res/raw/discretionary.txt";

    /**
     * get bank data either from DB or from bankdata.txt
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
     * Gets the String values of the User's EditBudget categories from DB
     * and returns an ArrayList to EditBudgetController start().
     * String format works best since this number doesn't need to be computed.
     * Listed in order of: [0]Income, [1]Bills, [2]Discretionary, [3]Debt reduction, and [4]Savings.
     * @return 'items' as Array
     * author: Dixie Cravens and....
     */
    public static List<String> getBudgetItems() {
        //temporary data for getBudgetItems--
        // when this updates from EditBudgetController.onUpdate()...what happens to this hardcoded data?
        items = new ArrayList<>(Arrays.asList("4085.00", "2795.34", "800.00", "200.00", "200.00"));

        //TODO: get values from DB and return the String values of the User's EditBudget categories
        // to EditBudgetController
        return items;
    }

    /**
     * updates to storage a List<String> of category values. Stored in order of:
     * [0]Income, [1]Bills, [2]Discretionary, [3]Debt reduction, and [4]Savings.
     * author: Dixie Cravens and....
     */
    public static void updateBudgetItems() {
        //TODO: save 'items' to db or shared pref
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
     * comes from firebase eventually
     * @param categoryFile
     */
    public static void getIncomeAmount(String categoryFile){
        Float amount = 2400f;

    }

    /**
     * Gets values from firebase
     * @return Float value of budget category
     * **right now it is just hardcoded in items
     */
    public Float getIncomeAmount() {
        return Float.parseFloat(items.get(0));
    }

    public Float getBillsAmount() {
        return Float.parseFloat(items.get(1));
    }

    public Float getDiscretionaryAmount() {
        return Float.parseFloat(items.get(2));
    }

    public Float getDebtReductionAmount() {
        return Float.parseFloat(items.get(3));
    }

    public Float getSavingsAmount() {
        return Float.parseFloat(items.get(4));
    }

    public static List<String> getItems() {
        return items;
    }

}
