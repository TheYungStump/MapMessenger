package com.example.vlisn.m4;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<String> ratDates;
    ArrayList<String>  longitude;
    ArrayList<String> latitude;
    ArrayList<String> key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        DatabaseReference mFirebaseInstance1 = FirebaseDatabase.getInstance().getReference().child("rats");

        mFirebaseInstance1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getRatDates((Map<String, Object>) dataSnapshot.getValue());
                //System.out.println("real rat dates: " + ratDates.get(0));

                for (int i = 0; i < ratDates.size(); i++) {
                    System.out.println("i: " + i + "; value: " + ratDates.get(i));
                    int ratMonth = Integer.parseInt(ratDates.get(i).substring(0, ratDates.get(i).indexOf('/')));
                    System.out.println("rat Month: " + ratMonth);
                    int ratDay = Integer.parseInt(ratDates.get(i).substring(ratDates.get(i).indexOf('/') + 1, ratDates.get(i).lastIndexOf('/')));
                    System.out.println("rat Day: " + ratDay);
                    int ratYear = Integer.parseInt(ratDates.get(i).substring(ratDates.get(i).lastIndexOf('/') + 1, ratDates.get(i).indexOf(':') - 2));
                    System.out.println("rat Year: "+ ratYear);
                    double lat = Double.parseDouble(latitude.get(i));
                    double longit = Double.parseDouble(longitude.get(i));
                    String uniqueKey = key.get(i);

                    if (ratYear > datePicker.fromYear && ratYear < datePicker.toYear) {
                        LatLng location = new LatLng(lat, longit);
                        float zoomLevel = 12.0f;
                        mMap.addMarker(new MarkerOptions().position(location).title("Unique Key: " + uniqueKey));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
                    } else if (ratYear == datePicker.fromYear && ratYear == datePicker.toYear && ratMonth > datePicker.fromMonth && ratMonth < datePicker.toMonth) {
                        LatLng location = new LatLng(lat, longit);
                        float zoomLevel = 12.0f;
                        mMap.addMarker(new MarkerOptions().position(location).title("Unique Key: " + uniqueKey));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
                    } else if (ratYear == datePicker.fromYear && ratYear == datePicker.toYear && ratMonth == datePicker.fromMonth && ratMonth == datePicker.toMonth
                            && ratDay > datePicker.fromDay && ratDay < datePicker.toDay) {
                        LatLng location = new LatLng(lat, longit);
                        float zoomLevel = 12.0f;
                        mMap.addMarker(new MarkerOptions().position(location).title("Unique Key: " + uniqueKey));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
                    }

                    // Add a marker in Sydney and move the camera

                }
            }

            private void getRatDates(Map<String,Object> users) {

                ratDates = new ArrayList<>();
                latitude = new ArrayList<>();
                longitude = new ArrayList<>();
                key = new ArrayList<>();

                //iterate through each user, ignoring their UID
                for (Map.Entry<String, Object> entry : users.entrySet()){

                    //Get user map
                    Map singleUser = (Map) entry.getValue();
                    //Get phone field and append to list
                    ratDates.add((String) singleUser.get("createdDate"));
                    latitude.add(singleUser.get("latitude").toString());
                    longitude.add(singleUser.get("longitude").toString());
                    key.add(singleUser.get("uniqueKey").toString());
                }
                System.out.println("rat Dates: " + ratDates.toString() + ", " + ratDates.size());
                System.out.println("latitudes: " + latitude.toString() + ", " + latitude.size());
                System.out.println("longitude: " + longitude.toString()+ ", " + longitude.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException(); // don't ignore errors
            }
        });
    }
}
