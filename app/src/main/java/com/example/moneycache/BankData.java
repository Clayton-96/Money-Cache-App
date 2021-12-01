package com.example.moneycache;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * builds the BankData object with only the data stored in DB.
 * Data variables are listed in the same order as in the json file.
 * **Reads the bankdata.txt file***
 * TODO: bring this data in from the DB
 */
public class BankData {
    //TODO: Ask Bro. Macbeth about this list that was generated by ItemFragment.java line 67
    static final String JSON_FILE = "app/src/main/java/com/example/moneycache/bankdata.txt";
    //public static final List<BankData> ITEMS = jsonToObjectList(JSON_FILE);

    private String date;
    // description would be the string that describes the payee or payer, my bank uses 'memo'
    private String memo;
    // amount can be positive for income or negative for expense
    //TODO: cast string from json into float---are the constructors enough??
    private Float amount;
    // amount2 for files that have separate debit and credit columns
    //private float amount2;
    private String category;


    public BankData(String date, String memo, String amount) {
        this.date = date;
        this.memo = memo;
        this.amount = Float.parseFloat(amount);
        category = "";
    }
    public BankData(String date, String memo, String amount, String amount2) {
        this.date = date;
        this.memo = memo;
        this.amount = Float.parseFloat(amount) + Float.parseFloat(amount2);
        //this.amount2 = amount2;
        category = "";
    }
    /**
     *https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
     * @return List<BankData>
     */
    public static List<BankData> jsonToObjectList(Activity activity) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(activity.getResources().openRawResource(R.raw.bankdata)));

        Gson gson = new Gson();
        //BankData bankData = gson.fromJson(dataJson, BankData.class);
        Type bankDataListType = new TypeToken<ArrayList<BankData>>(){}.getType();
        List<BankData> bankDataList = gson.fromJson(reader, bankDataListType);

        return bankDataList;


    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return memo;
    }

    public void setDescription(String memo) {
        this.memo = memo;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public String getStringAmount() {
        return Float.toString(amount);
    }

    public String getCategory() { return category; }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BankData{" +
                "date='" + date + '\'' +
                ", memo='" + memo + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                '}';
    }
}


