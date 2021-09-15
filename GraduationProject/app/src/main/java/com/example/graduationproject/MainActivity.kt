package com.example.graduationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBar
import android.widget.Toast

import androidx.annotation.NonNull
import android.content.Intent
import android.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this@MainActivity, SecondActivity::class.java)) // action bar back intent

        val curId = item.itemId
        when (curId) {
            R.id.itemSetting -> Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_SHORT).show()
            R.id.qna -> Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
            R.id.action_search -> Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT)
                .show()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}