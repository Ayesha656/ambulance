package com.example.ambulance;

public class user {

    public user(){

    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

    public String getLangitude() {
        return langitude;
    }

    public void setLangitude(String langitude) {
        this.langitude = langitude;
    }

    public String getLongitide() {
        return longitide;
    }

    public void setLongitide(String longitide) {
        this.longitide = longitide;
    }

    String contactinfo;
    String langitude;

    public user(String contactinfo, String langitude, String longitide) {
        this.contactinfo = contactinfo;
        this.langitude = langitude;
        this.longitide = longitide;
    }

    String longitide;
}
