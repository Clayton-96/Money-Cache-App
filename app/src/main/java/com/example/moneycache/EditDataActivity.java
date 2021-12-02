package com.example.moneycache;

import static java.lang.String.format;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.moneycache.databinding.ActivityNavigationBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class EditDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditDataController dataController;

    MyItemRecyclerViewAdapter recyclerView;
    private String categoryChosen;
    private String dataItem;
    String editedData;
    BankData data;
    NavigationActivity navigation;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;


    public String getCategoryChosen() {
        return categoryChosen;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        setTitle("Edit Bank Data");
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
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

//        navigation = new NavigationActivity();
//        //navigation.onSupportNavigateUp();
//        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_dashboard, R.id.nav_edit_data, R.id.nav_edit_budget, R.id.nav_impact)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
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
        //set categoryChosen from Spinner selection
        Spinner spinner = findViewById(R.id.assign_category_spinner);
        spinner.setOnItemSelectedListener(this); //sets global categoryChosen
        //dataItem = String.format("{\"date\": \"%s\", \"memo\": \"%s\", \"amount\": \"%s\", \"category\": \"%s\"}", newDate, newDescription, newAmount, categoryChosen);
        JsonElement j = new Gson().fromJson(dataItem, JsonElement.class);
        JsonObject newData = j.getAsJsonObject();
        newData.addProperty("category", categoryChosen);
        Gson gson = new Gson();
        BankData bankData = gson.fromJson(newData, BankData.class);
        //BankData bankData1 = gson.fromJson(bankData, BankData.class);

        Log.d("editedData", "handleUpdateDataClick: " + newData);

        dataController.updateData(bankData);
        //dataController.updateData(data);
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
        dataItem = String.format("{\"date\": \"%s\", \"memo\": \"%s\", \"amount\": \"%s\"}", newDate, newDescription, newAmount);
//        Gson gson = new Gson();
//        editedData = gson.toJson(dataItem, BankData.class);
        Log.d("JSON string builder", "onDoneClick: " + dataItem);
        //remove fragment from activity
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frag_placeholder_edit_transaction);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(fragment).commit();
    }

    public void onDeleteClick (View view) {
        //remove from
    }


    //*******Navigation Menu ********
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
            // do nothing ... we are already on this activity
        }
        if (item.getItemId() == R.id.nav_edit_budget) {
            Intent intent = new Intent(this, EditBudgetActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.nav_impact) {
            Intent intent = new Intent(this, ImpactActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}


//App data and files for saving to app specific file:
//https://developer.android.com/guide/topics/data