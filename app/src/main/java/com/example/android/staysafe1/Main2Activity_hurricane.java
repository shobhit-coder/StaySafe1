package com.example.android.staysafe1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main2Activity_hurricane extends AppCompatActivity {
    TextView descText2, descText3,descText4,descText5,descText6,descText7,descText8,descText9;
    ImageButton plus2, minus2,plus3, minus3,plus4, minus4,plus5, minus5,plus6, minus6,plus7, minus7,plus8, minus8,plus9, minus9;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_hurricane);
        descText2 = (TextView) findViewById(R.id.description_text2);
        descText3 = (TextView) findViewById(R.id.description_text3);
        descText4 = (TextView) findViewById(R.id.description_text4);
        descText5 = (TextView) findViewById(R.id.description_text5);
        descText6 = (TextView) findViewById(R.id.description_text6);
        descText7 = (TextView) findViewById(R.id.description_text7);
        descText8 = (TextView) findViewById(R.id.description_text8);
        descText9 = (TextView) findViewById(R.id.description_text9);


        minus2 = (ImageButton) findViewById(R.id.minus2);
        plus2 = (ImageButton) findViewById(R.id.plus2);

        minus3 = (ImageButton) findViewById(R.id.minus3);
        plus3 = (ImageButton) findViewById(R.id.plus3);

        minus4 = (ImageButton) findViewById(R.id.minus4);
        plus4 = (ImageButton) findViewById(R.id.plus4);

        minus5 = (ImageButton) findViewById(R.id.minus5);
        plus5 = (ImageButton) findViewById(R.id.plus5);

        minus6 = (ImageButton) findViewById(R.id.minus6);
        plus6 = (ImageButton) findViewById(R.id.plus6);

        minus7 = (ImageButton) findViewById(R.id.minus7);
        plus7 = (ImageButton) findViewById(R.id.plus7);

        minus8 = (ImageButton) findViewById(R.id.minus8);
        plus8 = (ImageButton) findViewById(R.id.plus8);

        minus9 = (ImageButton) findViewById(R.id.minus9);
        plus9 = (ImageButton) findViewById(R.id.plus9);


        minus2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus2.setVisibility(v.GONE);
                plus2.setVisibility(v.VISIBLE);
                descText2.setVisibility(View.GONE);

            }
        });
        plus2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus2.setVisibility(v.GONE);
                minus2.setVisibility(v.VISIBLE);
                descText2.setVisibility(View.VISIBLE);

            }
        });

        minus3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus3.setVisibility(v.GONE);
                plus3.setVisibility(v.VISIBLE);
                descText3.setVisibility(View.GONE);

            }
        });
        plus3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus3.setVisibility(v.GONE);
                minus3.setVisibility(v.VISIBLE);
                descText3.setVisibility(View.VISIBLE);

            }
        });

        minus4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus4.setVisibility(v.GONE);
                plus4.setVisibility(v.VISIBLE);
                descText4.setVisibility(View.GONE);

            }
        });
        plus4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus4.setVisibility(v.GONE);
                minus4.setVisibility(v.VISIBLE);
                descText4.setVisibility(View.VISIBLE);

            }
        });

        minus5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus5.setVisibility(v.GONE);
                plus5.setVisibility(v.VISIBLE);
                descText5.setVisibility(View.GONE);

            }
        });
        plus5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus5.setVisibility(v.GONE);
                minus5.setVisibility(v.VISIBLE);
                descText5.setVisibility(View.VISIBLE);

            }
        });

        minus6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus6.setVisibility(v.GONE);
                plus6.setVisibility(v.VISIBLE);
                descText6.setVisibility(View.GONE);

            }
        });
        plus6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus6.setVisibility(v.GONE);
                minus6.setVisibility(v.VISIBLE);
                descText6.setVisibility(View.VISIBLE);

            }
        });

        minus7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus7.setVisibility(v.GONE);
                plus7.setVisibility(v.VISIBLE);
                descText7.setVisibility(View.GONE);

            }
        });
        plus7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus7.setVisibility(v.GONE);
                minus7.setVisibility(v.VISIBLE);
                descText7.setVisibility(View.VISIBLE);

            }
        });


        minus8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus8.setVisibility(v.GONE);
                plus8.setVisibility(v.VISIBLE);
                descText8.setVisibility(View.GONE);

            }
        });
        plus8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus8.setVisibility(v.GONE);
                minus8.setVisibility(v.VISIBLE);
                descText8.setVisibility(View.VISIBLE);

            }
        });

        minus9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus9.setVisibility(v.GONE);
                plus9.setVisibility(v.VISIBLE);
                descText9.setVisibility(View.GONE);

            }
        });
        plus9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus9.setVisibility(v.GONE);
                minus9.setVisibility(v.VISIBLE);
                descText9.setVisibility(View.VISIBLE);

            }
        });


    }

}
