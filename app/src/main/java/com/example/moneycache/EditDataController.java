package com.example.moneycache;

import java.util.List;

public class EditDataController {
    // bring in an instance of the Activity
    private final EditDataActivity dataActivity;
    private final DataModel model;

    public EditDataController(EditDataActivity dataActivity){
        this.dataActivity = dataActivity;
        model = new DataModel();
    }

    public void start() {
        //whatever needs to be given data to start the view goes here
        //recyclerView reads transactions from??? file? DB?
    }

    /**
     * Saves newly uploaded and edited bank data line-item to a temporary file
     * @param data BankData  in json format
     * @param category user-selected category for BankData
     */
    public void updateData(String data, String category) {
        //get updated BankData item and category

        //TODO: save in temp file here until app is closing,
        // then save file to DB at close in saveFile() called from activity onStop()
    }

    /**
     * saves file of newly uploaded and edited BankData objects to database
     * file contents are generated in updateData()
     */
    public void saveFile(){

    }


}
