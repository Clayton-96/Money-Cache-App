package com.example.moneycache;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EditDataController {

    // bring in an instance of the Activity
    private final EditDataActivity dataActivity;
    private final DataModel model;
    MyItemRecyclerViewAdapter recyclerView;
    // dataItem Array accepts all the edited BankData items from recyclerView and
    // stores them until app closes and they go to permanent storage
    private ArrayList<BankData> dataItemArray = new ArrayList<>();// same thing as MyItemRecyclerViewAdapter.items
    private String category;
    public ArrayList<BankData> getDataItemArray() {
        return dataItemArray;
    }

    public EditDataController(EditDataActivity dataActivity){
        this.dataActivity = dataActivity;
        model = new DataModel();
    }
    public EditDataActivity getDataActivity() {
        return dataActivity;
    }
    public void start() {
        //whatever needs to be given data to start the view goes here
        //recyclerView reads transactions from??? file? DB?

    }

    /**
     * Saves newly uploaded and edited bank data line-item to a temporary file
     * @param dataItem BankData in json format
     */
    public void updateData(BankData dataItem) {
        //get updated BankData item and category
//        //is not going to work for sharedPref-- change json to BankData and save it an Array of BankData items
//        Gson gson = new Gson();
//        BankData bankData = gson.fromJson(dataItem, BankData.class);
//        dataItemArray.add(dataItem);

        //dataItem can be used to save amount into correct category_total in sharedPref (dataItem.date, dataItem.category,) dataItem.amount
        String category =  dataItem.getCategory();
        Float amount = dataItem.getAmount();

        model.updateCategoryTotals(category, amount, dataActivity);

        //recyclerView.removeItem();
    }

    /**
     * Calls DataModel userFileToBankObjects()
     * asks for new bank data to be pulled into app
     */
    public void getNewData() {
        model.userFileToString(dataActivity);

        //TODO: need a listener that tells recyclerView new data has been added to JSON_FILE


    }



}
