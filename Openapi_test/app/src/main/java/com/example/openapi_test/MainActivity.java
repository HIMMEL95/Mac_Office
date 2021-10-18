package com.example.openapi_test;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    TextView text;

    // 자신의 서비스키 입력
    String key = "LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D";
    //String address = "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice";
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText)findViewById(R.id.edit);
        text = (TextView)findViewById(R.id.result);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data = getXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    String getXmlData(){
        StringBuffer buffer = new StringBuffer();

        String str = edit.getText().toString();
        String location = URLEncoder.encode(str);

        String queryUrl = "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"
                + "?serviceKey=" + key
                + "&mntnAdd=" + location;

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("Parsing Start..\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName(); // Get tag name

                        if (tag.equals("item"));  // First search result
                        else if (tag.equals("mntninfodtlinfocont")){
                            buffer.append("Detail_info : ");
                            xpp.next();
                            buffer.append(xpp.getText()); // Add to StringBuffer from text of "mntninfodtlinfocont" element
                            buffer.append("\n");  // Add line-break
                        }
                        else if (tag.equals("mntnnm")){
                            buffer.append("Mountain_Name : ");
                            xpp.next();
                            buffer.append(xpp.getText()); // Add to StringBuffer from text of "mntnnm" element
                            buffer.append("\n");  // Add line-break
                        }
                        else if (tag.equals("mntninfohght")){
                            buffer.append("Mountain_Height : ");
                            xpp.next();
                            buffer.append(xpp.getText()); // Add to StringBuffer from text of "mntninfohght" element
                            buffer.append("\n");  // Add line-break
                        }
                        else if (tag.equals("mntninfopoflc")){
                            buffer.append("Mountain_Address : ");
                            xpp.next();
                            buffer.append(xpp.getText()); // Add to StringBuffer from text of "mntninfopoflc" element
                            buffer.append("\n");  // Add line-break
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); // Get Tag Name

                        if (tag.equals("item")) buffer.append("\n"); // End First Search result.. line-break
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        buffer.append("Parsing Finish...\n");
        return buffer.toString(); // Return String Object of StringBuffer
    }
}