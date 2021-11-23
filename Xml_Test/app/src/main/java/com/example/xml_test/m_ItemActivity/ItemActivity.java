package com.example.xml_test.m_ItemActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.xml_test.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    TextView nameTxt, addressTxt, heightTxt, infoTxt, adminTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        nameTxt = (TextView) findViewById(R.id.tv_title);
        addressTxt =(TextView) findViewById(R.id.tv_addr);
        heightTxt = (TextView) findViewById(R.id.tv_hgt);
        infoTxt = (TextView) findViewById(R.id.tv_info);
        adminTxt = (TextView) findViewById(R.id.adminTxt);

        Intent i = this.getIntent();

        String name = i.getExtras().getString("name");
        String address = i.getExtras().getString("address");
        String height = i.getExtras().getString("height");
        String info = i.getExtras().getString("info");
        String admin = i.getExtras().getString("admin");

        nameTxt.setText(name);
        addressTxt.setText(address);
        heightTxt.setText(height);
        infoTxt.setText(info);
        adminTxt.setText(admin);

    }
}