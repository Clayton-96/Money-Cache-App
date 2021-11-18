package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditDataController dataController;
    //RecyclerView recyclerView;
    public String categoryChosen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        //create the controller
        dataController = new EditDataController(this);
        // call the start() in controller to get data for view
        dataController.start();

        //recyclerView = findViewById(R.id.recyclerView);

        // code came from:https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner spinner = findViewById(R.id.assign_category_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    /**
     * for category spinner--override on the OnItemSelectedListener interface
     */

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        categoryChosen = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        //TODO: create an error message to user
    }

    /**
     * handles the onClick for update_data button
     * @param view is view object button
     */
    public void handleUpdateDataClick(View view) {
        //gather data and send to controller

        //set categoryChosen from Spinner selection
        Spinner spinner = findViewById(R.id.assign_category_spinner);
        spinner.setOnItemSelectedListener(this);
    }


}