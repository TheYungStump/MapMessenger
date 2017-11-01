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
        setUniqueKey(uniqueKey);
        setCreatedDate(createdDate);
        setLocationType(locationType);
        setBorough(borough);
        setIncidentZip(incidentZip);
        setIncidentAddress(incidentAddress);
        setCity(city);
        setBorough(borough);
        setLatitude(latitude);
        setLongitude(longitude);
    }
    public void setUniqueKey(String uniqueKey) { this.uniqueKey = uniqueKey;}
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate;;}
    public void setLocationType(String locationType) { this.locationType = locationType;}
    public void setIncidentZip(String incidentZip) { this.incidentZip= incidentZip;}
    public void setIncidentAddress(String incidentAddress) { this.incidentAddress = incidentAddress;}
    public void setCity(String city){ this.city = city;}
    public void setBorough(String borough){this.borough = borough;}
    public void setLatitude(String latitude){ this.latitude = latitude;}
    public void setLongitude(String longitude){this.longitude = longitude;}
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

