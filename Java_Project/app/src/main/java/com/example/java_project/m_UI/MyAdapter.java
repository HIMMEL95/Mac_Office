package com.example.java_project.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.java_project.R;
import com.example.java_project.m_DataObject.Mountain;
import com.example.java_project.m_DetailActivity.DetailActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<Mountain> mountains;

    public MyAdapter(Context c, ArrayList<Mountain> mountains) {
        this.c = c;
        this.mountains = mountains;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Mountain mountain = mountains.get(position);

        holder.nameTxt.setText(mountain.getName());
        holder.addressTxt.setText(mountain.getAddress());
        holder.heightTxt.setText(mountain.getHeight());

    }

    @Override
    public int getItemCount() {
        return mountains.size();
    }

    private void openDetailActivity(String... details) {
        Intent i = new Intent(c, DetailActivity.class);

        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("ADDRESS_KEY", details[1]);
        i.putExtra("HEIGHT_KEY", details[2]);
        i.putExtra("INFO_KEY", details[3]);
        i.putExtra("ADMIN_KEY", details[4]);
    }
}
