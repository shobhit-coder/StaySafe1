package com.example.android.staysafe1;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.android.staysafe1.Constants.CHANNEL_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
     String lon1="0",nam="",bloodgroup="";
     Button alert_b;
     String lat1="0";
     coord coordinates;
    int requestCode = 1;
    GoogleMap map;
    String message="";
    ArrayList<LatLng> pos;
    ArrayList<LatLng> nearbypos;


    public MapFragment() {
        // Required empty public constructor
    }
    protected LocationManager locationManager;
    MyLocationListener locationListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map,container,false);



        locationListener = new MyLocationListener();
//        retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);
        locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);

        String permission=android.Manifest.permission.ACCESS_FINE_LOCATION;

        Location lastKnownLocationGPS=null;
        coordinates=showCurrentLocation();

        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            Log.v("outerloop","outerloop");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                Log.v("innerloop","innerloop");
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
//                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
                showExplanation("Permission Needed", "Rationale", Manifest.permission.ACCESS_FINE_LOCATION, requestCode);
            } else {
                Log.v("innerloopelse","innerloopelse");
//                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
                requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, requestCode);
            }
        } else {
            Log.v("outerloopelse","innerloopelse");
            Toast.makeText(getContext(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }


        if (locationManager!=null)
            lastKnownLocationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastKnownLocationGPS!=null){
            coordinates=new coord(lastKnownLocationGPS.getLatitude(),lastKnownLocationGPS.getLongitude());
        }


        Intent i=getActivity().getIntent();
        final String loginPhoneNumber = i.getStringExtra("phno");
        bloodgroup = i.getStringExtra("bloodgroup");
        nam = i.getStringExtra("name");
        lat1 = Double.toString(coordinates.lat);//i.getStringExtra("lat");
        lon1 = Double.toString(coordinates.lon);//i.getStringExtra("lon");
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
                    CheckUpdateQuery checkUpdateQuery = new CheckUpdateQuery(loginPhoneNumber, Double.parseDouble(lat1), Double.parseDouble(lon1), "n");// this is the Asynctask, which is used to process in background to reduce load on app process
                    checkUpdateQuery.execute("");
                } else {
                    Log.d("smstrying", "smstrying");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "+919220592205"));
                    intent.putExtra("sms_body", "JPE93 "+lat1+","+lon1+",n");
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
                    CheckUpdateQuery checkUpdateQuery = new CheckUpdateQuery(loginPhoneNumber, Double.parseDouble(lat1), Double.parseDouble(lon1), "y");// this is the Asynctask, which is used to process in background to reduce load on app process
                    checkUpdateQuery.execute("");
                }
                else {
                    Log.d("smstrying", "smstrying");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "+919220592205"));
                    intent.putExtra("sms_body", "JPE93 "+lat1+","+lon1+",y");
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

        alert_b=(Button)view.findViewById(R.id.alerts_b);
        alert_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),AlertActivity.class);

                startActivity(i);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        pos=new ArrayList<>();
        nearbypos=new ArrayList<>();
        map=googleMap;
        if(message!=null){
            Log.v("fcmdata11",message);
            String mess="";//int ctr=0;
            for( int ctr=0;ctr<message.length()-1;ctr++){
                if(ctr>5) {
                    if (message.charAt(ctr) == '\'')
                        mess = mess + "\"";
                    else
                        mess = mess + message.charAt(ctr);
                }
//                ctr++;
            }
//            mess.replace("\'","\"");
            Log.v("fcmdata22",mess);
            try {
                JSONObject jsonObj = new JSONObject(mess);
                Log.v("fromjson",jsonObj.get("eqlocation").toString());
                JSONArray ja =(JSONArray) jsonObj.get("eqlocation");
                int flag=0;
                JSONArray cycJA = (JSONArray) jsonObj.get("cydata");

                for(int zz=0;zz<cycJA.length();zz++){
                    JSONObject cycLocation = (JSONObject) cycJA.get(zz);

                    JSONObject cyLocCoor = (JSONObject) cycLocation.get("cylocation");
                    LatLng cycStart = new LatLng((double)cyLocCoor.get("lat"),(double)cyLocCoor.get("lon"));
                    map.addMarker(new MarkerOptions().position(cycStart).title("Cyclone Location"));



//                    JSONObject cycTrack = (JSONObject) cycJA.get(zz);
                    JSONArray cyTrackArray = (JSONArray) cycLocation.get("cytrack");
                    ArrayList<LatLng> cyTrackPoints = new ArrayList<LatLng>();
                    for(int i=0;i<cyTrackArray.length();i++){
                        JSONObject temp=(JSONObject) cyTrackArray.get(i);
                        LatLng coord = new LatLng((double)temp.get("lat"),(double)temp.get("lon"));
                        cyTrackPoints.add(coord);
                    }

                    Polyline cyTrackLine = map.addPolyline(new PolylineOptions().addAll(cyTrackPoints).width(5).color(Color.RED));


//                    JSONObject cycForecast = (JSONObject) cycJA.get(2);
                    JSONArray cycForecastArray = (JSONArray) cycLocation.get("cyforecast");
                    ArrayList<LatLng> cyForecastPoints = new ArrayList<LatLng>();
                    for(int i=0;i<cycForecastArray.length();i++){
                        JSONObject temp = (JSONObject)cycForecastArray.get(i);
                        LatLng coord = new LatLng((double)temp.get("lat"),(double)temp.get("lon"));
                        cyForecastPoints.add(coord);
                    }

                    Polyline cyForecastLine = map.addPolyline(new PolylineOptions().addAll(cyForecastPoints).width(5).color(Color.BLACK));


                }


                for(int i=0;i<ja.length();i++){
                    JSONObject temp =(JSONObject) ja.get(i);
                    double tlat=Double.parseDouble(lat1),tlon=Double.parseDouble(lon1);

                    double al_lat=(double)temp.get("lat"),al_lon=(double)temp.get("lon");
                    int tsunami=(int)temp.get("tsunami");
                    Log.v("madvalues"," "+tlat+" "+tlon+" "+al_lat+" "+al_lon);
                    if(Math.pow(tlat-al_lat,2)+ Math.pow(tlon-al_lon,2)<=5){
                        flag=1;
                        pos.add(new LatLng(al_lat,al_lon));
                        nearbypos.add(new LatLng(al_lat,al_lon));

                    }
                    if(tsunami==0){
                        map.addMarker(new MarkerOptions().position(new LatLng((double)temp.get("lat"),(double)temp.get("lon"))).title("Earthquake"));
                    }else{
                        map.addMarker(new MarkerOptions().position(new LatLng((double)temp.get("lat"),(double)temp.get("lon"))).title("Earthquake").snippet("Tsunami Warning"));
                    }

//                    map.addMarker(new MarkerOptions().position(new LatLng((double)temp.get("lat"),(double)temp.get("lon"))).title("Earthquake").snippet("Tsunami Warning"));
                }
                if(flag==1){
//                    Intent intent=new Intent(getContext(),Alert.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.putExtra("message",pos);
//                    PendingIntent pendingIntent=PendingIntent.getActivity(getContext(),0/*request code*/,intent,PendingIntent.FLAG_ONE_SHOT);
                    Log.v("tooclose","tooclose");
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.small)
                            .setContentTitle("Alert!!")
                            .setContentText("Nearby disaster.")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("Tap here to look at the updated map.\nName:"+nam+":\nBlood Group:"+bloodgroup))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//                            .setOngoing(true);                    //PERSISTENT NOTIFICATIONS

                    notificationManager.notify(1, mBuilder.build());
//                            .setContentIntent(pendingIntent);

                }
            }
            catch (JSONException e){
                e.printStackTrace();
                Toast.makeText(getContext(),"No JSON received",Toast.LENGTH_SHORT).show();
            }

        }

        LatLng point=new LatLng(12.925471,77.501349);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(point).title("India");
