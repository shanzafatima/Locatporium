package com.w9566041.locatporium;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewerActivity extends AppCompatActivity {

    private ImageView ivViewer;
    private Button btnBack;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        Intent intent = getIntent();
        String str = intent.getStringExtra("Image");
        ivViewer = findViewById(R.id.ivViewer);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivViewer.setImageBitmap(getBitmapFromEncodedString(str));
    }

    private Bitmap getBitmapFromEncodedString(String encodedString) {
        byte[] arr = Base64.decode(encodedString, Base64.URL_SAFE);
        Bitmap img = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        return img;
    }
}