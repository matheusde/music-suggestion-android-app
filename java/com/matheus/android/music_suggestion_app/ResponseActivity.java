package com.matheus.android.music_suggestion_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        Bundle extras = getIntent().getExtras();
        String extrasString = extras.getString("Json response");

        TextView musicNameTxt = findViewById(R.id.musicNameTxt);
        TextView singer = findViewById(R.id.singerTxt);

        try{
            JSONObject object = new JSONObject(extrasString);

            JSONArray array = object.getJSONArray("tracks");

            for(int i =0;i < array.length();i++){

                JSONObject obj1 =  array.getJSONObject(i);
                obj1.getJSONObject("album");
                JSONArray array1 = obj1.getJSONArray("artists");

                for(int o = 0;i < array1.length();o++){

                    String musicName = obj1.getString("name");
                    musicNameTxt.setText(musicName);

                    JSONObject obj2 = array1.getJSONObject(o);
                    String singerName = obj2.getString("name");
                    singer.setText(singerName);


                }

            }

            } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
