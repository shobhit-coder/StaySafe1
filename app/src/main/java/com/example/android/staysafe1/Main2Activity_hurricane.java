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

public class Main2Activity_hurricane extends AppCompatActivity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_hurricane);

        expListView = (ExpandableListView) findViewById(R.id.lhurr);

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
        top250.add("Know your area’s risk of hurricanes.");
        top250.add("Sign up for your community’s warning system. The Emergency Alert System (EAS) and National Oceanic and Atmospheric Administration (NOAA) Weather Radio also provide emergency alerts.");
        top250.add("If you are at risk for flash flooding, watch for warning signs such as heavy rain.");
        top250.add("Practice going to a safe shelter for high winds, such as a FEMA safe room or ICC 500 storm shelter. The next best protection is a small, interior, windowless room in a sturdy building on the lowest level that is not subject to flooding.");
        top250.add("Based on your location and community plans, make your own plans for evacuation or sheltering in place.");
        top250.add("Become familiar with your evacuation zone, the evacuation route, and shelter locations.");
        top250.add("Gather needed supplies for at least three days. Keep in mind each person’s specific needs, including medication. Don’t forget the needs of pets.");
        top250.add("Keep important documents in a safe place or create password-protected digital copies.");
        top250.add("Protect your property. Declutter drains and gutters. Install check valves in plumbing to prevent backups. Consider hurricane shutters. Review insurance policies.");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("If told to evacuate, do so immediately. Do not drive around barricades.");
        nowShowing.add("If sheltering during high winds, go to a FEMA safe room, ICC 500 storm shelter, or a small, interior, windowless room or hallway on the lowest floor that is not subject to flooding.");
        nowShowing.add("If trapped in a building by flooding, go to the highest level of the building. Do not climb into a closed attic. You may become trapped by rising flood water.");
        nowShowing.add("Listen for current emergency information and instructions.");
        nowShowing.add("Use a generator or other gasoline-powered machinery outdoors ONLY and away from windows.");
        nowShowing.add("Do not walk, swim, or drive through flood waters. Turn Around. Don’t Drown! Just six inches of fast-moving water can knock you down, and one foot of moving water can sweep your vehicle away.");
        nowShowing.add("Stay off of bridges over fast-moving water.");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Listen to authorities for information and special instructions.");
        comingSoon.add("Be careful during clean-up. Wear protective clothing and work with someone else.");
        comingSoon.add("Do not touch electrical equipment if it is wet or if you are standing in water. If it is safe to do so, turn off electricity at the main breaker or fuse box to prevent electric shock.");
        comingSoon.add("Avoid wading in flood water, which can contain dangerous debris. Underground or downed power lines can also electrically charge the water.");
        comingSoon.add("Save phone calls for emergencies. Phone systems are often down or busy after a disaster. Use text messages or social media to communicate with family and friends.");
        comingSoon.add("Document any property damage with photographs. Contact your insurance company for assistance.");


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