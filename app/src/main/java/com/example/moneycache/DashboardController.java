package com.example.moneycache;

import android.widget.TextView;

import java.util.List;

public class DashboardController {
    private final DashboardActivity dbActivity;
    private final DataModel model;

    //variables used to get income & goal amounts
    Float billsAmt; //different than master
    Float discretionaryAmt;
    Float debtReductionAmt;
    Float savingsAmt;

    public Float getIncome() {
        return income;
    }
    Float income;

    /**
     * Logic for pie chart on Dashboard //if it wants a decimal take out the * 100
     */
    public float pieChartIncome () {
        return getIncome();
    }
    public float pieChartBills () {
        return (billsAmt/income) * 100;
    }
    public float pieChartDiscretionary () {
        return (discretionaryAmt/income) * 100;
    }
    public float pieChartReduction () {
        return (debtReductionAmt/income) * 100;
    }
    public float pieChartSavings () {
        return (savingsAmt/income) * 100;
    }
    /**
     * Logic for alerts on the Dashboard
     */
    boolean billsAmtGreen;
    boolean discretionaryAmtGreen;
    boolean debtReductionAmtGreen;
    boolean savingsAmtGreen;

    private List<String> items;


    public DashboardController(DashboardActivity dbActivity) {
        this.dbActivity = dbActivity;
        model = new DataModel();
    }

    /**
     * gets all of the data needed to run the activity
     */
    public void start() {
        //DataModel model = new DataModel();

        model.loadData(dbActivity);
        items = DataModel.getItems();
        income = model.getIncomeGoal();
        alertBills();
        alertDiscretionary();
        alertDebtReduction();
        alertSavings();

    }
    public List<String> getItems() {
        return items;
    }

    /**
     * retrieves budget goal for BILLS category
     * retrieves actual spending for BILLS category
     * subtracts actual spending from goal. if amount is <= 0, set color to green.
     * If spending is greater than goal, set color to red
     * Displays the result of the calculation.
     * Author: Dixie Cravens
     */
    public void alertBills() {
        float goal = model.getBillsGoal();
        float spent = model.getBills();
        boolean green;
        billsAmt = goal - spent;
        if (spent <= goal) billsAmtGreen = true;
    }
    /**
     * retrieves budget goal for DISCRETIONARY category
     * retrieves actual spending for DISCRETIONARY category
     * subtracts actual spending from goal. if amount is < 0, set color to green.
     * If spending is greater than goal or 0, set color to red.
     * Displays the result of the calculation.
     * Author: Dixie Cravens
     */
    public void alertDiscretionary() {
        float goal = model.getDiscretionaryGoal();
        float spent = model.getDiscretionary();
        boolean green;
        discretionaryAmt = goal - spent;
        if (spent < goal) discretionaryAmtGreen = true;
    }
    public void alertDebtReduction() {
        float goal = model.getDebtReductionGoal();
        float spent = model.getDebt_reduction();
        boolean green;
        debtReductionAmt = goal - spent;
        if (spent >= goal) debtReductionAmtGreen = true;
    }
    public void alertSavings() {
        float goal = model.getSavingsGoal();
        float spent = model.getSavings();
        boolean green;
        savingsAmt = goal - spent;
        if (spent >= goal) savingsAmtGreen = true;
    }

}
