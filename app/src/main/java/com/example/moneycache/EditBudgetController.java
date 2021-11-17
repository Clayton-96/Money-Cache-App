package com.example.moneycache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditBudgetController {

    private final EditBudgetActivity budgetActivity;
    private final DataModel model;

    private String income;// should it be set to 0 rather than null?
    private String bills;
    private String discretionary;
    private String debtReduction;
    private String savings;

    public EditBudgetController(EditBudgetActivity budgetActivity){
        this.budgetActivity = budgetActivity;
        model = new DataModel();
    }
    public void start() {
        //display budget item (BI) boxes with correct text
        //  ask model for correct data for each BI box--what format is this coming from the DB in?
        //  Fill variables with stored values from DB.
        //https://github.com/macbeth-byui/MVP_Android/blob/master/app/src/main/java/t/macbeth/mvp_android/MVP1/Presenter.java
        List<String> items = DataModel.getBudgetItems();
        income = items.get(0);
        bills = items.get(1);
        discretionary = items.get(2);
        debtReduction = items.get(3);
        savings = items.get(4);
    }

    public String getIncome() {
        return income;
    }
    public void setIncome(String income) {
        this.income = income;
    }
    public String getBills() {
        return bills;
    }
    public void setBills(String bills) {
        this.bills = bills;
    }
    public String getDiscretionary() {
        return discretionary;
    }

    public void setDiscretionary(String discretionary) {
        this.discretionary = discretionary;
    }

    public String getDebtReduction() {
        return debtReduction;
    }

    public void setDebtReduction(String debtReduction) {
        this.debtReduction = debtReduction;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }


    // send/update model of all changes
    /**
     * update/set new category value/values
     * categories are edited in a fragment with a final update when any/all categories are edited
     */
    public void onUpdate() {
        //create a new string for List<String> items and save it to DataModel
        // TODO: does this need to be 'new' ArrayList since it is replacing what currently exists?
        DataModel.items = new ArrayList<>(Arrays.asList(getIncome(),
                getBills(), getDiscretionary(), getDebtReduction(), getSavings()));
        DataModel.updateBudgetItems();

    }

}
