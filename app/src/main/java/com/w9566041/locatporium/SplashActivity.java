package com.w9566041.locatporium;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> startActivity(new Intent(SplashActivity.this, MainActivity.class)), 2000);
    }
}