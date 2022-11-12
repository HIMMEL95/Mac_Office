package com.example.openapi_kotlin.m_ItemActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.openapi_kotlin.R

class ItemActivity : AppCompatActivity() {

    var nameTxt: TextView? = null
    var addressTxt:TextView? = null
    var heightTxt:TextView? = null
    var infoTxt:TextView? = null
    var trafficTxt:TextView? = null
    var surround:TextView? = null
    var mtnImg:ImageView? = null
    var surroundImg: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        nameTxt = findViewById<View>(R.id.tv_title) as TextView
        addressTxt = findViewById<View>(R.id.tv_addr) as TextView
        heightTxt = findViewById<View>(R.id.tv_hgt) as TextView
        infoTxt = findViewById<View>(R.id.info) as TextView
        trafficTxt = findViewById<View>(R.id.traffic) as TextView
        surround = findViewById<View>(R.id.surround) as TextView
        mtnImg = findViewById<View>(R.id.iv) as ImageView
        surroundImg = findViewById<View>(R.id.surround_img) as ImageView


        val i = this.intent

        val name = i.extras!!.getString("name")
        val address = i.extras!!.getString("address")
        val height = i.extras!!.getString("height")
        val info = i.extras!!.getString("info")
        val traffic = i.extras!!.getString("traffic")
        val surround = i.extras!!.getString("surround")
        val mtnImg = i.extras!!.getString("mtnImg")
        val surroundImg = i.extras!!.getString("surroundImg")

        nameTxt!!.text = name
        addressTxt!!.text = address
        heightTxt!!.text = height
        infoTxt!!.text = info
        traffic!!.text = traffic
        surround!!.text = surround
        mtnImg!!.text = mtnImg
        surroundImg!!.text = surroundImg

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}