//Ethan McDermott
//Student ID: s2130814

package org.me.gcu.labstuff.mcdermott_ethan_mpd_cw;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Description extends Fragment {

TextView descriptionTitle;
TextView descriptionDetails;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle descriptionBundle = this.getArguments();
        //if(descriptionBundle != null) {

            String titleKey = descriptionBundle.getString("titleString");
            String descriptionKey = descriptionBundle.getString("descriptionString");

            descriptionTitle = view.findViewById(R.id.descriptionRoadInfo);
            descriptionDetails = view.findViewById(R.id.descriptionDetailInfo);

            descriptionTitle.setText(titleKey);
            descriptionDetails.setText(descriptionKey);

            System.out.println(titleKey + descriptionKey);
        //}

    }

}
