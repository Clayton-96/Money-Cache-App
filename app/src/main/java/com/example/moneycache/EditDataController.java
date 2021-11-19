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

    public void updateData() {
        //get updated BankData item and category
        String item = dataActivity.getDataItem();//json formatted orString?? BankData object
        String category = dataActivity.getCategoryChosen();//category and date to store amount in
        //TODO: save in temp file here until app is closing,
        // then save file to DB at close in saveFile() called from activity onStop()
    }
    public void saveFile(){

    }


    //select transaction in recyclerView..How?
    //  Auto select first line?

    //display transaction in tranItem box
    //  Auto (select and) display first item in List?

    //edit Transaction
    //  how much edit do we allow? They could hurt json formatting.
    // only allow editing between commas and ""...so 3 fields of editing?


    //delete Transaction ...would this need an update? Maybe a warning before delete. (Double press?)

}
