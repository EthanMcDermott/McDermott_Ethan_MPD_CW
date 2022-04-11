//Ethan McDermott
//Student ID: s2130814

package org.me.gcu.labstuff.mcdermott_ethan_mpd_cw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{ //implements View.OnClickListener

    Fragment currentIncidentsFrag; //, description, plannedRoadWorks, roadWorks
    Fragment plannedRoadWorksFrag;
    Fragment roadWorksFrag;
    Button currentIncidentsButton;
    Button roadWorksButton;
    Button plannedRoadworksButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentIncidentsButton = findViewById(R.id.currentIncidentsButton);
        plannedRoadworksButton = findViewById(R.id.plannedRoadWorksButton);
        roadWorksButton = findViewById(R.id.roadWorksButton);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        currentIncidentsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                currentIncidentsFrag(new CurrentIncidents());

            }

        });

        plannedRoadworksButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                plannedRoadWorksFrag(new PlannedRoadWorks());

            }

        });

        roadWorksButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                roadWorksFrag(new RoadWorks());

            }

        });

    }

    private void currentIncidentsFrag(Fragment fragment) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragFrame, fragment).commit();
        //fragmentTransaction.commit();

    }

    private void plannedRoadWorksFrag(Fragment fragment) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragFrame, fragment).commit();

    }

    private void roadWorksFrag(Fragment fragment) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragFrame, fragment).commit();

    }

}