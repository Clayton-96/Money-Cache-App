package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class ImpactActivity extends AppCompatActivity {


    private TextView spend, save;
    private EditText amountTv;
    private ImpactController impactController;
    private Float ImpactAmount;
    private String ImpactFrequency;

    public Float getImpactAmount() {
        return ImpactAmount;
    }

    public String getImpactFrequency() {
        return ImpactFrequency;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impact);
        setTitle("Spending and Savings Impact");

        impactController = new ImpactController(this);
        amountTv = findViewById(R.id.dollar_amount_what_if);
        spend = findViewById(R.id.spending_impact_display);
        save = findViewById(R.id.savings_impact_display);

    }

    /**
     * reads the impact amount user enters in view
     * @return amount as a Float
     */
    public Float getUserAmount() {
        //create a method that reads the value from R.id.dollar_amount_what_if and assigns it to a variable
        Float amount = (Float.parseFloat(amountTv.getText().toString()));

        Log.d("get user amount", "getUserAmount: " + amount);

        return amount;
   }

    /**
     * could be a boolean...string that allows for forecasting if reoccurring is enabled and selected
     * @param view of radio button group
     * @return one-time or reoccurring
     */
    public String radioButtonSelection(View view) {
        String string = "";
        //could also return a boolean
        //need an if/else statement for the two options
        return "one-time";

    }

    /**
     * When Update button is clicked
     * @param view is Update button
     * gets Impact amount user entered, along with chosen frequency
     * calls the 2 display functions for Budget Impact and Savings Impact
     * author: Dixie Cravens
     */
    public void updateImpactClick(View view) {
        //call method to get amount
        ImpactAmount = getUserAmount();
        //call method to get frequency
        ImpactFrequency = radioButtonSelection(view);
        //call display methods
        displayBudgetImpact();
        displaySavingsImpact();
    }

    /**
     * called from updateImpactClick()
     * sets the text inside box to reflect the impact amount (zero if no impact)
     * sets the background to green if impact is zero and red if savings is impacted at all (along with amount)
     * author: Dixie Cravens
     */
    public void displaySavingsImpact() {
        String impactText = String.format("%.02f",impactController.savingsImpact());

        if (impactController.isGreen()) {
            //set color of box to green...set this green color in the values/themes
            save.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

        } else {
            //set color of box to red
            save.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        //set value of impact to the TextView for savings impact
        save.setText(impactText);

    }
    /**
     * called from updateImpactClick()
     * sets the text inside box to reflect the impact amount
     * sets the background to green if impact is within budget and red if not and therefore affects savings
     * author: Dixie Cravens
     */
    public void displayBudgetImpact() {
        //get impact value from the controller
        String impactText = String.format("%.02f",impactController.budgetImpact());
        if (impactController.isGreen()) {
            spend.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

        } else {
            spend.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        //set value of impact to the TextView for spending impact
        spend.setText(impactText);
    }

    /**
     * When Clear button is clicked
     * @param view Clear button
     * clears all fields to empty so activity can be run with different numbers
     * author: Dixie Cravens
     */
    public void onClear (View view) {
        amountTv.setText("");
        spend.setBackgroundColor(Color.TRANSPARENT);
        spend.setText("");
        save.setBackgroundColor(Color.TRANSPARENT);
        save.setText("");
        //TODO: clear radio buttons

    }
//********* menu for mavigation *****************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.nav_dashboard) {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Don't allow back to this page since we are leaving it ... activity object will get deleted
        }
        if (item.getItemId() == R.id.nav_edit_data) {
            Intent intent = new Intent(this, EditDataActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.nav_edit_budget) {
            Intent intent = new Intent(this, EditBudgetActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.nav_impact) {
            // do nothing ... we are already on this activity
        }
        return super.onOptionsItemSelected(item);
    }
}





