package com.example.mountain.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mountain.R;
import com.example.mountain.m_DataObject.Item;
import com.example.mountain.m_DataObject.Mountain;
import com.example.mountain.m_ItemActivity.ItemActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    ArrayList<Item> items;
    Context context;
    ArrayList<Mountain> mountains;

    public MyAdapter(ArrayList<Item> items,Context context) {
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

            Glide.with(context).load("https://www.forest.go.kr/images/data/down/mountain/"+item.getImgUrl()).into(vh.iv);
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

            mtnName=itemView.findViewById(R.id.name);
            mtnAddress=itemView.findViewById(R.id.address);
            mtnHeight=itemView.findViewById(R.id.height);
            iv=itemView.findViewById(R.id.iv);

            //리사이클뷰의 아이템뷰를 클릭했을 때
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = items.get(getLayoutPosition()).getName();
                    String address = items.get(getLayoutPosition()).getAddress();
                    String height = items.get(getLayoutPosition()).getHeight();
                    String info = items.get(getLayoutPosition()).getInfo();
                    String img = items.get(getLayoutPosition()).getImgUrl();
                    String admin = items.get(getLayoutPosition()).getAdmin();
                    String adminNum = items.get(getLayoutPosition()).getAdminNum();
                    String listNo = items.get(getLayoutPosition()).getListNo();
                    String imgName = mountains.get(getLayoutPosition()).getImgName();
                    String imgFileName = mountains.get(getLayoutPosition()).getImgFileName();


                    //웹튜를 가진 새로운 액티비티
                    Intent intent= new Intent(context, ItemActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("address", address);
                    intent.putExtra("height", height + " m");
                    intent.putExtra("info", info);
                    intent.putExtra("img", img);
                    intent.putExtra("admin", admin);
                    intent.putExtra("adminNum", adminNum);
                    intent.putExtra("listNo", listNo);
                    intent.putExtra("imgName", imgName);
                    intent.putExtra("imgFileName", imgFileName);
                    context.startActivity(intent);

                    try {

                        URL url = new URL("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoImgOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&mntiListNo="+listNo);

                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        XmlPullParser xpp = factory.newPullParser();

                        InputStream is = url.openStream();
                        xpp.setInput(is, "utf-8");
                        int eventType = xpp.getEventType();

                        Mountain mountain = null;
                        String tagName = null;

                        while (eventType != XmlPullParser.END_DOCUMENT) {
                            switch (eventType) {
                                case XmlPullParser.START_DOCUMENT:
                                    break;
                                case XmlPullParser.START_TAG:
                                    tagName = xpp.getName();

                                    if (tagName.equals("item")) {
                                        mountain = new Mountain();
                                    } else if (tagName.equals("imgname")) {
                                        xpp.next();
                                        if (mountain != null) mountain.setImgName(xpp.getText());
                                    } else if (tagName.equals("imgfilename")) {
                                        xpp.next();
                                        if (mountain != null) mountain.setImgFileName(xpp.getText());
                                    }
                                    break;

                                case XmlPullParser.TEXT:
                                    break;
                                case XmlPullParser.END_TAG:
                                    tagName = xpp.getName();
                                    if (tagName.equals("item")) {
                                        mountains.add(mountain);
                                        mountains=null;
                                    }
                                    break;
                            }
                            eventType = xpp.next();
                        }
                    } catch (IOException e){e.printStackTrace();}  catch (XmlPullParserException e){e.printStackTrace();}
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
