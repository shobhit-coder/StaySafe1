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

public class Main2Activity_landslide extends AppCompatActivity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_landslide);

        expListView = (ExpandableListView) findViewById(R.id.lland);

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

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Build an emergency kit.");
        nowShowing.add("Make a family communications plan.");

        List<String> top250 = new ArrayList<String>();
        top250.add("Stay alert and awake. Many people die from landslides when they are sleeping.");
        top250.add("Listen for unusual sounds like trees cracking or boulders knocking together. If you hear something, tell an adult immediately!");
        top250.add("Move away from the path of a landslide or debris flow as fast as you can.");
        top250.add("Avoid river valleys and low-lying areas.");
        top250.add("If you can’t escape, curl into a tight ball and cover your head with your hands and arms.");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Stay away from the slide area. There may be additional slides.");
        comingSoon.add("If you see dangling or loose wires, stay away and tell an adult.\n");
        comingSoon.add("Listen to safety officials about where it’s safe to go.\n");
        comingSoon.add("Text, don’t talk. Unless there’s a life-threatening  situation, send a text so that you don’t tie up phone lines needed by emergency workers. Plus, texting may work even if cell service is down.\n");

        listDataChild.put(listDataHeader.get(0), nowShowing ); // Header, Child data
        listDataChild.put(listDataHeader.get(1), top250);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}