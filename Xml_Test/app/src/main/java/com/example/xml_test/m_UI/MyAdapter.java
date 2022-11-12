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

        vh.mtnName.setText(item.getMtnName());
        vh.mtnAddress.setText(item.getMtnAddress());
        vh.mtnHeight.setText(item.getMtnHeight() + " m");

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

            //리사이클뷰의 아이템뷰를 클릭했을 때
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = items.get(getLayoutPosition()).getMtnName();
                    String address = items.get(getLayoutPosition()).getMtnAddress();
                    String height = items.get(getLayoutPosition()).getMtnHeight();
                    String info = items.get(getLayoutPosition()).getMtnInfo();
                    String admin = items.get(getLayoutPosition()).getMtnAdmin();
                    String adminNum = items.get(getLayoutPosition()).getMtnAdminNum();
                    String listNo = items.get(getLayoutPosition()).getMtnListNo();
                    String top = items.get(getLayoutPosition()).getMtnTop();

                    //웹튜를 가진 새로운 액티비티
                    Intent intent= new Intent(context, ItemActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("address", address);
                    intent.putExtra("height", height + " m");
                    intent.putExtra("info", info);
                    intent.putExtra("admin", admin);
                    intent.putExtra("adminNum", adminNum);
                    intent.putExtra("listNo", listNo);
                    intent.putExtra("top", top);
                    /*intent.putExtra("imgName", imgName);
                    intent.putExtra("imgFileName", imgFileName);
                    intent.putExtra("imgNo", imgNo);*/
                    context.startActivity(intent);
                }
            });
        }
    }

    // add for search
    public void filterList(ArrayList<Item> filterllist) {
        items = filterllist;
        notifyDataSetChanged();
    }
}
