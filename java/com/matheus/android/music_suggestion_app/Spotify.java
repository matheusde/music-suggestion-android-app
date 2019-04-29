package com.matheus.android.music_suggestion_app;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;



public class Spotify {

    public static String getToken() {

        String token = null;
        String encodedString = passToBase64("SPOTIFY CLIENT ID GOES HERE","SPOTIFY CLIENT SECRET ID GOES HERE");

        String url = "https://accounts.spotify.com/api/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        LinkedMultiValueMap<String, String> Body = new LinkedMultiValueMap<>();
        Body.add("grant_type", "client_credentials");
        headers.add("Authorization", "Basic " + encodedString);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(Body, headers);

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        try {
            JSONObject object = new JSONObject(response.getBody());
            token = object.getString("access_token");
        }catch(Exception e){e.printStackTrace();}

        return token;

    }


    public static String passToBase64(String clientId, String clientIdSecret) {

        String stringToBeEncoded = clientId+":"+clientIdSecret;

        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(stringToBeEncoded.getBytes(StandardCharsets.UTF_8));

        return encodedString;

    }

}
