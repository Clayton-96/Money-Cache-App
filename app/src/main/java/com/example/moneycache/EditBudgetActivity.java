package com.example.moneycache;

import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditBudgetActivity extends AppCompatActivity {

    private EditBudgetController controller;

    private TextView i;
    private TextView b;
    private TextView d;
    private TextView dr;
    private TextView s;

    public String getPlaceholder() {
        return placeholder;
    }

    private String placeholder;
    EditBudgetFragment f = new EditBudgetFragment();


    public TextView getI() {
        return i;
    }
    public void setI(TextView i, String amount) {
        this.i = i;
        i = findViewById(R.id.income_category);
        i.setText(getString(R.string.income_text, amount));
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);
        setTitle("Edit Budget");

        //get Budget Items from controller to fill in as text
        //https://stackoverflow.com/questions/33164886/android-textview-do-not-concatenate-text-displayed-with-settext
        controller = new EditBudgetController(this);
        controller.start();

        //set text in each category view object
        i = findViewById(R.id.income_category);
        i.setText(getString(R.string.income_text, controller.getIncome()));
        b = findViewById(R.id.bills_category);
        b.setText(getString(R.string.bills_text, controller.getBills()));
        d = findViewById(R.id.discretionary_category);
        d.setText(getString(R.string.discretionary_text, controller.getDiscretionary()));
        dr = findViewById(R.id.debt_reduction_category);
        dr.setText(getString(R.string.debt_reduction_text, controller.getDebtReduction()));
        s = findViewById(R.id.savings_category);
        s.setText(getString(R.string.savings_text, controller.getSavings()));

    }

    /**
     * starts the fragment and places it in the correct placeholder inside Layout
     * @param placeholder indicates which edit button was clicked and which placeholder should receive the fragment
     *author: Dixie Cravens
     */
    public void startFragment(String placeholder){
        Bundle bundle = new Bundle();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (placeholder){
            case ("income"):
                bundle.putString("amount", controller.getIncome());
                transaction.setReorderingAllowed(true);
                transaction.add(R.id.frag_placeholder_income,EditBudgetFragment.class, bundle);
                break;
            case ("bills"):
                bundle.putString("amount", controller.getBills());
                transaction.setReorderingAllowed(true);
                transaction.add(R.id.frag_placeholder_bills,EditBudgetFragment.class, bundle);
                break;
            case ("discretionary"):
                bundle.putString("amount", controller.getDiscretionary());
                transaction.setReorderingAllowed(true);
                transaction.add(R.id.frag_placeholder_discretionary,EditBudgetFragment.class, bundle);
                break;
            case ("debt_reduction"):
                bundle.putString("amount", controller.getDebtReduction());
                transaction.setReorderingAllowed(true);
                transaction.add(R.id.frag_placeholder_debt_reduction,EditBudgetFragment.class, bundle);
                break;
            case ("savings"):
                bundle.putString("amount", controller.getSavings());
                transaction.setReorderingAllowed(true);
                transaction.add(R.id.frag_placeholder_savings,EditBudgetFragment.class, bundle);
                break;
            default:
                Toast.makeText(this, "No category found.", Toast.LENGTH_LONG).show();

        }
        transaction.commit();
    }


    /**
     * receives onClick from (category specific))_edit_button
     * fills placeholder id for fragment build
     * calls the startFragment() method, passing the placeholder parameter
     * author: Dixie Cravens
     */
    public void handleEditButton(View view) {
        if (view.getId() == R.id.income_edit_button) {
            placeholder = "income";
            Toast.makeText(this, "Edit Income clicked", Toast.LENGTH_LONG).show();
            startFragment(placeholder);
        } else if (view.getId() == R.id.bill_edit_button) {
            placeholder = "bills";
            Toast.makeText(this, "Edit Bills clicked", Toast.LENGTH_LONG).show();
            startFragment(placeholder);
        } else if (view.getId() == R.id.discretionary_edit_button) {
            placeholder = "discretionary";
            Toast.makeText(this, "Edit Discretionary clicked", Toast.LENGTH_LONG).show();
            startFragment(placeholder);
        } else if (view.getId() == R.id.debt_reduction_edit_button) {
            placeholder = "debt_reduction";
            Toast.makeText(this, "Edit Debt Reduction clicked", Toast.LENGTH_LONG).show();
            startFragment(placeholder);
        } else if (view.getId() == R.id.savings_edit_button) {
            placeholder = "savings";
            Toast.makeText(this, "Edit Savings clicked", Toast.LENGTH_LONG).show();
            startFragment(placeholder);
        } else {
            placeholder = null;
            Toast.makeText(this, "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * receives the onClick of Update button
     * updates all categories to the current value in view using a setter in the controller
     * calls the controller.onUpdate() method to save values to model/db
     * @param view is the view object for each category
     * Author: Dixie Cravens
     */
    public void handleUpdateButton(View view) {
        //save all fields to their variables use a setter() in controller
        controller.setIncome(i.getText().toString());
        controller.setBills(b.getText().toString());
        controller.setDiscretionary(d.getText().toString());
        controller.setDebtReduction(dr.getText().toString());
        controller.setSavings(s.getText().toString());

        // send new data to controller by calling onUpdate() method.
        controller.onUpdate();
        Toast.makeText(this, "Budget Updated", Toast.LENGTH_LONG).show();

        //hide or detach the fragment: https://developer.android.com/guide/fragments/transactions#views
    }

    /**
     * onClick response to 'Done button in fragment edit activity
     * resets the budget item view to the new amount
     * @param amount
     * @param placeholder
     * author: Dixie Cravens
     */
    public void onDoneClick(View v, String amount, String placeholder) {

        this.placeholder = placeholder;
        //EditText a = findViewById(R.id.editBudgetAmount);

        //get placeholder associated with activity to specify a switch statement for each category
        switch (placeholder) {
            case "income":
                i.setText(getString(R.string.income_text, amount));
                break;
            case "bills":
                b.setText(getString(R.string.bills_text, amount));
                break;
            case "discretionary":
                d.setText(getString(R.string.discretionary_text, amount));
                break;
            case "debt_reduction":
                dr.setText(getString(R.string.debt_reduction_text,amount));
                break;
            case "savings":
                s.setText(getString(R.string.savings_text, amount));
                break;
            default:
                Toast.makeText(this, "Oops, something went wrong!", Toast.LENGTH_LONG).show();
                break;
        }


        //s.setText(getString(R.string.savings_text, a.getText().toString()));

        //remove fragment from activity
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;
        FragmentTransaction transaction;
        switch (placeholder) {
            case "income":
                fragment = fm.findFragmentById(R.id.frag_placeholder_income);
                transaction = fm.beginTransaction();
                transaction.remove(fragment).commit();
                break;
            case "bills":
                fragment = fm.findFragmentById(R.id.frag_placeholder_bills);
                transaction = fm.beginTransaction();
                transaction.remove(fragment).commit();
                break;
            case "discretionary":
                fragment = fm.findFragmentById(R.id.frag_placeholder_discretionary);
                transaction = fm.beginTransaction();
                transaction.remove(fragment).commit();
                break;
            case "debt_reduction":
                fragment = fm.findFragmentById(R.id.frag_placeholder_debt_reduction);
                transaction = fm.beginTransaction();
                transaction.remove(fragment).commit();
                break;
            case "savings":
                fragment = fm.findFragmentById(R.id.frag_placeholder_savings);
                transaction = fm.beginTransaction();
                transaction.remove(fragment).commit();
                break;
            default:
                Toast.makeText(this, "Oops, something went wrong!", Toast.LENGTH_LONG).show();
                break;
        }

    }
//***** menu for navigation ***********
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
            // do nothing ... we are already on this activity
        }
        if (item.getItemId() == R.id.nav_impact) {
            Intent intent = new Intent(this, ImpactActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
//how do I pass info with an OnClick event?
//https://stackoverflow.com/questions/37105066/android-data-binding-pass-arguments-to-onclick-method