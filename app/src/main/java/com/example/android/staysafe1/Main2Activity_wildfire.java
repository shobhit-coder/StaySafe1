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

public class Main2Activity_wildfire extends AppCompatActivity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_wildfire);

        expListView = (ExpandableListView) findViewById(R.id.lwild);

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
        top250.add("Sign up for your community’s warning system. The Emergency Alert System (EAS) and National Oceanic and Atmospheric Administration (NOAA) Weather Radio also provide emergency alerts.");
        top250.add("Know your community’s evacuation plans and find several ways to leave the area. Drive the evacuation routes and find shelter locations. Have a plan for pets and livestock.\n");
        top250.add("Gather emergency supplies, including N95 respirator masks that filter out particles in the air you breathe. Keep in mind each person’s specific needs, including and updated asthma action plan and medication. Don’t forget the needs of pets.");
        top250.add("Designate a room that can be closed off from outside air. Close all doors and windows. Set up a portable air cleaner to keep indoor pollution levels low when smoky conditions exist.\n");
        top250.add("Keep important documents in a fireproof, safe place. Create password-protected digital copies.");
        top250.add("Use fire-resistant materials to build, renovate, or make repairs.\n");
        top250.add("Use fire-resistant materials to build, renovate, or make repairs.\n");
        top250.add("Create a fire-resistant zone that is free of leaves, debris, or flammable materials for at least 30 feet from your home.\n");
        top250.add("Review insurance coverage to make sure it is enough to replace your property.\n");
        top250.add("Pay attention to air quality alerts.\n");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Evacuate immediately if authorities tell you to do so.\n");
        nowShowing.add("If trapped, then call 911 and give your location, but be aware that emergency response could be delayed or impossible. Turn on lights to help rescuers find you.\n");
        nowShowing.add("Listen to EAS, NOAA Weather Radio, or local alerting systems for current emergency information and instructions.\n");
        nowShowing.add("Use an N95 masks to keep harmful particles out of the air you breathe.\n");
        nowShowing.add("If you are not ordered to evacuate but smoky conditions exist, stay inside in a safe location or go to a community building where smoke levels are lower.\n");



        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Listen to authorities to find out when it is safe to return, and whether water is safe to drink.");
        comingSoon.add("Avoid hot ash, charred trees, smoldering debris, and live embers. The ground may contain heat pockets that can burn you or spark another fire. Consider the danger to pets and livestock.");
        comingSoon.add("Send text messages or use social media to reach out to family and friends. Phone systems are often busy following a disaster. Make calls only in emergencies.\n");
        comingSoon.add("Wear a NIOSH certified-respirator dust mask and wet debris down to minimize breathing dust particles.\n");
        comingSoon.add("Document property damage with photographs. Conduct an inventory and contact your insurance company for assistance.\n");
        comingSoon.add("Wildfires dramatically change landscape and ground conditions, which can lead to increased risk of flooding due to heavy rains, flash flooding and mudflows. Flood risk remains significantly higher until vegetation is restored—up to 5 years after a wildfire. Consider purchasing flood insurance to protect the life you've built and to assure financial protection from future flooding.\n");



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