package com.example.openapi_kotlin.m_UI

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openapi_kotlin.R
import com.example.openapi_kotlin.m_DataObject.Item
import com.example.openapi_kotlin.m_ItemActivity.ItemActivity

abstract class MyAdapter(items: ArrayList<Item>, context: Context) :
    RecyclerView.Adapter<MyAdapter.VH>() {
    var items: ArrayList<Item>
    var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val vh = holder as VH
        val item: Item = items[position]
        vh.mtnName.setText(item.getName())
        vh.mtnAddress.setText(item.getAddress())
        vh.mtnHeight.setText(item.getHeight().toString() + " m")
        if (item.getImgUrl() == null) {
            vh.iv.visibility = View.GONE
        } else {
            vh.iv.visibility = View.VISIBLE
            Glide.with(context).load(item.getImgUrl()).into(vh.iv)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mtnName: TextView
        var mtnAddress: TextView
        var mtnHeight: TextView
        var iv: ImageView

        init {
            mtnName = itemView.findViewById(R.id.tv_title)
            mtnAddress = itemView.findViewById(R.id.tv_addr)
            mtnHeight = itemView.findViewById(R.id.tv_hgt)
            iv = itemView.findViewById(R.id.iv)

            //리사이클뷰의 아이템뷰를 클릭했을 때
            itemView.setOnClickListener {
                val name: String? = items[layoutPosition].getName()
                val address: String? = items[layoutPosition].getAddress()
                val height: String? = items[layoutPosition].getHeight()
                val info: String? = items[layoutPosition].getInfo()
                val traffic: String? = items[layoutPosition].getTraffic()
                val surround: String? = items[layoutPosition].getSurround()
                val surroundImg: String? = items[layoutPosition].getSurroundImg()
                val imgUrl: String? = items[layoutPosition].getImgUrl()

                //웹튜를 가진 새로운 액티비티
                val intent = Intent(context, ItemActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("address", address)
                intent.putExtra("height", "$height m")
                intent.putExtra("info", info)
                intent.putExtra("traffic", traffic)
                intent.putExtra("surround", surround)
                intent.putExtra("surroundImg", surroundImg)
                intent.putExtra("imgUrl", imgUrl)
                context.startActivity(intent)
            }
        }
    }

    // add for search
    fun filterList(filterllist: ArrayList<Item>) {
        items = filterllist
        notifyDataSetChanged()
    }

    init {
        this.items = items
        this.context = context
    }

}