package com.example.moneycache;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class EditDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditDataController dataController;

    //RecyclerView recyclerView;
    private String categoryChosen;
    private String dataItem;

    public String getDataItem() {
        return dataItem;
    }

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
        dataItem = "[\"10/12/2021\",\"MCDONALDS\",\"-4.33\"]";
        //enter R.id when created
        //dataItem = findViewById(R.id.edited_data_string);
        //set categoryChosen from Spinner selection
        Spinner spinner = findViewById(R.id.assign_category_spinner);
        spinner.setOnItemSelectedListener(this);//sets global categoryChosen

        dataController.updateData();
    }
}


//App data and files for saving to app specific file:
//https://developer.android.com/guide/topics/data