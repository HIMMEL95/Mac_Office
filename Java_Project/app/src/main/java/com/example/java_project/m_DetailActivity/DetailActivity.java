package com.example.java_project.m_DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.java_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {

    TextView nameTxt, addressTxt, heightTxt, infoTxt, adminTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        nameTxt = (TextView) findViewById(R.id.nameTxt);
        addressTxt = (TextView) findViewById(R.id.);
        heightTxt = (TextView) findViewById(R.id.);
        infoTxt = (TextView) findViewById(R.id.);
        adminTxt = (TextView) findViewById(R.id.);

        Intent i = this.getIntent();

        String name = i.getExtras().getString("NAME_KEY");
        String address = i.getExtras().getString("ADDRESS_KEY");
        String height = i.getExtras().getString("HEIGHT_KEY");
        String info = i.getExtras().getString("INFO_KEY");
        String admin = i.getExtras().getString("ADMIN_KEY");

        nameTxt.setText(name);
        addressTxt.setText(address);
        heightTxt.setText(height);
        infoTxt.setText(info);
        adminTxt.setText(admin);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}