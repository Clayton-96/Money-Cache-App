package com.example.moneycache;

import android.widget.TextView;

import java.util.List;

public class DashboardController {
    private final DashboardActivity dbActivity;
    private final DataModel model;

    Float billsAmt;
    Float discretionaryAmt;
    Float debtReductionAmt;
    Float savingsAmt;
    boolean billsAmtGreen;
    boolean discretionarAmtGreen;
    boolean debtReductionAmtGreen;
    boolean savingsAmtGreen;

    private List<String> items;


    public DashboardController(DashboardActivity dbActivity) {
        this.dbActivity = dbActivity;
        model = new DataModel();
    }

    public void start() {
        //DataModel model = new DataModel();
        model.loadData(dbActivity);
        items = DataModel.getItems();
        alertBills();
        alertDiscretionary();
        alertDebtReduction();
        alertSavings();

    }
    public List<String> getItems() {
        return items;
    }

    public void alertBills() {
        float goal = model.getBillsGoal();
        float spent = model.getBills();
        boolean green;
        billsAmt = goal - spent;
        if (spent < goal) billsAmtGreen = true;
    }
    public void alertDiscretionary() {
        float goal = model.getDiscretionaryGoal();
        float spent = model.getDiscretionary();
        boolean green;
        discretionaryAmt = goal - spent;
        if (spent < goal) discretionarAmtGreen = true;
    }
    public void alertDebtReduction() {
        float goal = model.getDebtReductionGoal();
        float spent = model.getDebt_reduction();
        boolean green;
        debtReductionAmt = goal - spent;
        if (spent < goal) debtReductionAmtGreen = true;
    }
    public void alertSavings() {
        float goal = model.getSavingsGoal();
        float spent = model.getSavings();
        boolean green;
        savingsAmt = goal - spent;
        if (spent < goal) savingsAmtGreen = true;
    }

}
