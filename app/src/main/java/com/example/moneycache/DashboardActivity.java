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

    //NavigationActivity navigation;
    DashboardController controller;
    List<String> pieChartData;
    TextView b, d, dr, s;

    //Variables needed for pie chart
    Float bAmt, drAmt, dAmt, sAmt;
    Float income; //(income = controller.getIncome())


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Dashboard");
    // when we create the page we run a start method that creates all the data needed for the page
    //
        controller = new DashboardController(this);
        controller.start();


        pieChartData = controller.getItems();
        Log.d("Created controller", "onCreate: ");
        b = (TextView) findViewById(R.id.bills_alert);
        d = (TextView) findViewById(R.id.discretionary_alert);
        dr = (TextView) findViewById(R.id.debtreduction_alert);
        s = (TextView) findViewById(R.id.savings_alert);
        bAmt = controller.billsAmt;
        dAmt = controller.discretionaryAmt;
        drAmt = controller.debtReductionAmt;
        sAmt = controller.savingsAmt;




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

//        //Pie Chart Update
//            // Calculate the slice size and update the pie chart:
//            ProgressBar pieChart = findViewById(R.id.stats_progressbar);
//
//            Float totalSpent = controller.getTotalSpent();
//            int bills = (int)controller.pieChartBills();
//            int discretionary = (int)controller.pieChartDiscretionary();
//            int debtReduction = (int)controller.pieChartReduction();
//            int savings = (int)controller.pieChartSavings();
//            //int progress = (int) (d * 100);
//            pieChart.setProgress(bills);
//            pieChart.setProgress(discretionary);
//            pieChart.setProgress(debtReduction);
//            pieChart.setProgress(savings);
        drawPie();


    }

    public void drawPie() {
        int bills = (int)controller.pieChartBills();
        int discretionary = (int)controller.pieChartDiscretionary();
        int debtReduction = (int)controller.pieChartReduction();
        int savings = (int)controller.pieChartSavings();

        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(bills, R.color.bills, "Bills"))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(discretionary, R.color.discretionary, "Discretionary"))
                .addData(new SimplePieInfo(debtReduction, R.color.debtReduction, "Debtreduction"))
                .addData(new SimplePieInfo(savings, R.color.savings, "Savings")).drawText(true).strokeMode(false)

      .duration(2000).textSize(30);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }
    //
//    private void updateChart(){
//        // Update the text in a center of the chart:
//        TextView numberOfCals = findViewById(R.id.number_of_calories);
//        numberOfCals.setText(String.valueOf(calsBurned) + " / " + calsConsumed);
//
//        // Calculate the slice size and update the pie chart:
//        ProgressBar pieChart = findViewById(R.id.stats_progressbar);
//        double d = (double) calsBurned / (double) calsConsumed;
//        int progress = (int) (d * 100);
//        pieChart.setProgress(progress);
//    }






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