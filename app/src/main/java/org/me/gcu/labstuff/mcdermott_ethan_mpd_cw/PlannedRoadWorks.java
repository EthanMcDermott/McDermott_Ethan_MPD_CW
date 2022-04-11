//Ethan McDermott
//Student ID: s2130814

package org.me.gcu.labstuff.mcdermott_ethan_mpd_cw;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlannedRoadWorks extends Fragment {


    ListView plannedRoadList;
    Fragment descriptionFrag;
    Bundle descriptionBundle;
    ArrayList<RssItem> arrayParsed = new ArrayList<>();

    public PlannedRoadWorks() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.planned_road_works_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plannedRoadList = view.findViewById(R.id.plannedRoadList);
        RssXMLPullParser rsxml = new RssXMLPullParser("https://trafficscotland.org/rss/feeds/plannedroadworks.aspx");
        Thread thread = new Thread(rsxml);
        thread.start();
        try {
            thread.join(0);
            arrayParsed = rsxml.getItems();
        }
        catch(InterruptedException ie) {
            ie.printStackTrace();
        }

        List<String> titles = new ArrayList<String>();
        for(int i = 0; i <arrayParsed.size(); i++)
        {

            titles.add(arrayParsed.get(i).getTitle());

        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, titles);
        plannedRoadList.setAdapter(arrayAdapter);

        plannedRoadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                descriptionFrag = new Description();
                descriptionBundle = new Bundle();

                descriptionBundle.putString("titleString", arrayParsed.get(i).getTitle());
                descriptionBundle.putString("descriptionString", arrayParsed.get(i).getDescription().replace("<br />", "\n"));
                descriptionFrag.setArguments(descriptionBundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragFrame, descriptionFrag).commit();


            }
        });

    }

}
