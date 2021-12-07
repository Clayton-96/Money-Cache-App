package com.example.moneycache;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EditDataController {

    // bring in an instance of the Activity
    private final EditDataActivity dataActivity;
    private final DataModel model;
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
        //change json to BankData and save it an Array of BankData items
//        Gson gson = new Gson();
//        BankData bankData = gson.fromJson(dataItem, BankData.class);
        //dataItemArray.add(dataItem);


        // save in temp file here until app is closing,----dataItemArray will save objects until app closes
        // TODO:then save file to DB at close in saveFile() called from activity onStop()
    }

    /**
     * saves file of newly uploaded and edited BankData objects to database
     * file contents are generated in updateData()
     */
    public void saveFile(){

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
