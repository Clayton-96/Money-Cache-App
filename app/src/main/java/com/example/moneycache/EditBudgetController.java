package com.example.moneycache;

import java.util.List;

public class EditBudgetController {
    private final EditBudgetActivity budgetActivity;
    private final DataModel model;

    private Float income;// should it be set to 0 rather than null?
    private Float bills;
    private Float discretionary;
    private Float debtReduction;
    private Float savings;

    public EditBudgetController(EditBudgetActivity budgetActivity){
        this.budgetActivity = budgetActivity;
        model = new DataModel();
    }
    public void start() {
        //display budget item (BI) boxes with correct text
        //  ask model for correct data for each BI box--what format is this coming from the DB in?
        //  Fill variables with stored values from DB.
        //https://github.com/macbeth-byui/MVP_Android/blob/master/app/src/main/java/t/macbeth/mvp_android/MVP1/Presenter.java
        List<Float> items = model.getBudgetItems();
        income = items.get(0);
        bills = items.get(1);
        discretionary = items.get(2);
        debtReduction = items.get(3);
        savings = items.get(4);
        // pass data to budget item (BI)boxes in the view
    }

    // edit data in BI  amount box
    //   create ONE field box for editing with appropriate text (Not allowing editing of category name)
    //should this be some sort of onClick?
    public void editBudgetAmount(/*budget category being edited*/) {
        //create fragment in EditBudgetActivity
        //pass that data to fragment upon start
    }
    // send/update model of all changes
    //  Do we update all Budget Categories? or do we update only what has changed?
    //  How is this stored in DB?

}
