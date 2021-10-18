package com.example.openapi_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.textView)
        var button = findViewById<Button>(R.id.btn)

        textView.text = ""

        button.setOnClickListener() {
            var thread = NetworkThread()
            thread.start()
        }
    }

    inner class NetworkThread: Thread() {
        override fun run() {
            try {
                var site = "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"
                var url = URL(site)
                var conn = url.openConnection()
                var input = conn.getInputStream()

                var factory = DocumentBuilderFactory.newInstance()
                var builder = factory.newDocumentBuilder()
                var doc = builder.parse(input)

                var root = doc.documentElement

                var item_node_list = root.getElementsByTagName("item")

                for (i in 0 until item_node_list.length) {
                    var item_element = item_node_list.item(i) as Element

                    var mntnInfodtlinfocont_list = item_element.getElementsByTagName("mntnInfodtlinfocont")
                    var mntnnm_list = item_element.getElementsByTagName("mntnnm")
                    var mntninfopoflc_list = item_element.getElementsByTagName("mntninfopoflc")
                    var mntninfohght_list = item_element.getElementsByTagName("mntninfohght")

                    var mntnInfodtlinfocont_node = mntnInfodtlinfocont_list.item(0) as Element
                    var mntnnm_node = mntnnm_list.item(0) as Element
                    var mntninfopoflc_node = mntninfopoflc_list.item(0) as Element
                    var mntninfohght_node = mntninfohght_list.item(0) as Element

                    var mntnInfodtlinfocont = mntnInfodtlinfocont_node.textContent
                    var mntnnm = mntnnm_node.textContent
                    var mntninfopoflc = mntninfopoflc_node.textContent
                    var mntninfohght = mntninfohght_node.textContent

                    runOnUiThread {
                        textView.append("상세정보내용 : ${mntnInfodtlinfocont}")
                        textView.append("산 이름 : ${mntnnm}")
                        textView.append("소재지 : ${mntninfopoflc}")
                        textView.append("산 높이 : ${mntninfohght}")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}