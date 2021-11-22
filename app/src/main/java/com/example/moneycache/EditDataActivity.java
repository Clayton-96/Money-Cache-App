package com.example.moneycache;

import static java.lang.String.format;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class EditDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditDataController dataController;

    MyItemRecyclerViewAdapter recyclerView;
    private String categoryChosen;
    private String dataItem;
    BankData data;


    public String getCategoryChosen() {
        return categoryChosen;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        //create the controller
        dataController = new EditDataController(this);
        // call the start() in controller to get data for view
        dataController.start();
        //*****Spinner for category selection*************
        // code came from:https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner spinner = findViewById(R.id.assign_category_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select a category");//I don't think this works by itself
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        dataController.saveFile();
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
        //TODO: set default text..https://stackoverflow.com/questions/867518/how-to-make-an-android-spinner-with-initial-text-select-one?rq=1
    }


    /**
     * handles the onClick for update_data button
     * sets BankData and categoryChosen from view and sends to controller updateData()
     *
     * @param view is view update button
     */
    public void handleUpdateDataClick(View view) {
        // retrieve edited data from ItemFragment RecyclerView
        //TODO: Replace temp dataItem with retrieved data

        //set categoryChosen from Spinner selection
        Spinner spinner = findViewById(R.id.assign_category_spinner);
        spinner.setOnItemSelectedListener(this);//sets global categoryChosen

        dataController.updateData(data, categoryChosen);
    }

    public void handleEditTransactionClick(View view){
        data = MyItemRecyclerViewAdapter.ViewHolder.mItem;
        //build and inflate the edit fragment
        Bundle bundle = new Bundle();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // retrieve edited data from ItemFragment RecyclerView
        //TODO: Replace temp data with data in recyclerView--but how????
        bundle.putString("date", data.getDate());
        bundle.putString("description", data.getDescription());
        bundle.putString("amount", String.valueOf(data.getAmount()));
        transaction.setReorderingAllowed(true);
        transaction.add(R.id.frag_placeholder_edit_transaction,EditDataFragment.class, bundle);
        transaction.commit();


    }
    public void onDoneClick(View v, String newDate, String newDescription, String newAmount) {
        //put edited data into a JSON string
        dataItem = String.format("{\"date\": \"%s\", \"memo\": \"%s\", \"amount\": \"%s\"", newDate, newDescription, newAmount);

        //remove fragment from activity
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frag_placeholder_edit_transaction);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(fragment).commit();
    }

    public void onDeleteClick (View view) {
        //remove from
    }
}


//App data and files for saving to app specific file:
//https://developer.android.com/guide/topics/data