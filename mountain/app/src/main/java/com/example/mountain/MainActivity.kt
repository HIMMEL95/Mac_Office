package com.example.mountain

import android.content.ClipData
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.mountain.databinding.ActivityMainBinding
import com.example.mountain.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    var edit: EditText? = null
    var text: TextView? = null

    // 자신의 서비스키 입력
    var key =
        "LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D"

    //String address = "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice";
    var data: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Don't Touch Part Start
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        // Don't Touch Part End

        edit = findViewById<View>(R.id.edit) as EditText
        text = findViewById<View>(R.id.result) as TextView
    }

    // Don't touch Part Start
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main , menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                TODO("Not yet implemented")
                //return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
                //return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                val toast = Toast.makeText(this, "산을 검색해주세요", Toast.LENGTH_SHORT)
                toast.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // Don't touch Part End

    fun mOnClick(v: View) {
        when (v.id) {
            R.id.btn -> Thread {
                data = getXmlData()
                runOnUiThread { text!!.text = data }
            }.start()
        }
    }

    fun getXmlData(): String {
        val buffer = StringBuffer()
        val str = edit!!.text.toString()
        val location = URLEncoder.encode(str)
        val queryUrl =
            ("http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"
                    + "?serviceKey=" + key
                    + "&mntnAdd=" + location)
        try {
            val url = URL(queryUrl)
            val `is` = url.openStream()
            val factory = XmlPullParserFactory.newInstance()
            val xpp = factory.newPullParser()
            xpp.setInput(InputStreamReader(`is`, "UTF-8"))
            var tag: String
            xpp.next()
            var eventType = xpp.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_DOCUMENT -> buffer.append("Parsing Start..\n\n")
                    XmlPullParser.START_TAG -> {
                        tag = xpp.name // Get tag name
                        if (tag == "item") ; else if (tag == "mntninfodtlinfocont") {
                            buffer.append("Detail_info : ")
                            xpp.next()
                            buffer.append(xpp.text) // Add to StringBuffer from text of "mntninfodtlinfocont" element
                            buffer.append("\n") // Add line-break
                        } else if (tag == "mntnnm") {
                            buffer.append("Mountain_Name : ")
                            xpp.next()
                            buffer.append(xpp.text) // Add to StringBuffer from text of "mntnnm" element
                            buffer.append("\n") // Add line-break
                        } else if (tag == "mntninfohght") {
                            buffer.append("Mountain_Height : ")
                            xpp.next()
                            buffer.append(xpp.text) // Add to StringBuffer from text of "mntninfohght" element
                            buffer.append("\n") // Add line-break
                        } else if (tag == "mntninfopoflc") {
                            buffer.append("Mountain_Address : ")
                            xpp.next()
                            buffer.append(xpp.text) // Add to StringBuffer from text of "mntninfopoflc" element
                            buffer.append("\n") // Add line-break
                        }
                    }
                    XmlPullParser.TEXT -> {
                    }
                    XmlPullParser.END_TAG -> {
                        tag = xpp.name // Get Tag Name
                        if (tag == "item") buffer.append("\n") // End First Search result.. line-break
                    }
                }
                eventType = xpp.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        buffer.append("Parsing Finish...\n")
        return buffer.toString() // Return String Object of StringBuffer
    }
}

