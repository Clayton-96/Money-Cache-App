package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneycache.databinding.ActivityImpactBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

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

        impactController = new ImpactController(this);
        amountTv = findViewById(R.id.dollar_amount_what_if);
        spend = findViewById(R.id.spending_impact_display);
        save = findViewById(R.id.savings_impact_display);

    }
    // in the same onResume method we would check true or false value of radio button
    // if one time equals true "do something" add or subtract from category based on user input
    //else if recurring "do something if we get that far but realistically same thing as one time

    //send to controller
    // when boolean is complete add or subtract
    //then compare

    //event listeners to listen for a response
    //onclick updates the impact calls the value method and boolean method
    public Float getUserAmount() {
        Float amount = (Float.parseFloat(amountTv.getText().toString()));
        //create a method that reads the value from R.id.dollar_amount_what_if and assigns it to a variable
        Log.d("get user amount", "getUserAmount: " + amount);

        return amount;
   }
    //this is pseudocode to explain I need to check the radio button group selection
    public String radioButtonSelection(View view) {
        String string = "";
        //true return a string giving explanation
        //one time is true
        //return string one time
        //else return string
        return "one-time"; //return something;

    }
    public void updateImpactClick(View view) {
        //call method to get amount
        ImpactAmount = getUserAmount();
        //get frequency
        //call method to get frequency
        ImpactFrequency = radioButtonSelection(view);
        displayBudgetImpact();
        displaySavingsImpact();
    }

    //we have two so this has to be done a bit differently
    //2 methods that call the display alerts
    public void displaySavingsImpact() {
        String impactText = String.format("%.02f",impactController.savingsImpact());
        //set color of box to red
        if (impactController.isGreen()) {
            //set color of box to green...may have to set this green color in the values/themes
            save.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

        } else {
            save.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        //set value of impact to the TextView for savings impact

        save.setText(impactText);

    }

    public void displayBudgetImpact() {
        // to test hard code a value in
        //get some value from the controller
        String impactText = String.format("%.02f",impactController.budgetImpact());
        if (impactController.isGreen()) {
            spend.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

        } else {
            spend.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }
        //set value of impact to the TextView for spending impact
        spend.setText(impactText);
    }

    public void onClear (View view) {
        amountTv.setText("");
        spend.setBackgroundColor(View.INVISIBLE);
        spend.setText("");
        save.setBackgroundColor(View.INVISIBLE);
        save.setText("");
        //clear radio buttons

    }
}

    //so the above code will allow the controller to request needed info

    //




