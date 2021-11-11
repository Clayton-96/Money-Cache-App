package com.example.moneycache;

public class EditDataController {
    // bring in an instance of the Activity

    //display transactions in ListView
    //    Where is this data? Not in db...not yet. File in Shared Resources?
    //    It needs to be Json (or maybe the list of bankData objects??)
    //    and only showing pertinent data, so 'update' is db ready

    //select transaction in listView..How?
    //  Auto select first line?

    //display transaction in tranItem box
    //  Auto (select and) display first item in List?

    //edit Transaction
    //  how much edit do we allow? They could hurt json formatting.
    // only allow editing between commas and ""...so 3 fields of editing?

    // create/call 3 fields for editing with appropriate text in each one

    //delete Transaction ...would this need an update? Maybe a warning before delete. (Double press?)

    //create a category enum for budget category dropdown menu

    //select a category from dropdown menu

    //undo assignment of category on Transaction
    // **I don't think we will need this if selection isn't finalized until 'Select' is pushed

    //select assignment of category
    // **I don't think we will need this if we have an update button...nothing left to update.
    //Select could update or Update can.

    //Update will read tranItem box and category box and pass updated Json data to model
}
