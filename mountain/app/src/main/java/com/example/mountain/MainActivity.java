package com.example.mountain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mountain.m_DataObject.Item;
import com.example.mountain.m_DataObject.Mountain;
import com.example.mountain.m_UI.MyAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> items = new ArrayList<>();
    MyAdapter adapter;
    SwipeRefreshLayout refreshLayout;

    // add for search
    ArrayAdapter<String> arrayAdapter;
    List<ArrayList<Item>> data;

    final static String urlAddress = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&numOfRows=5000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        adapter = new MyAdapter(items, this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, data);
        data.add(items);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        readRss();

        refreshLayout = findViewById(R.id.layout_swipe);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                adapter.notifyDataSetChanged();
                readRss();
            }
        });
    }

    void readRss() {

        try {
            URL url = new URL(urlAddress);

            RssFeedTask task = new RssFeedTask();
            task.execute(url);

        } catch (MalformedURLException e) {e.printStackTrace();}
    }

    class RssFeedTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];

            try {
                InputStream is = url.openStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(is, "utf-8");
                int eventType = xpp.getEventType();

                Item item = null;
                String tagName = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            tagName = xpp.getName();

                            if (tagName.equals("item")) {
                                item = new Item();
                            } else if (tagName.equals("mntiname")) {
                                xpp.next();
                                if (item != null) item.setName(xpp.getText());
                            } else if (tagName.equals("mntiadd")) {
                                xpp.next();
                                if (item != null) item.setAddress(xpp.getText());
                            } else if (tagName.equals("mntihigh")) {
                                xpp.next();
                                if (item != null) item.setHeight(xpp.getText());
                            } else if (tagName.equals("mntidetails")) {
                                xpp.next();
                                if (item != null) item.setInfo(xpp.getText());
                            } else if (tagName.equals("mntiadmin")) {
                                xpp.next();
                                if (item != null) item.setAdmin(xpp.getText());
                            } else if (tagName.equals("mntilistno")) {
                                xpp.next();
                                if (item != null) item.setListNo(xpp.getText());
                            } else if (tagName.equals("mntiadminnum")) {
                                xpp.next();
                                if (item != null) item.setAdminNum(xpp.getText());
                            }
                            break;

                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tagName = xpp.getName();
                            if (tagName.equals("item")) {
                                items.add(item);
                                item=null;

                                publishProgress();
                            }
                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (IOException e){e.printStackTrace();} catch (XmlPullParserException e) {e.printStackTrace();}
            return "Parsing End";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            adapter.notifyItemInserted(items.size());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            refreshLayout.setRefreshing(false);

            //adapter.notifyDataSetChanged();

            Toast.makeText(MainActivity.this,s+":"+items.size(), Toast.LENGTH_SHORT).show();
        }
    }

    // 검색을 위해서 추가됨
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem search = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Search for mountain");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void filter(String text){
        ArrayList<Item> filteredList = new ArrayList<>();

        for (Item item: items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "데이터가 없습니다..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredList);
        }
    }
}