package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditBudgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

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