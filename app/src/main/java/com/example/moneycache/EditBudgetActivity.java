package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditBudgetActivity extends AppCompatActivity {

    private EditBudgetController controller;
    private EditText i;
    private EditText b;
    private EditText d;
    private EditText dr;
    private EditText s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        //get Budget Items from controller to fill in
        //https://stackoverflow.com/questions/33164886/android-textview-do-not-concatenate-text-displayed-with-settext
        controller.start();
        i = findViewById(R.id.income_category);
        i.setText(getString(R.string.income_text,controller.getIncome()));
        b = findViewById(R.id.bills_category);
        b.setText(getString(R.string.bills_text, controller.getBills()));
        d = findViewById(R.id.discretionary_category);
        d.setText(getString(R.string.discretionary_text, controller.getDiscretionary()));
        dr = findViewById(R.id.debt_reduction_category);
        dr.setText(getString(R.string.debt_reduction_text, controller.getDebtReduction()));
        s = findViewById(R.id.savings_category);
        s.setText(getString(R.string.savings_text, controller.getSavings()));




        //need to add a EditBudgetFragment with an onClick from any 'Edit' button.
        //see https://developer.android.com/guide/fragments/create#add-programmatic
        if (savedInstanceState == null) {
            // bring in data with Bundle for each unique edit view
            // each edit button is going to have to have a unique identifier.
            Bundle bundle = new Bundle();
            bundle.putInt("some_int", 0);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, EditBudgetFragment.class, null)
                    .commit();
        }
    }
}