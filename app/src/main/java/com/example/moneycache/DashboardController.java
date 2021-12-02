package com.example.moneycache;

import java.util.List;

public class DashboardController {
    private final DashboardActivity dbActivity;
    private final DataModel model;

    List<String> items;

    public DashboardController(DashboardActivity dbActivity) {
        this.dbActivity = dbActivity;
        model = new DataModel();
    }

    public void start() {
        DataModel model = new DataModel();
        items = DataModel.getItems();
    }
    public List<String> getItems() {
        return items;
    }



}
