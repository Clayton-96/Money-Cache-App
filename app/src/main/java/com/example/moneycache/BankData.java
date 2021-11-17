package com.example.moneycache;

/**
 * builds the BankData object with only the data stored in DB.
 * Data variables are listed in the same order as in the json file.
 * **Reads the bankData.txt file***
 * TODO: bring this data in from the DB
 */
public class BankData {
    private String date;
    // description would be the string that describes the payee or payer, my bank uses 'memo'
    private String description;
    // amount can be positive for income or negative for expense
    //TODO: cast string from json into float
    private Float amount;
    // amount2 for files that have separate debit and credit columns
    //private float amount2;


    public BankData(String date, String description, String amount) {
        this.date = date;
        this.description = description;
        this.amount = Float.parseFloat(amount);
    }
    public BankData(String date, String description, String amount, String amount2) {
        this.date = date;
        this.description = description;
        this.amount = Float.parseFloat(amount) + Float.parseFloat(amount2);
        //this.amount2 = amount2;
    }
}


