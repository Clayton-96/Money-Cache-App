package com.example.moneycache;

public class ImpactController {
    DataModel model = new DataModel();
    private ImpactActivity impactActivity = null;
    private Float ImpactAmount;
    private String ImpactFrequency;
    private boolean green;

    public ImpactController(ImpactActivity impactActivity){
        this.impactActivity = impactActivity;
        model.getBudgetItems();//this adds data to 'items' array for use in the Impact controller

    }
    public boolean isGreen() {
        return green;
    }

    /**
     * Called by displayBudgetImpact() from Impact Activity
     * Calculates an impactResult by adding the impact amount from user with all expenses and
     * subtracting it from income.
     * If it is positive (>= 0), green is set to true (as in the text box will be green).
     * If negative, green is set to false.
     * @return the impactResult--amount of the difference (positive or negative) as a Float
     * author: Dixie Cravens
     */
    public Float budgetImpact() {
        ImpactAmount = impactActivity.getImpactAmount();
        ImpactFrequency = impactActivity.getImpactFrequency();//not using this to do any calculations at the moment
        Float income = model.getIncomeGoal();
        Float totalBills = model.getBillsGoal() + model.getDiscretionaryGoal() + model.getDebtReductionGoal() + model.getSavingsGoal();
        Float impactResult = (income - (totalBills + ImpactAmount));
        if  (impactResult >= 0) {
            green = true;
        } else {
            green = false;
        }
        return impactResult;

    }

    /**
     * called by displaySavingsImpact() from ImpactActivity
     * Calculates the impact by subtracting the absolute value of the budgetImpact() from the savings category.
     * If negative, green is set to false and impact is calculated.
     * If it is positive, green is set to true, and impact is set to 0.
     * @return impact--amount of negative impact on savings, or 0 if no impact.
     * author: Dixie Cravens
     */
    public Float savingsImpact() {
        Float savingsTotal = model.getSavingsGoal();
        Float impact = null;
        if (green != true) {
            impact = savingsTotal - Math.abs(budgetImpact());
        } else {
            impact = 0f;
        }
        return impact;


    }

}


