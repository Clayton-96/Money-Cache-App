package com.example.moneycache;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "date";
    private static final String ARG_PARAM2 = "description";
    private static final String ARG_PARAM3 = "amount";

    // TODO: Rename and change types of parameters
    private String date;
    private String description;
    private String amount;
    private EditText editDate, editDescription, editAmount;
    private Button doneButton;

    public EditDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditDataFragment newInstance(String param1, String param2, String param3) {
        EditDataFragment fragment = new EditDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString(ARG_PARAM1);
            description = getArguments().getString(ARG_PARAM2);
            amount = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_edit_data, container, false);
        editDate = view.findViewById(R.id.editDate);
        //editDate.setText(getString(R.string.edit_budget_amount, amount));
        editDescription = view.findViewById(R.id.editDescription);
        //editDescription.setText(getString());
        editAmount = view.findViewById(R.id.editAmount);
        //editAmount.setText(getString());
        doneButton = view.findViewById(R.id.edit_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView d = view.findViewById(R.id.editDate);
                String newDate = d.getText().toString();
                TextView dc = view.findViewById(R.id.editDescription);
                String newDescription = dc.getText().toString();
                TextView a = view.findViewById(R.id.editAmount);
                String newAmount = a.getText().toString();
                EditDataActivity activity = (EditDataActivity) getActivity();
                activity.onDoneClick(v, newDate, newDescription, newAmount);
            }
        });


        return view;
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_edit_data, container, false);
    }
}