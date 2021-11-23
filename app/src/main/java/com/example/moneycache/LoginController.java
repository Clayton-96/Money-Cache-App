package com.example.moneycache;

import static com.example.moneycache.DataModel.dataFile;

public class LoginController {
    LoginActivity loginActivity;
    DataModel model;

    public LoginController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }
    //as soon as successful login is complete, retrieve bank data (from file or DB)
    // will need to specify a user at some point---that should come from LoginActivity as a param
    public void retrieveBankData() {
        // extend Thread either in LoginController or in DataModel
        DataModel.toBankDataObjects(dataFile);
    }
}
