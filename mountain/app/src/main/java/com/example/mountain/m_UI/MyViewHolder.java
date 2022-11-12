package com.example.mountain.m_UI;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mountain.R;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt, addressTxt, heightTxt;
    ItemClickListener itemClickListener;

    public MyViewHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView)itemView.findViewById(R.id.title);
        addressTxt = (TextView)itemView.findViewById(R.id.address);
        heightTxt = (TextView)itemView.findViewById(R.id.height);

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
