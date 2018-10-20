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

public class Main2Activity_tornado extends AppCompatActivity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_tornado);

        expListView = (ExpandableListView) findViewById(R.id.ltorn);

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

        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

        // Listview on child click listener
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
//                return false;
//            }
//        });
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
        top250.add("Know your area’s tornado risk. In the U.S., the Midwest and the Southeast have a greater risk for tornadoes.");
        top250.add("Know the signs of a tornado, including a rotating, funnel-shaped cloud; an approaching cloud of debris; or a loud roar—similar to a freight train.");
        top250.add("Sign up for your community’s warning system. The Emergency Alert System (EAS) and National Oceanic and Atmospheric Administration (NOAA) Weather Radio also provide emergency alerts. If your community has sirens, then become familiar with the warning tone.");
        top250.add("Pay attention to weather reports. Meteorologists can predict when conditions might be right for a tornado.");
        top250.add("Identify and practice going to a safe shelter in the event of high winds, such as a safe room built using FEMA criteria or a storm shelter built to ICC 500 standards. ");
        top250.add("Consider constructing your own safe room that meets FEMA or ICC 500 standards.");



        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Immediately go to a safe location that you identified.");
        nowShowing.add("Take additional cover by shielding your head and neck with your arms and putting materials such as furniture and blankets around you.");
        nowShowing.add("Listen to EAS, NOAA Weather Radio, or local alerting systems for current emergency information and instructions.");
        nowShowing.add("Do not try to outrun a tornado in a vehicle.");
        nowShowing.add("If you are in a car or outdoors and cannot get to a building, cover your head and neck with your arms and cover your body with a coat or blanket, if possible.");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Keep listening to EAS, NOAA Weather Radio, and local authorities for updated information.");
        comingSoon.add("If you are trapped, cover your mouth with a cloth or mask to avoid breathing dust. Try to send a text, bang on a pipe or wall, or use a whistle instead of shouting.");
        comingSoon.add("Stay clear of fallen power lines or broken utility lines.");
        comingSoon.add("Do not enter damaged buildings until you are told that they are safe.");
        comingSoon.add("Save your phone calls for emergencies. Phone systems are often down or busy. ");
        comingSoon.add("Be careful during clean-up. Wear thick-soled shoes, long pants, and work gloves.");



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