package com.example.moneycache;

import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataModel {
    static final String dataFile = "app/src/main/java/com/example/moneycache/bankData.txt";

    /**
     * get bank data either from DB or from bankData.txt
     * and put into object BankData (and budgetCategories?)
     * called from LoginController after login is complete
     * @param dataFile is bankData.txt--eventually should be data from DB
     */
    //TODO: should this be on a thread? or would it be a thread from calling LoginController?
    public static void toBankDataObjects (String dataFile) {
        Gson gson = new Gson();
        BankData appData = gson.fromJson(dataFile, BankData.class);

    }

    /**
     * Gets the Float values of the User's EditBudget categories from DB
     * and returns an ArrayList to EditBudgetController start().
     * @return 'items' as Array
     */
    public static List<Float> getBudgetItems() {
        //temporary data for getBudgetItems
        List<Float> items = new ArrayList<>(Arrays.asList(4085.00f, 2795.34f, 800.00f, 200.00f, 200.00f));

        //TODO: get values from DB and return the Float values of the User's EditBudget categories
        // to EditBudgetController
        return items;
    }
}
