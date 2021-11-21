package com.example.moneycache;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneycache.databinding.ActivityEditDataBinding;
import com.example.moneycache.databinding.FragmentEditDataBinding;
import com.example.moneycache.databinding.FragmentItemBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDataFragment extends Fragment {
    MyItemRecyclerViewAdapter recyclerViewAdapter;
    MyItemRecyclerViewAdapter.ViewHolder holder;
    EditDataActivity activity;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "date";
    private static final String ARG_PARAM2 = "description";
    private static final String ARG_PARAM3 = "amount";

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
//https://stackoverflow.com/questions/34706399/how-to-use-data-binding-with-fragment
    private FragmentEditDataBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditDataBinding.inflate(inflater, container, false);
        binding.editDate.setText(date);
        binding.editDescription.setText(description);
        binding.editAmount.setText(amount);
        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onDoneClick(v, editDate.getText().toString(),
                        editDescription.getText().toString(),
                        editAmount.getText().toString());
            }
        });
        View view = binding.getRoot();
        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}