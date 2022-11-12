package com.example.openapi_kotlin.m_RSS

import android.app.ProgressDialog
import android.content.Context
import android.graphics.ColorSpace.connect
import android.os.AsyncTask
import android.system.Os.connect
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection

class Downloader(var c: Context, var urlAddress: String, var rv: RecyclerView) :
    AsyncTask<Void?, Void?, Any>() {
    var pd: ProgressDialog? = null
    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(c)
        pd!!.setTitle("Fetch Latest Mountains")
        pd!!.setMessage("Fetching Data.. Please Wait")
        pd!!.show()
    }

    protected override fun doInBackground(vararg params: Void): Any {
        return downloadData()
    }

    override fun onPostExecute(data: Any) {
        super.onPostExecute(data)
        pd!!.dismiss()
        if (data.toString().startsWith("Error")) {
            Toast.makeText(c, data.toString(), Toast.LENGTH_SHORT).show()
        } else {
            // parse Data
            RssParser(c, data as InputStream, rv).execute()
        }
    }

    private fun downloadData(): Any {
        val connection: Any = Connector.connect(urlAddress)
        return if (connection.toString().startsWith("Error")) {
            connection.toString()
        } else try {
            val con = connection as HttpURLConnection
            val responseCode = con.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return BufferedInputStream(con.inputStream)
            }
            ErrorTracker.RESPONSE_ERROR.toString() + con.responseMessage
        } catch (e: IOException) {
            e.printStackTrace()
            ErrorTracker.IO_ERROR
        }
    }
}