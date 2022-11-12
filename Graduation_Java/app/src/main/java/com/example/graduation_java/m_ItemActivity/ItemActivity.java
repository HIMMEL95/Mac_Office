package com.example.graduation_java.m_ItemActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.graduation_java.R;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    TextView nameTxt, addressTxt, heightTxt, infoTxt, adminTxt, adminNum, Reason, list;
    ImageView img;

    final static String iAddress = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoImgOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&mntiListNo=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        nameTxt = (TextView) findViewById(R.id.tv_title);
        addressTxt =(TextView) findViewById(R.id.tv_addr);
        heightTxt = (TextView) findViewById(R.id.tv_hgt);
        infoTxt = (TextView) findViewById(R.id.tv_info);
        adminTxt = (TextView) findViewById(R.id.adminTxt);
        adminNum = (TextView) findViewById(R.id.adminNum);
        Reason = (TextView) findViewById(R.id.Reason);
        list = (TextView) findViewById(R.id.imgAdd);
        img = findViewById(R.id.iv);

        Intent i = this.getIntent();

        String name = i.getExtras().getString("name");
        String address = i.getExtras().getString("address");
        String height = i.getExtras().getString("height");
        String info = i.getExtras().getString("info");
        String admin = i.getExtras().getString("admin");
        String adminNums = i.getExtras().getString("adminNum");
        String listNum = i.getExtras().getString("listNo");
        String top = i.getExtras().getString("top");
        String imgName = i.getExtras().getString("imgName");
        String imgFileName = i.getExtras().getString("imgFileName");
        String imgNo = i.getExtras().getString("imgNo");

        nameTxt.setText(name);
        addressTxt.setText(address);
        heightTxt.setText(height);
        infoTxt.setText(info);
        adminTxt.setText(admin);
        adminNum.setText(adminNums);
        Reason.setText(top);
        list.setText(listNum);

        Glide.with(this).load("www.forest.go.kr/images/data/down/mountain/").into(img);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}