package com.example.moneycache;

import static com.example.moneycache.DataModel.dataFile;

public class LoginController {

    //as soon as successful login is complete, retrieve bank data (from file or DB)
    private void retrieveBankData() {
        // extend Thread either in LoginController or in DataModel
        DataModel.toBankDataObjects(dataFile);
    }
}
