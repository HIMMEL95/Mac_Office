package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter : BaseAdapter() {

    var list = ArrayList<ListViewItem>()

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    fun getSize(): Int {
        return list.size
    }

    fun addItem(mountainName: String, mountainAddress: String, mountainHeight: String) {
        var item = ListViewItem(mountainName, mountainAddress, mountainHeight)
        list.add(item)

        println("in ListViewAdapter --> listSize : " + list.size)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var context = parent?.context
        var convertV = convertView

        if (convertView == null) {
            val systemService = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertV = systemService.inflate(R.layout.listview, parent, false)
        }

        var mountainName = convertV?.findViewById<TextView>(R.id.mountainTxt)
        var mountainAddress = convertV?.findViewById<TextView>(R.id.addressTxt)
        var mountainHeight = convertV?.findViewById<TextView>(R.id.heightTxt)

        val listViewItem = list[position]

        mountainName?.text  = listViewItem.getMountainName()
        mountainAddress?.text = listViewItem.getMountainAddress()
        mountainHeight?.text = listViewItem.getMountainHeight()

        return convertV
    }
}