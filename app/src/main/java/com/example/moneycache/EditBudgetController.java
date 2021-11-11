package com.example.moneycache;

import java.util.List;

public class EditBudgetController {
    EditBudgetActivity budgetActivity = new EditBudgetActivity();

    private Float income;// should it be set to 0 rather than null?
    private Float bills;
    private Float discretionary;
    private Float debtReduction;
    private Float savings;

    //display budget item (BI)boxes with correct text
    //  ask model for correct data for each BI box--what format is this coming from the DB in?
    //  Maybe filling variables with stored values from DB? Then changing that?
    private void getBudgetItemData() {
        List<Float> items = DataModel.getBudgetItems();
        income = items.get(0);
        bills = items.get(1);
        discretionary = items.get(2);
        debtReduction = items.get(3);
        savings = items.get(4);
    }

    // edit data in BI  amount box
    //   create ONE field box for editing with appropriate text (Not allowing editing of category name)

    // send/update model of all changes
    //  Do we update all Budget Categories? or do we update only what has changed?
    //  How is this stored in DB?

}
