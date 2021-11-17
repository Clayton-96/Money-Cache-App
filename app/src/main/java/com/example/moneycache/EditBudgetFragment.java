package com.example.moneycache;

import static java.lang.String.valueOf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class EditBudgetFragment extends Fragment {

    private String amount;
    private EditText budgetAmount;
    private Button doneButton;


    public EditBudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            amount = getArguments().getString("amount");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_edit_budget, container, false);
        budgetAmount = view.findViewById(R.id.editBudgetAmount);
        budgetAmount.setText(getString(R.string.edit_budget_amount, amount));
        doneButton = view.findViewById(R.id.edit_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditBudgetActivity activity = (EditBudgetActivity) getActivity();
               activity.onDoneClick(v);
            }
        });


       return view;
    }

}