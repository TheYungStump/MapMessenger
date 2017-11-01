package com.example.vlisn.m4;

/**
 * Created by vlisn on 10/30/2017.
 */

public class Rat {
    public String uniqueKey;
    public String createdDate;
    public String locationType;
    public String incidentZip;
    public String incidentAddress;
    public String city;
    public String borough;
    public String latitude;
    public String longitude;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Rat() {
    }

    public Rat(String uniqueKey, String createdDate, String locationType, String incidentZip, String incidentAddress,
               String city, String borough, String latitude, String longitude) {
        this.uniqueKey = uniqueKey;
        this.createdDate = createdDate;
        this.locationType = locationType;
        this.incidentZip= incidentZip;
        this.incidentAddress = incidentAddress;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getUniqueKey() { return uniqueKey;}
    public String getCreatedDate() { return createdDate;}
    public String getLocationType() { return locationType;}
    public String getIncidentZip() { return incidentZip;}
    public String getIncidentAddress() { return incidentAddress;}
    public String getCity(){return city;}
    public String getBorough(){return borough;}
    public String getLatitude(){return latitude;}
    public String getLongitude(){return longitude;}


}

