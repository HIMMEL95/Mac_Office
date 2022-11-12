package com.example.openapi_kotlin.m_UI

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openapi_kotlin.R

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var nameTxt: TextView
    var addressTxt: TextView
    var heightTxt: TextView
    private var itemClickListener: ItemClickListener? = null
    fun setItemClickListener(itemClickListener: ItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(view: View) {
        itemClickListener!!.onItemClick(this.layoutPosition)
    }

    init {
        nameTxt = itemView.findViewById<View>(R.id.tv_title) as TextView
        addressTxt = itemView.findViewById<View>(R.id.tv_addr) as TextView
        heightTxt = itemView.findViewById<View>(R.id.tv_hgt) as TextView
        itemView.setOnClickListener(this)
    }
}