package com.ono.locatporium;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityModel {

    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("participants")
    @Expose
    private int participants;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("accessibility")
    @Expose
    private double accessibility;


    public ActivityModel(String activity, String type, int participants, double price, String link, String key, double accessibility) {
        this.activity = activity;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.key = key;
        this.accessibility = accessibility;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(double accessibility) {
        this.accessibility = accessibility;
    }
}
