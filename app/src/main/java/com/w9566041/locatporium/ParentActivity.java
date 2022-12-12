package com.w9566041.locatporium;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParentActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        IntentFilter airPlaneModeFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airPlaneModeReceiver, airPlaneModeFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(airPlaneModeReceiver);
        super.onPause();
    }

    private BroadcastReceiver airPlaneModeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean airPlaneModeEnabled = intent.getBooleanExtra("state", false);
            if (airPlaneModeEnabled) {
                Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show();
            }
        }
    };
}
