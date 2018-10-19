package com.example.android.staysafe1;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText num,bg,name;
    SharedPreferences sp;

    private static final float MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 5f; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 5000; // in Milliseconds
    protected LocationManager locationManager;
    protected Button retrieveLocationButton;

    MyLocationListener locationListener;
    coord coordinates;
    int requestCode = 1;
    String num1="",name1="",bloodgroup="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationListener = new MyLocationListener();
//        retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String permission=android.Manifest.permission.ACCESS_FINE_LOCATION;
        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            Log.v("outerloop","outerloop");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, permission)) {
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
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
        while(ContextCompat.checkSelfPermission(LoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) ;
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new MyLocationListener());

                coordinates=showCurrentLocation();
                Log.v("cooor",""+coordinates.lat+coordinates.lon);


        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.button);
        num = (EditText) findViewById(R.id.editText2);
        bg=(EditText) findViewById(R.id.BG_et);
        name=(EditText)findViewById(R.id.Name_et);
        sp = getSharedPreferences("login",MODE_PRIVATE);
//        sp = getSharedPreferences("bloodgroup",MODE_PRIVATE);
//        sp = getSharedPreferences("login",MODE_PRIVATE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }

        /*
         * Displaying a notification locally
         */
//        MyNotificationManager.getInstance(this).displayNotification("Greetings", "Hello how are you?");

        if(sp.getBoolean("logged",false)){

            goToMainActivity();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=num.getText().toString();
                bloodgroup=bg.getText().toString();
                name1=name.getText().toString();
                CheckLogin checkLogin = new CheckLogin(num.getText().toString(),-500.00,-500.00,"y");// this is the Asynctask, which is used to process in background to reduce load on app process
                checkLogin.execute("");
                goToMainActivity();
                sp.edit().putBoolean("logged",true).apply();

                sp.edit().putString("bloodgroup",bg.getText().toString()).apply();
                sp.edit().putString("name",name.getText().toString()).apply();
                sp.edit().putString("number",num.getText().toString()).apply();
                sp.edit().putString("lat",Double.toString(coordinates.lat)).apply();
                sp.edit().putString("lon",Double.toString(coordinates.lon)).apply();
//                sp.edit().commit();
            }
        });
    }



    protected coord showCurrentLocation() {

        String permission=android.Manifest.permission.ACCESS_FINE_LOCATION;
        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            Log.v("outerloop1","outerloop1");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, permission)) {
                Log.v("innerloop1","innerloop1");
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);

            } else {
                Log.v("innerloopelse1","innerloopelse1");
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Log.v("outerloopelse1","outerloopelse1");
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }


//            Location location =
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
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



    private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {

            String message = String.format(

                    "New Location \n Longitude: %1$s \n Latitude: %2$s",

                    location.getLongitude(), location.getLatitude()

            );

            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

            Log.v("loooc",message);

        }



        public void onStatusChanged(String s, int i, Bundle b) {

            Toast.makeText(LoginActivity.this, "Provider status changed",

                    Toast.LENGTH_LONG).show();

        }


        public void onProviderDisabled(String s) {

            Toast.makeText(LoginActivity.this,

                    "Provider disabled by the user. GPS turned off",

                    Toast.LENGTH_LONG).show();

        }
        public void onProviderEnabled(String s) {

            Toast.makeText(LoginActivity.this,

                    "Provider enabled by the user. GPS turned on",

                    Toast.LENGTH_LONG).show();

        }
    }




    public void goToMainActivity(){
        Intent i = new Intent(this,MainActivity.class);
        String s="",bg="",nam="";
        String lat1="",lon1="";
        Log.v("valueofnum1",num1);
        if(num1==""){
            s= sp.getString("number","DEFAULT");
            bg=sp.getString("bloodgroup","DEFAULT");;
            nam=sp.getString("name","DEFAULT");;
            lat1= sp.getString("lat","DEFAULT");
            lon1=sp.getString("lon","DEFAULT");
//            sp.edit().putString("bloodgroup",bg.getText().toString()).apply();
//            sp.edit().putString("name",name.getText().toString()).apply();
//            sp.edit().putString("number",num.getText().toString()).apply();
//            sp.edit().putFloat("lat",(float)coordinates.lat).apply();
//            sp.edit().putFloat("lon",(float)coordinates.lon).apply();
        }
        else{
            s=num1;
            bg=bloodgroup;
            nam=name1;
            lat1=Double.toString(coordinates.lat);
            lon1=Double.toString( coordinates.lon);
        }
        Log.v("numberis",s);
        i.putExtra("phno",s);
        i.putExtra("name",nam);
        i.putExtra("bloodgroup",bg);
        i.putExtra("lon",lon1);
        i.putExtra("lat",lat1);
        startActivity(i);
    }



    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(LoginActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }
    class coord{
        coord(double l1,double l2){
            lat=l1;lon=l2;
        }
        double lat,lon;
    }
}