//        map.addMarker(markerOptions);
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



    private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {

            String message = String.format(

                    "New Location \n Longitude: %1$s \n Latitude: %2$s",

                    location.getLongitude(), location.getLatitude()

            );

//            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();

            Log.v("loooc1",message);

        }



        public void onStatusChanged(String s, int i, Bundle b) {

            Toast.makeText(getContext(), "Provider status changed",

                    Toast.LENGTH_LONG).show();

        }


        public void onProviderDisabled(String s) {

            Toast.makeText(getContext(),

                    "Provider disabled by the user. GPS turned off",

                    Toast.LENGTH_LONG).show();

        }
        public void onProviderEnabled(String s) {

            Toast.makeText(getContext(),

                    "Provider enabled by the user. GPS turned on",

                    Toast.LENGTH_LONG).show();

        }
    }






    protected coord showCurrentLocation() {

        String permission=android.Manifest.permission.ACCESS_FINE_LOCATION;
        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            Log.v("outerloop1","outerloop1");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                Log.v("innerloop1","innerloop1");
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);

            } else {
                Log.v("innerloopelse1","innerloopelse1");
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            }
        } else {
            Log.v("outerloopelse1","outerloopelse1");
            Toast.makeText(getContext(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }


//            Location location =
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);

//            if (location != null) {
//
//                String message = String.format(
//
//                        "Current Location \n Longitude: %1$s \n Latitude: %2$s",
//
//                        location.getLongitude(), location.getLatitude()
//
//                );
//                coord coord1=new coord(location.getLongitude(),location.getLatitude());
//                return coord1;
//
////                Toast.makeText(LoginActivity.this, message,
////
////                        Toast.LENGTH_LONG).show();
//
//            }

        return new coord(1,1);



    }

    class coord{
        coord(double l1,double l2){
            lat=l1;lon=l2;
        }
        double lat,lon;
    }


    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{permissionName}, permissionRequestCode);
    }
}
