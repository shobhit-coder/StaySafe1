package com.example.android.staysafe1;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    String message="";

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map,container,false);
        Intent i=getActivity().getIntent();
        final String loginPhoneNumber = i.getStringExtra("phno");
        final String bloodgroup = i.getStringExtra("bloodgroup");
        final String nam = i.getStringExtra("name");
        final String lat1 = i.getStringExtra("lat");
        final String lon1 = i.getStringExtra("lon");
        Log.v("valueoffinal",loginPhoneNumber+bloodgroup+nam+lat1+lon1);
        Button todo = (Button)v.findViewById(R.id.todo_b);

        message=getActivity().getIntent().getStringExtra("message");



        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),TodoActivity.class);
//                String s="",bg="",nam="";
//                Log.v("valueofnum1",num1);
//                if(num1==""){
//                    s= sp.getString("number","DEFAULT");
//                }
//                else{
//                    s=num1;
//                    bg=bloodgroup;
//                    nam=name1;
//                }
//                Log.v("numberis",s);
//                i.putExtra("phno",s);
//                i.putExtra("name",nam);
//                i.putExtra("bloodgroup",bg);
                startActivity(i);
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton)v.findViewById(R.id.floatingActionButton_unsafe);
//        Button button1 = (Button) v.findViewById(R.id.send_b_unsafe);
        fab2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//                Log.v("cmis",cm.toString());
                if (isInternetAvailable()) {
                    CheckUpdateQuery checkUpdateQuery = new CheckUpdateQuery(loginPhoneNumber, 0.00, 0.00, "n");// this is the Asynctask, which is used to process in background to reduce load on app process
                    checkUpdateQuery.execute("");
                } else {
                    Log.d("smstrying", "smstrying");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "+919220592205"));
                    intent.putExtra("sms_body", "JPE93 -1,-1,n");
                    startActivity(intent);
                }
            }

        });
        FloatingActionButton fab1 = (FloatingActionButton)v.findViewById(R.id.floatingActionButton_safe);
//        Button button = (Button) v.findViewById(R.id.send_b_safe);
        fab1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

//                ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//                Log.v("cmis",cm.toString());
                if (isInternetAvailable()) {
                    CheckUpdateQuery checkUpdateQuery = new CheckUpdateQuery(loginPhoneNumber, 0.00, 0.00, "y");// this is the Asynctask, which is used to process in background to reduce load on app process
                    checkUpdateQuery.execute("");
                }
                else {
                    Log.d("smstrying", "smstrying");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "+919220592205"));
                    intent.putExtra("sms_body", "JPE93 -1,-1,y");
                    startActivity(intent);
                }


//                Log.d("smstrying","smstrying");
//                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + "+919220592205"));
//                intent.putExtra( "sms_body", "JPE93 -1,-1,y" );
//                startActivity(intent);
            }
        });


        return v;
//        ViewGroup parent = (ViewGroup) super.onCreateView( inflater, container, savedInstanceState );
////        View overlay = inflater.inflate( R.layout.overlay_map, parent, false );
////
////        mIndoorSelector = (SeekBar) overlay.findViewById( R.id.indoor_level_selector );
////        mIndoorMinLevel = (TextView) overlay.findViewById( R.id.indoor_min_level );
////        mIndoorMaxLevel = (TextView) overlay.findViewById( R.id.indoor_max_level );
//
//        parent.addView( overlay );
//        return parent;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment supportMapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map1);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        map=googleMap;
        if(message!=null){
            Log.v("fcmdata",message);
        }

        LatLng point=new LatLng(12.925471,77.501349);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point).title("India");
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLng(point));
//        Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
    }


    public boolean isInternetAvailable() {

            ConnectivityManager manager =
                    (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            boolean isAvailable = false;
            if (networkInfo != null && networkInfo.isConnected()) {
                // Network is present and connected
                isAvailable = true;
            }
            if(!isAvailable)Toast.makeText(getContext(),"No Internet Connection. Sending an SMS!",Toast.LENGTH_LONG).show();
            return isAvailable;

    }

}
