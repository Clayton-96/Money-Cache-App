package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Spinner;

public class EditDataActivity extends AppCompatActivity {
    private EditDataController dataController;
    //RecyclerView recyclerView;

    private Spinner catSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        //create the controller
        dataController = new EditDataController(this);
        // call the start() in controller to get data for view
        dataController.start();

        //recyclerView = findViewById(R.id.recyclerView);
    }
}