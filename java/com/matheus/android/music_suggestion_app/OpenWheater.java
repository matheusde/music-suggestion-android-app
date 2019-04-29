package com.matheus.android.music_suggestion_app;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OpenWheater{

   private static OpenWheatherResponse openWheatherResponse = null;


    public static String Token(){

        return Spotify.getToken();

    }

    public static String getTemperature(String city,String country) {

        double temp = 0;

        openWheatherResponse = new OpenWheatherResponse(city, country);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&appid=*OPEN WHEATER API KEY GOES HERE*";

        RestTemplate restTemplate = new RestTemplate();

        String response =  restTemplate.postForObject(url, entity, String.class);

        try {
            JSONObject object = new JSONObject(response);
            temp = object.getJSONObject("main").getDouble("temp");
        }catch(Exception e){e.printStackTrace();}


        return suggestions(temp);
    }


    public static String suggestions(Double temperature){

        final String PARTY = "party";
        final String POP = "pop";
        final String ROCK = "rock";
        final String CLASSICAL = "classical";

        String result = null;

        if(temperature >= 30) {

            result = httpRequest(PARTY);

        }else if(temperature >= 15){

            result = httpRequest(POP);

        }else if(temperature >= 10 && temperature <= 14){

            result = httpRequest(ROCK);

        }else if(temperature < 10){

            result = httpRequest(CLASSICAL);

        }

        return result;


    }

    public static String httpRequest(String genre){

        RestTemplate restTemplate = new RestTemplate();
        String token = Token();
        String url = "https://api.spotify.com/v1/recommendations?seed_genres="+genre+"&limit=1";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();

    }

}
