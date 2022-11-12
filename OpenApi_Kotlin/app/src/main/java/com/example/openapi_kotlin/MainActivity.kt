package com.example.openapi_kotlin

import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.openapi_kotlin.m_DataObject.Item
import com.example.openapi_kotlin.m_UI.MyAdapter
import org.w3c.dom.Element
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var items: ArrayList<Item> = ArrayList<Item>()
    var adapter: MyAdapter? = null
    var refreshLayout: SwipeRefreshLayout? = null

    // add for search
    var arrayAdapter: ArrayAdapter<String?>? = null
    var data: MutableList<ArrayList<Item>?>? = null

    val urlAddress =
        "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&numOfRows=5000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler)
        adapter = MyAdapter(items, this)
        with(recyclerView) { setAdapter(adapter) }

        // add for search
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        data = ArrayList<ArrayList<Item>?>()
        arrayAdapter = ArrayAdapter<Any?>(this, R.layout.support_simple_spinner_dropdown_item, data)
        data.add(items)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(layoutManager)
        readRss()
        refreshLayout = findViewById(R.id.layout_swipe)
        refreshLayout.setOnRefreshListener(OnRefreshListener {
            items.clear()
            adapter.notifyDataSetChanged()
            readRss()
        })
    }

    fun readRss() {
        try {
            val url =
                URL("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&numOfRows=5000")
            val task = RssFeedTask()
            task.execute(url)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

    internal class RssFeedTask : AsyncTask<URL?, Void?, String>() {
        protected override fun doInBackground(vararg urls: URL): String {
            val url = urls[0]
            try {
                val `is` = url.openStream()
                val factory = XmlPullParserFactory.newInstance()
                val xpp = factory.newPullParser()
                xpp.setInput(`is`, "utf-8")
                var eventType = xpp.eventType
                var item: Item? = null
                var tagName: String? = null
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_DOCUMENT -> {}
                        XmlPullParser.START_TAG -> {
                            tagName = xpp.name
                            if (tagName == "item") {
                                item = Item()
                            } else if (tagName == "mntiname") {
                                xpp.next()
                                if (item != null) item.setName(xpp.text)
                            } else if (tagName == "mntiadd") {
                                xpp.next()
                                if (item != null) item.setAddress(xpp.text)
                            } else if (tagName == "mntihigh") {
                                xpp.next()
                                if (item != null) item.setHeight(xpp.text)
                            } else if (tagName == "mntidetails") {
                                xpp.next()
                                if (item != null) item.setInfo(xpp.text)
                            } else if (tagName == "mntiadmin") {
                                xpp.next()
                                if (item != null) item.setAdmin(xpp.text)
                            }
                        }
                        XmlPullParser.TEXT -> {}
                        XmlPullParser.END_TAG -> {
                            tagName = xpp.name
                            if (tagName == "item") {
                                items.add(item)
                                item = null
                                publishProgress()
                            }
                        }
                    }
                    eventType = xpp.next()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: XmlPullParserException) {
                e.printStackTrace()
            }
            return "Parsing End"
        }

        protected override fun onProgressUpdate(vararg values: Void) {
            super.onProgressUpdate(*values)
            adapter.notifyItemInserted(items.size)
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            refreshLayout.setRefreshing(false)

            //adapter.notifyDataSetChanged();
            val items = null
            Toast.makeText(this@MainActivity, s + ":" + items.size, Toast.LENGTH_SHORT).show()
        }
    }

    // 검색을 위해서 추가됨
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search for mountain"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<Item> = ArrayList<Item>()
        for (item in items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "데이터가 없습니다..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredList)
        }
    }
}