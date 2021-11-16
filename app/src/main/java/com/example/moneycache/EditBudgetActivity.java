package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class EditBudgetActivity extends AppCompatActivity {

    private EditBudgetController controller;




    public EditText i;
    private EditText b;
    private EditText d;
    private EditText dr;
    private EditText s;
    private String placeholder;

    public EditText getI() {
        return i;
    }
    public void setI(EditText i, String amount) {
        this.i = i;
        i = findViewById(R.id.income_category);
        i.setText(getString(R.string.income_text, amount));
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        //get Budget Items from controller to fill in
        //https://stackoverflow.com/questions/33164886/android-textview-do-not-concatenate-text-displayed-with-settext
        controller = new EditBudgetController(this);
        controller.start();

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
//        bundle.putString("amount", String.valueOf(i.getText()));
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.setReorderingAllowed(true);
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
     * fills category and placeholder id for fragment build
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
    public void handleUpdateButton(View view) {
        //hide the fragment: https://developer.android.com/guide/fragments/transactions#views
    }

}
//how do I pass info with an OnClick event?
//https://stackoverflow.com/questions/37105066/android-data-binding-pass-arguments-to-onclick-method