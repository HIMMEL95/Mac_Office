package com.example.test

import org.json.JSONObject

import java.io.InputStreamReader

import java.io.BufferedReader

import java.io.BufferedInputStream

import java.net.URL

import java.net.HttpURLConnection

import java.io.IOException

import java.net.MalformedURLException

class ApiPublicMountain(addr: String) {

    var addr: String

    init {
        this.addr = addr
    }

    var adapter: ListViewAdapter = ListViewAdapter()

    var INDENT_FACTOR = 4

    @Throws(MalformedURLException::class, IOException::class)
    fun main(args: Array<String>) {
        val conn = URL(
            "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?"
                    + "serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&numOfRows=5000"
        ).openConnection() as HttpURLConnection
        conn.connect()
        val bis = BufferedInputStream(conn.inputStream)
        val reader = BufferedReader(InputStreamReader(bis))
        val st = StringBuffer()
        var line: String?
        while ((reader.readLine().also { line = it }) != null) {
            st.append(line)
        }
        val xmlJSONObj: JSONObject = XML.toJSONObject(st.toString())
        val jsonPrettyPrintString = xmlJSONObj.toString(INDENT_FACTOR)
        println(jsonPrettyPrintString)
    }

}

