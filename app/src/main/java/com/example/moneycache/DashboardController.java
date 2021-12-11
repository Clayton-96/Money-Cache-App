package com.example.moneycache;

import android.widget.TextView;

import java.util.List;

public class DashboardController {
    private final DashboardActivity dbActivity;
    private final DataModel model;

    //variables used to get income & goal amounts for alerts
    Float billsAmt;
    Float discretionaryAmt;
    Float debtReductionAmt;
    Float savingsAmt;

    //spent amounts for each category. Given values in alert[Category](). Used for pie chart.
    float spentB = 0.0f;
    float spentD = 0.0f;
    float spentDR = 0.0f;
    float spentS = 0.0f;

    public float getTotalSpent() {
        return spentB + spentD + spentDR + spentS;
    }

    /**
     * Logic for pie chart on Dashboard
     * takes total actual amount spent as base
     * divided each category total against the combined total for each piece
     */

    public float pieChartBills () {
        float totalAmtSpent = spentB + spentD + spentDR + spentS;
        if (totalAmtSpent != 0){
        return spentB/totalAmtSpent * 100;
        } else return 0;
    }
    public float pieChartDiscretionary () {
        float totalAmtSpent = spentB + spentD + spentDR + spentS;
        if (totalAmtSpent != 0){
            return spentD/totalAmtSpent * 100;
        } else return 0;
    }
    public float pieChartReduction () {
        float totalAmtSpent = spentB + spentD + spentDR + spentS;
        if (totalAmtSpent != 0){
        return spentDR/totalAmtSpent * 100;
        } else return 0;
    }
    public float pieChartSavings () {
        float totalAmtSpent = spentB + spentD + spentDR + spentS;
        if (totalAmtSpent != 0){
        return spentS/totalAmtSpent * 100;
        } else return 0;
    }
    /**
     * Logic for alerts on the Dashboard
     * is green = true, is red = false
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

        model.loadData(dbActivity);
        //items = DataModel.getItems();
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
        spentB = Math.abs(model.getBills());
        boolean green;
        billsAmt = goal - spentB;
        if (spentB <= goal) billsAmtGreen = true;
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
        spentD = Math.abs(model.getDiscretionary());
        boolean green;
        discretionaryAmt = goal - spentD;
        if (spentD < goal) discretionaryAmtGreen = true;
    }
    public void alertDebtReduction() {
        float goal = model.getDebtReductionGoal();
        spentDR = Math.abs(model.getDebt_reduction());
        boolean green;
        debtReductionAmt = goal - spentDR;
        if (spentDR >= goal) debtReductionAmtGreen = true;
    }
    public void alertSavings() {
        float goal = model.getSavingsGoal();
        spentS = Math.abs(model.getSavings());
        boolean green;
        savingsAmt = goal - spentS;
        if (spentS >= goal) savingsAmtGreen = true;
    }

}
