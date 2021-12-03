package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    //NavigationActivity navigation;
    DashboardController controller;
    List<String> pieChartData;
    TextView b, d, dr, s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Dashboard");

        controller = new DashboardController(this);
        controller.start();
        pieChartData = controller.getItems();
        Log.d("Created controller", "onCreate: ");
        b = (TextView) findViewById(R.id.bills_alert);
        d = (TextView) findViewById(R.id.discretionary_alert);
        dr = (TextView) findViewById(R.id.debtreduction_alert);
        s = (TextView) findViewById(R.id.savings_alert);
        Float bAmt = controller.billsAmt;
        Float dAmt = controller.discretionaryAmt;
        Float drAmt = controller.debtReductionAmt;
        Float sAmt = controller.savingsAmt;


        if (controller.billsAmtGreen) {
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        b.setText(bAmt.toString());
        if (controller.discretionarAmtGreen) {
            d.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            d.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        d.setText(dAmt.toString());
        if (controller.debtReductionAmtGreen) {
            dr.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            dr.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        dr.setText(drAmt.toString());
        if (controller.savingsAmtGreen) {
            s.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else {
            s.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        s.setText(sAmt.toString());

        //navigation = new NavigationActivity();
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