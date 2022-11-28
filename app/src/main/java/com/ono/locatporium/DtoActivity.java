package com.ono.locatporium;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Activity")
public class DtoActivity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "activity")
    private String activity = "";
    @ColumnInfo(name = "type")
    private String type = "";
    @ColumnInfo(name = "participants")
    private int participants;
    @ColumnInfo(name = "price")
    private double price = 0.0;
    @ColumnInfo(name = "link")
    private String link = "";
    @ColumnInfo(name = "accessibility")
    private double accessibility = 0.0;

    public DtoActivity(int id, String activity, String type, int participants, double price, String link, double accessibility) {
        this.id = id;
        this.activity = activity;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.accessibility = accessibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(double accessibility) {
        this.accessibility = accessibility;
    }
}
