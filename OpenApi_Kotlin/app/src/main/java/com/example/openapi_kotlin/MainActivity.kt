package com.example.openapi_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

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

        var textView = findViewById<TextView>(R.id.result)
        var button = findViewById<Button>(R.id.btn)
    }

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