package com.example.mountain.m_ItemActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mountain.MainActivity;
import com.example.mountain.R;
import com.example.mountain.m_DataObject.Item;
import com.example.mountain.m_DataObject.Mountain;
import com.example.mountain.m_UI.MyAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    TextView nameTxt, addressTxt, heightTxt, infoTxt, adminTxt, adminNumTxt, listNoTxt;
    ImageView img;

    final static String imgAddress = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoImgOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&mntiListNo=";
    final static String iAddress = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoImgOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&mntiListNo=111100101";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        nameTxt = (TextView) findViewById(R.id.name);
        addressTxt =(TextView) findViewById(R.id.address);
        heightTxt = (TextView) findViewById(R.id.height);
        infoTxt = (TextView) findViewById(R.id.mtnInfo);
        adminTxt = (TextView) findViewById(R.id.admin);
        adminNumTxt = (TextView) findViewById(R.id.adminNum);
        listNoTxt = (TextView) findViewById(R.id.listNo);
        img =  findViewById(R.id.iv);

        Intent i = this.getIntent();

        String name = i.getExtras().getString("name");
        String address = i.getExtras().getString("address");
        String height = i.getExtras().getString("height");
        String info = i.getExtras().getString("info");
        String admin = i.getExtras().getString("admin");
        String adminNum = i.getExtras().getString("adminNum");
        String imgTxt = i.getExtras().getString("img");
        String listNo = i.getExtras().getString("listNo");
        String imgName = i.getExtras().getString("imgName");
        String imgFileName = i.getExtras().getString("imgFileName");

        nameTxt.setText(name);
        addressTxt.setText(address);
        heightTxt.setText(height);
        infoTxt.setText(info);
        adminTxt.setText(admin);
        adminNumTxt.setText(adminNum);
        listNoTxt.setText(listNo);

        Glide.with(this).load("https://www.forest.go.kr/images/data/down/mountain/"+imgFileName).into(img);

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