//Ethan McDermott
//Student ID: s2130814

package org.me.gcu.labstuff.mcdermott_ethan_mpd_cw;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssXMLPullParser implements Runnable{

    public String tempTitle = "";
    public String tempDescription = "";
    ArrayList<RssItem> itemsList = new ArrayList<>();
    String runnableInput;

    public RssXMLPullParser(String runnableUrl) {

        runnableInput = runnableUrl;

    }

    public ArrayList<RssItem> getItems(){

        return itemsList;

    }

    @Override
    public void run() {

        try {
            URL rssurl = new URL(runnableInput);
            HttpURLConnection httpURLConnection = (HttpURLConnection) rssurl.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(inputStream, null);

            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                eventType = xmlPullParser.next();

                if (eventType == XmlPullParser.START_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {
                    eventType = xmlPullParser.next();

                    while (eventType != XmlPullParser.END_TAG && xmlPullParser.getName() != "item") {
                        eventType = xmlPullParser.next();

                        if (eventType == XmlPullParser.START_TAG) {
                            if (xmlPullParser.getName().equalsIgnoreCase("title")) {
                                this.tempTitle = xmlPullParser.nextText().trim();
                            } else if (xmlPullParser.getName().equalsIgnoreCase("description")) {
                                this.tempDescription = xmlPullParser.nextText().trim();
                            }

                        }

                    }
                    itemsList.add(new RssItem(this.tempTitle, this.tempDescription));
                }
            }
        } catch (XmlPullParserException e) {
            Log.e("e", "XmlPullParserException error.");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("e", "IOException error.");
            e.printStackTrace();
        }

    }
}
