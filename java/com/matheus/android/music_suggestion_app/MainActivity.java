package com.matheus.android.music_suggestion_app;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText city, country;
    Button search;

    String response;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.cidadeEdt);
        country = findViewById(R.id.paisEdt);
        search = findViewById(R.id.searchBtn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        response = OpenWheater.getTemperature(city.getText().toString(), country.getText().toString());

                    try {
                        JSONObject object = new JSONObject(response);

                       passInformation(object.toString());

                    }catch(Exception e){e.printStackTrace();}

                    }

                    });

                thread.start();
            }
        });


    }

    public void passInformation(String value){

        Intent intent = new Intent(MainActivity.this, ResponseActivity.class);
        intent.putExtra("Json response", value);
        startActivity(intent);
    }

}
