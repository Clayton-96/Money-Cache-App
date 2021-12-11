package com.example.moneycache;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    DashboardController controller;
    TextView b, d, dr, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Dashboard");
    // when we create the page we run a start method that creates all the data needed for the page
        controller = new DashboardController(this);
        controller.start();
        //pieChartData = controller.getItems();

        b = (TextView) findViewById(R.id.bills_alert);
        d = (TextView) findViewById(R.id.discretionary_alert);
        dr = (TextView) findViewById(R.id.debtreduction_alert);
        s = (TextView) findViewById(R.id.savings_alert);
        Float bAmt = controller.billsAmt;
        Float dAmt = controller.discretionaryAmt;
        Float drAmt = controller.debtReductionAmt;
        Float sAmt = controller.savingsAmt;

        //alert boxes
        if (controller.billsAmtGreen) {
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        b.setText(getString(R.string.bills_alert, String.format("%.2f", bAmt)));

        if (controller.discretionaryAmtGreen) {
            d.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            d.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        d.setText(getString(R.string.discretionary_alert, String.format("%.2f", dAmt)));

        if (controller.debtReductionAmtGreen) {
            dr.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            dr.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        dr.setText(getString(R.string.debtreduction_alert, String.format("%.2f", drAmt)));

        if (controller.savingsAmtGreen) {
            s.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            s.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        s.setText(getString(R.string.savings_alert, String.format("%.2f", sAmt)));

        //create pie chart
        drawPie();

    }

    /**
     * Draws an animated pie chart based on total spent/each category spending
     * https://github.com/razerdp/AnimatedPieView
     * https://www.youtube.com/watch?v=cA-HNN2_-TY&t=35s
     */
    public void drawPie() {
        int bills = (int)controller.pieChartBills();
        int discretionary = (int)controller.pieChartDiscretionary();
        int debtReduction = (int)controller.pieChartReduction();
        int savings = (int)controller.pieChartSavings();

        //this code came from AnimatedPieView
        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(bills, ContextCompat.getColor(this, R.color.bills), "Bills"))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(discretionary, ContextCompat.getColor(this,R.color.discretionary), "Discretionary"))
                .addData(new SimplePieInfo(debtReduction, ContextCompat.getColor(this,R.color.debtReduction), "Debtreduction"))
                .addData(new SimplePieInfo(savings, ContextCompat.getColor(this,R.color.savings), "Savings")).drawText(true).strokeMode(false)

      .duration(2000).textSize(30);// draw pie animation duration

        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }


//****** menu for navigation *************
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
            // do nothing ... we are already on this activity
        }
        if (item.getItemId() == R.id.nav_edit_data) {
            Intent intent = new Intent(this, EditDataActivity.class);
            startActivity(intent);
            finish();// Don't allow back to this page since we are leaving it ... activity object will get deleted

        }
        if (item.getItemId() == R.id.nav_edit_budget) {
            Intent intent = new Intent(this, EditBudgetActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.nav_impact) {
            Intent intent = new Intent(this, ImpactActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}