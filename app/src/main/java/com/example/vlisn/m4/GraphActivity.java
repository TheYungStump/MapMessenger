package com.example.vlisn.m4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    ArrayList<String> ratDates;
    Map<String, Integer> occursCount;
    ArrayList<String> datesArr;

    /**
     * Pulls from Firebase Database then creates a Map with keys corresponding to dates within
     * the range from datepicker and values corresponding to the number of rat sightings each
     * month. It then graphs the data using GraphView.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        occursCount = new HashMap<>(23, (float) 0.7);
        DatabaseReference mFirebaseInstance1 = FirebaseDatabase.getInstance().getReference().child("rats");

        mFirebaseInstance1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getRatDates((Map<String, Object>) dataSnapshot.getValue());
                datesArr = new ArrayList<String>(0);
                System.out.println(ratDates.toString());
                for (int i = 0; i < ratDates.size(); i++) {
                    String ratMonthYear = ratDates.get(i).substring(0, ratDates.get(i).indexOf('/'))
                            + '/' + ratDates.get(i).substring(ratDates.get(i).lastIndexOf('/') + 1,
                            ratDates.get(i).indexOf(':') - 2);
                    if (occursCount.containsKey(ratMonthYear)) {
                        occursCount.put(ratMonthYear, occursCount.get(ratMonthYear) + 1);
                    } else {
                        occursCount.put(ratMonthYear, 1);
                    }
                }

                System.out.println(occursCount.toString());
                for (Map.Entry<String, Integer> entry : occursCount.entrySet()) {
                    Integer ratMonth = Integer.parseInt(entry.getKey().substring(0, entry.getKey().indexOf('/')));
                    Integer ratYear = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf('/') + 2,
                            entry.getKey().length())) + 2000;
                    Integer monthYear = Integer.parseInt(ratMonth.toString() + ratYear.toString());
                    System.out.println(ratMonth.toString());
                    System.out.println(ratYear.toString());
                    System.out.println(monthYear.toString());
                    if (ratYear >= DatePickerforGraph.fromYear && ratYear <= DatePickerforGraph.toYear
                            && ratMonth >= DatePickerforGraph.fromMonth && ratMonth <= DatePickerforGraph.toMonth) {
                        System.out.println("here");
                        series.appendData(new DataPoint(monthYear, entry.getValue()), true, occursCount.size());
                        datesArr.add(datesArr.size(), entry.getKey());
                    }
                }
                System.out.println(datesArr);
                String[] dateLabels = new String[datesArr.size()];
                for (int i = 0; i < datesArr.size(); i++) {
                    dateLabels[i] = datesArr.get(i);
                }
                //StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                //staticLabelsFormatter.setHorizontalLabels(dateLabels);
                //graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                graph.getViewport().setScrollable(true);
                graph.addSeries(series);
            }

            private void getRatDates(Map<String, Object> users) {
                ratDates = new ArrayList<>();

                //iterate through each user, ignoring their UID
                for (Map.Entry<String, Object> entry : users.entrySet()) {

                    //Get user map
                    Map singleUser = (Map) entry.getValue();
                    //Get phone field and append to list
                    ratDates.add((String) singleUser.get("createdDate"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException(); // don't ignore errors
            }
        });
    }
}
