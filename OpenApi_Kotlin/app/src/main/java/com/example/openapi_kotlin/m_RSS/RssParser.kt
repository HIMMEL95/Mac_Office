package com.example.openapi_kotlin.m_RSS

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.openapi_kotlin.m_DataObject.Item
import com.example.openapi_kotlin.m_UI.MyAdapter
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class RssParser(var c: Context, var `is`: InputStream, var rv: RecyclerView) :
    AsyncTask<Void?, Void?, Boolean>() {
    var pd: ProgressDialog? = null
    var items: ArrayList<Item> = ArrayList<Item>()
    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(c)
        pd!!.setTitle("Parse Mountains")
        pd!!.setMessage("Parsing.. Please Wait")
        pd!!.show()
    }

    protected override fun doInBackground(vararg p0: Void?): Boolean? {
        return parseRss()
    }

    override fun onPostExecute(isParsed: Boolean) {
        super.onPostExecute(isParsed)
        pd!!.dismiss()
        if (isParsed) {
            rv.adapter = MyAdapter(items, c)
        } else {
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseRss(): Boolean {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(`is`, "utf-8")
            var event = parser.eventType
            var tagValue: String? = null
            var isSiteMeta = true
            items.clear()
            var mountain = Item()
            do {
                val tagName = parser.name
                when (event) {
                    XmlPullParser.START_TAG -> if (tagName.equals("item", ignoreCase = true)) {
                        mountain = Item()
                        isSiteMeta = false
                    }
                    XmlPullParser.TEXT -> tagValue = parser.text
                    XmlPullParser.END_TAG -> {
                        if (!isSiteMeta) {
                            if (tagName.equals("mntnnm", ignoreCase = true)) {
                                mountain.setName(tagValue)
                            } else if (tagName.equals("mntninfopoflc", ignoreCase = true)) {
                                mountain.setAddress(tagValue)
                            } else if (tagName.equals("mntninfohght", ignoreCase = true)) {
                                mountain.setHeight(tagValue)
                            } else if (tagName.equals("mntnInfodtlinfocont", ignoreCase = true)) {
                                mountain.setInfo(tagValue)
                            } else if (tagName.equals("ptmntrcmmncoursdscrt", ignoreCase = true)) {
                                if (tagValue != null) {
                                    mountain.setTraffic(tagValue)
                                }
                            } else if (tagName.equals("hndfmsmtnslctnrson", ignoreCase = true)) {
                                if (tagValue != null) {
                                    mountain.setInfoHun(tagValue)
                                }
                            } else if (tagName.equals("crcmrsghtnginfodscrt", ignoreCase = true)) {
                                if (tagValue != null) {
                                    mountain.setSurround(tagValue)
                                }
                            } else if (tagName.equals("crcmrsghtngetcimageseq", ignoreCase = true)) {
                                if (tagValue != null) {
                                    mountain.setSurroundImg(tagValue)
                                }
                            }
                        }
                        if (tagName.equals("item", ignoreCase = true)) {
                            items.add(mountain)
                            isSiteMeta = true
                        }
                    }
                }
                event = parser.next()
            } while (event != XmlPullParser.END_DOCUMENT)
            return true
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }
}