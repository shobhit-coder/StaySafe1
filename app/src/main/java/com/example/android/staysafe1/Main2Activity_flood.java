package com.example.android.staysafe1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity_flood extends AppCompatActivity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_flood);

        expListView = (ExpandableListView) findViewById(R.id.lfloo);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter1(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);



        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Prepare NOW");
        listDataHeader.add("Survive DURING");
        listDataHeader.add("Be Safe AFTER");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Know types of flood risk in your area. Visit FEMA’s Flood Map Service Center for information.");
        top250.add("Sign up for your community’s warning system. The Emergency Alert System (EAS) and National Oceanic and Atmospheric Administration (NOAA) Weather Radio also provide emergency alerts.");
        top250.add("If flash flooding is a risk in your location, then monitor potential signs, such as heavy rain.");
        top250.add("Learn and practice evacuation routes, shelter plans, and flash flood response.");

        top250.add("Gather supplies in case you have to leave immediately, or if services are cut off. Keep in mind each person’s specific needs, including medication. Don’t forget the needs of pets. Obtain extra batteries and charging devices for phones and other critical equipment.");
        top250.add("Purchase or renew a flood insurance policy. It typically takes up to 30 days for a policy to go into effect and can protect the life you've built. Homeowner’s policies do not cover flooding. Get flood coverage under the National Flood Insurance Program (NFIP)");
        top250.add("Protect your property. Move valuables to higher levels. Declutter drains and gutters. Install check valves. Consider a sump pump with a battery.");
        top250.add("Keep important documents in a waterproof container. Create password-protected digital copies.");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Depending on where you are, and the impact and the warning time of flooding, go to the safe location that you previously identified.");
        nowShowing.add("If told to evacuate, do so immediately. Never drive around barricades. Local responders use them to safely direct traffic out of flooded areas.");
        nowShowing.add("Listen to EAS, NOAA Weather Radio, or local alerting systems for current emergency information and instructions.");
        nowShowing.add("Do not walk, swim, or drive through flood waters. Turn Around. Don’t Drown!");
        nowShowing.add("Stay off bridges over fast-moving water. Fast-moving water can wash bridges away without warning.");
        nowShowing.add("If your vehicle is trapped in rapidly moving water, then stay inside. If water is rising inside the vehicle, then seek refuge on the roof.");
        nowShowing.add("If trapped in a building, then go to its highest level. Do not climb into a closed attic. You may become trapped by rising floodwater. Go on the roof only if necessary. Once there, signal for help.");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Listen to authorities for information and instructions. Return home only when authorities say it is safe.");
        comingSoon.add("Avoid driving, except in emergencies.");
        comingSoon.add("Snakes and other animals may be in your house. Wear heavy gloves and boots during clean up.");
        comingSoon.add("Be aware of the risk of electrocution. Do not touch electrical equipment if it is wet or if you are standing in water. If it is safe to do so, turn off the electricity to prevent electric shock.");
        comingSoon.add("Avoid wading in floodwater, which can contain dangerous debris and be contaminated. Underground or downed power lines can also electrically charge the water.");
        comingSoon.add("Use a generator or other gasoline-powered machinery ONLY outdoors and away from windows.");


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}
/*public class Main2Activity_earthquake extends AppCompatActivity {

    TextView descText2, descText3,descText4;
    ImageButton plus2, minus2,plus3, minus3,plus4, minus4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_earthquake);
        descText2 = (TextView) findViewById(R.id.description_texte2);
        descText3 = (TextView) findViewById(R.id.description_texte3);
        descText4 = (TextView) findViewById(R.id.description_texte4);


        minus2 = (ImageButton) findViewById(R.id.minuse2);
        plus2 = (ImageButton) findViewById(R.id.pluse2);

        minus3 = (ImageButton) findViewById(R.id.minuse3);
        plus3 = (ImageButton) findViewById(R.id.pluse3);

        minus4 = (ImageButton) findViewById(R.id.minuse4);
        plus4 = (ImageButton) findViewById(R.id.pluse4);

        minus2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus2.setVisibility(v.GONE);
                plus2.setVisibility(v.VISIBLE);
//                descText2.setMaxLines(0);
                descText2.setVisibility(View.GONE);


            }
        });
        plus2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus2.setVisibility(v.GONE);
                minus2.setVisibility(v.VISIBLE);
//                descText2.setMaxLines(15);
                descText2.setVisibility(View.VISIBLE);
//                descText3.setVisibility(View.GONE);
//                descText4.setVisibility(View.GONE);

            }
        });

        minus3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus3.setVisibility(v.GONE);
                plus3.setVisibility(v.VISIBLE);
//                descText3.setMaxLines(0);
                descText3.setVisibility(View.GONE);

            }
        });
        plus3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus3.setVisibility(v.GONE);
                minus3.setVisibility(v.VISIBLE);
//                descText3.setMaxLines(15);
                descText3.setVisibility(View.VISIBLE);
//                descText4.setVisibility(View.GONE);
//                descText2.setVisibility(View.GONE);

            }
        });

        minus4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus4.setVisibility(v.GONE);
                plus4.setVisibility(v.VISIBLE);
//                descText4.setMaxLines(0);
                descText4.setVisibility(View.GONE);

            }
        });
        plus4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus4.setVisibility(v.GONE);
                minus4.setVisibility(v.VISIBLE);
//                descText4.setMaxLines(15);
                descText4.setVisibility(View.VISIBLE);
//                descText2.setVisibility(View.GONE);
//                descText3.setVisibility(View.GONE);

            }
        });

    }
}


*/