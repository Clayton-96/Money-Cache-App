package com.example.moneycache;

import static java.lang.String.format;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
//    NavigationActivity navigation;
//    private AppBarConfiguration mAppBarConfiguration;
//    private ActivityNavigationBinding binding;



    public String getCategoryChosen() {
        return categoryChosen;
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
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
     * Handles the onClick for update_data button.
     * Updates bankData with category (categoryChosen from spinner)
     * and sends to controller updateData().
     * @param view is view update button
     * author: Dixie Cravens
     */
    public void handleUpdateDataClick(View view) {

        //set categoryChosen from Spinner selection
        Spinner spinner = findViewById(R.id.assign_category_spinner);
        spinner.setOnItemSelectedListener(this); //sets global categoryChosen
       //add category to bankData object
        JsonElement j = new Gson().fromJson(dataItem, JsonElement.class);
        JsonObject newData = j.getAsJsonObject();
        newData.addProperty("category", categoryChosen);
        Gson gson = new Gson();
        BankData bankData = gson.fromJson(newData, BankData.class);
        Toast.makeText(this, "Transaction Updated!", Toast.LENGTH_SHORT).show();
        dataController.updateData(bankData);
    }

    /**
     * Creates an EditData Fragment with data copied from item selected in recycler view fragment
     * @param view is 'Edit Transaction' button
     * author: Dixie Cravens
     */
    public void handleEditTransactionClick(View view){
        data = MyItemRecyclerViewAdapter.ViewHolder.mItem;
        //build and inflate the edit fragment
        Bundle bundle = new Bundle();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // retrieve to-be edited data from ItemFragment RecyclerView
        bundle.putString("date", data.getDate());
        bundle.putString("description", data.getDescription());
        bundle.putString("amount", String.valueOf(data.getAmount()));
        transaction.setReorderingAllowed(true);
        transaction.add(R.id.frag_placeholder_edit_transaction,EditDataFragment.class, bundle);
        transaction.commit();
    }

    /**
     * Copies data from the EditData Fragment and stores it into a JSON string,
     * then removes fragment.
     * @param v is 'Done' button in EditDataFragment
     * @param newDate is date from user editing
     * @param newDescription is description for user editing
     * @param newAmount is amount from user editing
     * author: Dixie Cravens
     */
    public void onDoneClick(View v, String newDate, String newDescription, String newAmount) {
        //put edited data into a JSON string (could have used BankData.toString() ?)
        dataItem = String.format("{\"date\": \"%s\", \"memo\": \"%s\", \"amount\": \"%s\"}", newDate, newDescription, newAmount);

        //remove fragment from activity
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frag_placeholder_edit_transaction);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(fragment).commit();
    }

    public void onDeleteClick (View view) {
        //completely remove transaction from data
    }

    /**
     * starts DataController getNewData()
     * @param view Upload Bank Data button
     * author: Dixie Cravens
     */
    public void handleUploadData (View view) {
        verifyStoragePermissions(this);
        dataController.getNewData();
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