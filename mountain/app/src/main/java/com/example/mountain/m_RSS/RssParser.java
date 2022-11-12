package com.example.mountain.m_RSS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mountain.m_DataObject.Item;
import com.example.mountain.m_UI.MyAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RssParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    InputStream is;
    RecyclerView rv;

    ProgressDialog pd;
    ArrayList<Item> items = new ArrayList<>();

    public RssParser(Context c, InputStream is, RecyclerView rv) {
        this.c = c;
        this.is = is;
        this.rv = rv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd= new ProgressDialog(c);
        pd.setTitle("Parse Mountains");
        pd.setMessage("Parsing.. Please Wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseRss();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();
        if (isParsed) {

            rv.setAdapter(new MyAdapter(items, c));

        } else {
            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseRss() {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(is, "utf-8");
            int event = parser.getEventType();

            String tagValue = null;
            Boolean isSiteMeta = true;

            items.clear();
            Item mountain = new Item();

            do {

                String tagName = parser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")){
                            mountain = new Item();
                            isSiteMeta = false;
                        }

                        break;

                    case XmlPullParser.TEXT:
                        tagValue = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if (!isSiteMeta){
                            if (tagName.equalsIgnoreCase("imgfilename")) {
                                mountain.setImgUrl(tagValue);
                            } else if (tagName.equalsIgnoreCase("mntninfopoflc")) {
                                mountain.setAddress(tagValue);
                            } else if (tagName.equalsIgnoreCase("mntninfohght")) {
                                mountain.setHeight(tagValue);
                            } else if (tagName.equalsIgnoreCase("mntninfodtlinfocont")) {
                                mountain.setInfo(tagValue);
                            } else if (tagName.equalsIgnoreCase("ptmntrcmmncoursdscrt")) {
                                mountain.setTraffic(tagValue);
                            } else if (tagName.equalsIgnoreCase("crcmrsghtnginfodscrt")) {
                                mountain.setSurround(tagValue);
                            } else if (tagName.equalsIgnoreCase("crcmrsghtngetcimageseq")) {
                                mountain.setSurroundImg(tagValue);
                            } else if (tagName.equalsIgnoreCase("mntnattchimageseq")) {
                                mountain.setImgUrl(tagValue);
                            }
                        }

                        if (tagName.equalsIgnoreCase("item")) {
                            items.add(mountain);
                            isSiteMeta = true;
                        }
                        break;
                }
                event = parser.next();
            } while (event != XmlPullParser.END_DOCUMENT);

            return true;

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
