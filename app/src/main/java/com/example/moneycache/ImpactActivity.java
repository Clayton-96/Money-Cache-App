package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ImpactActivity extends AppCompatActivity {


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
        Float amount = findViewById(R.id.dollar_amount_what_if);
        //create a method that reads the value from R.id.dollar_amount_what_if and assigns it to a variable
        return amount;
    }
    //this is pseudocode to explain I need to check the radio button group selection
    public String radioButtonSelection() {
        //true return a string giving explanation
        //one time is true
        //return string one time
        //else return string
        return string; //return something;

    }
    public void updateImpactClick() {
        //call method to get amount
        ImpactAmount = getUserAmount();
        //get frequency
        //call method to get frequency
        ImpactFrequency = radioButtonSelection();

        //we have two so this has to be done a bit differently
        //2 methods that call the display alerts
    public void displaySavingsImpact() {
        // to test hard code a value in
        //get some value from the controller
        //if (what if value(float) is >= 0)
            // then show amount in green
        //if (what if value(float) is < 0)
            // then show amount in red
        }
    public void displayBudgetImpact() {
        // to test hard code a value in
        //get some value from the controller
        //if (what if value(float) is >= 0)
        // then show amount in green
        //if (what if value(float) is < 0)
        // then show amount in red
        }
    }

    //so the above code will allow the controller to request needed info

    //
}



