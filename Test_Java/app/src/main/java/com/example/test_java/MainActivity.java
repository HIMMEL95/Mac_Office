package com.example.test_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MountainProject";

    String mountainData ;
    String mountainImgData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mountainData = getMountainXmlData();
                mountainImgData = getMountainImgData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    String getMountainXmlData() {
        StringBuffer buffer = new StringBuffer();

        String 
    }
}