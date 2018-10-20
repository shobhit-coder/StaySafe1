package com.example.android.staysafe1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity_earthquake extends AppCompatActivity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_earthquake);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

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
        top250.add("Look around places where you spend time.  Identify safe places such as under a sturdy piece of furniture or against an interior wall in your home, office or school so that when the shaking starts, you Drop to the ground, Cover your head and neck with your arms, and if a safer place is nearby, crawl to it and Hold On.");
        top250.add("Practice how to “Drop, Cover, and Hold On!");
        top250.add("To react quickly you must practice often. You may only have seconds to protect yourself in an earthquake.");
        top250.add("Before an earthquake occurs, secure items that could fall and cause injuries (e.g., bookshelves, mirrors, light fixtures)");
        top250.add("Store critical supplies (e.g., water, medication) and documents");
        top250.add("Plan how you will communicate with family members, including multiple methods by making a family emergency communication plan");
        top250.add("When choosing your home or business, check if the building is earthquake resistant per local building codes");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Think of the three things to do: DROP, COVER and HOLD ON! (AGACHESE, CUBRASE, AGARRESE!) If you are indoors, stay there until the shaking has stopped");
        nowShowing.add("Stay away from glass, windows, outside doors and walls, and anything that could fall such as light fixtures or furniture");
        nowShowing.add("Stay away from doorways unless you know it is a load-bearing doorway and is close to you");
        nowShowing.add("In the Kitchen: Move away from the refrigerator, stove and overhead cupboards");
        nowShowing.add("In High-Rise Buildings: Stay near an inside wall. Do not use the elevators");
        nowShowing.add("In a stadium or theater: Stay in your seat and protect your head with your arms. DO NOT try to leave until the shaking is over");
        nowShowing.add("Outdoors:\nMove to a clear area, away from trees, signs, buildings or downed electrical wires and poles");
        nowShowing.add("On the Street:\nDuck into a doorway to protect yourself from falling bricks, glass, plaster and other debris");
        nowShowing.add("In the Car:\nPull over to the side of the road and stop. Stay away from overpasses, power lines and other dangers. STAY INSIDE THE VEHICLE UNTIL THE SHAKING IS OVER");
        nowShowing.add("In a Store:\nDo not run for the exits. STAY CALM. Move away from anything that might fall");
        nowShowing.add("In the Mountains:\nWatch out for falling rock, landslides, trees and other debris that could be loosened by quakes");



        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("When the shaking stops, look around. If there is a clear path to safety, leave the building and go to an open space away from damaged areas");
        comingSoon.add("If you are trapped, do not move about or kick up dust");
        comingSoon.add("If you have a cell phone with you, use it to call or text for help");
        comingSoon.add("Tap on a pipe or wall or use a whistle, if you have one, so that rescuers can locate you");
        comingSoon.add("Once safe, monitor local news reports via battery operated radio, TV, social media, and cell phone text alerts for emergency information and instructions");
        comingSoon.add("Be prepared to “Drop, Cover, and Hold on” in the likely event of aftershocks");
        comingSoon.add("Do not go into buildings with damage or those that have fallen down");
        comingSoon.add("Stay away from electrical wiring, both indoors and out");
        comingSoon.add("Be aware of possible tsunamis in coastal areas. When local authorities give a tsunami warning, assume that a series of dangerous waves is on the way. Stay away from the beach");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}