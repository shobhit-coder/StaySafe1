package com.example.android.staysafe1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
    }
    public void callHurricane(View view){
        Intent intent = new Intent(this, Main2Activity_hurricane.class);
        this.startActivity ( intent );
    }
    public void callFlood(View view){
        Intent intent = new Intent(this, Main2Activity_flood.class);
        this.startActivity ( intent );
    }

    public void callWildfire(View view){
        Intent intent = new Intent(this, Main2Activity_wildfire.class);
        this.startActivity ( intent );
    }
    public void callEarthquake(View view){
        Intent intent = new Intent(this, Main2Activity_earthquake.class);
        this.startActivity ( intent );
    }
    public void callTornado(View view){
        Intent intent = new Intent(this, Main2Activity_tornado.class);
        this.startActivity ( intent );
    }
    public void callLandslide(View view){
        Intent intent = new Intent(this, Main2Activity_landslide.class);
        this.startActivity ( intent );
    }

}
