package com.example.moneycache;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataModel {

    /**
     * Gets the Float values of the User's EditBudget categories from DB
     * and returns an ArrayList to EditBudgetController getBudgetItemData().
     * @return 'items' as Array
     */
    public static List<Float> getBudgetItems() {
        //temporary data for getBudgetItems
        List<Float> items = new ArrayList<>(Arrays.asList(4085.00f, 2795.34f, 800.00f, 200.00f, 200.00f));

        //gets values from DB and returns the Float values of the User's EditBudget categories
        // to EditBudgetController
        return items;
    }
}
