package com.example.graduation_java.m_UI;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.graduation_java.R;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt, addressTxt, heightTxt;
    ItemClickListener itemClickListener;

    public MyViewHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView)itemView.findViewById(R.id.tv_title);
        addressTxt = (TextView)itemView.findViewById(R.id.tv_addr);
        heightTxt = (TextView)itemView.findViewById(R.id.tv_hgt);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
