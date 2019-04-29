package com.matheus.android.music_suggestion_app;

public class OpenWheatherResponse {


    private String city;
    private String country;

    public OpenWheatherResponse(String city, String country) {

        setCity(city);
        setCountry(country);

    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }



}
