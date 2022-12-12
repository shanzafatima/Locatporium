package com.w9566041.locatporium;

import android.app.Application;

import io.paperdb.Paper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);

        DatabaseEngine.getInstance(this).initializeDatabase();
    }
}
