package com.example.moneycache;

public class ImpactController {
    DataModel model = new DataModel();
    private ImpactActivity impactActivity = null;
    private Float ImpactAmount;
    private String ImpactFrequency;
    private boolean green;

    public ImpactController(ImpactActivity impactActivity){
        this.impactActivity = impactActivity;
        model.getBudgetItems();

    }
    public boolean isGreen() {
        return green;
    }


    public Float budgetImpact() {
        ImpactAmount = impactActivity.getImpactAmount();
        ImpactFrequency = impactActivity.getImpactFrequency();//not using this to do any calculations at the moment
        Float income = model.getIncomeAmount();
        Float totalBills = model.getBillsAmount() + model.getDiscretionaryAmount() + model.getDebtReductionAmount() + model.getSavingsAmount();
        Float impactResult = (income - (totalBills + ImpactAmount));
        if  (impactResult >= 0) {
            green = true;
        } else {
            green = false;
        }
        return impactResult;

    }

    public Float savingsImpact() {
        Float savingsTotal = model.getSavingsAmount();
        Float impact = null;
        if (green != true) {

            //get a savings total? or subtract impactResult from monthly savings amounts?
            // "This amount will reduce this month's savings goals by impactResult"
            impact = savingsTotal - Math.abs(budgetImpact());
        } else {
            impact = 0f;
        }
        return impact;


    }

}


