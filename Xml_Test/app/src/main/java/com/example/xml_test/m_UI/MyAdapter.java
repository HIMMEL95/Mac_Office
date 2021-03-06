package com.example.xml_test.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xml_test.R;
import com.example.xml_test.m_DataObject.Item;
import com.example.xml_test.m_ItemActivity.ItemActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    ArrayList<Item> items;
    Context context;

    public MyAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);

        VH vh = new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh = (VH)holder;

        Item item = items.get(position);

        vh.mtnName.setText(item.getName());
        vh.mtnAddress.setText(item.getAddress());
        vh.mtnHeight.setText(item.getHeight() + " m");

        if (item.getImgUrl()==null){
            vh.iv.setVisibility(View.GONE);
        } else {
            vh.iv.setVisibility(View.VISIBLE);

            Glide.with(context).load(item.getImgUrl()).into(vh.iv);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView mtnName, mtnAddress, mtnHeight;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            mtnName=itemView.findViewById(R.id.tv_title);
            mtnAddress=itemView.findViewById(R.id.tv_addr);
            mtnHeight=itemView.findViewById(R.id.tv_hgt);
            iv=itemView.findViewById(R.id.iv);

            //?????????????????? ??????????????? ???????????? ???
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = items.get(getLayoutPosition()).getName();
                    String address = items.get(getLayoutPosition()).getAddress();
                    String height = items.get(getLayoutPosition()).getHeight();
                    String info = items.get(getLayoutPosition()).getInfo();
                    String admin = items.get(getLayoutPosition()).getAdmin();

                    //????????? ?????? ????????? ????????????
                    Intent intent= new Intent(context,ItemActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("address", address);
                    intent.putExtra("height", height + " m");
                    intent.putExtra("info", info);
                    intent.putExtra("admin", admin);
                    context.startActivity(intent);
                }
            });
        }
    }
}
