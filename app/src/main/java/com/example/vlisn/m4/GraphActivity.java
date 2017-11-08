package com.example.vlisn.m4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class GraphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    ArrayList<String> ratDates;
    Map<String, Integer> occursCount;
    ArrayList<String> datesArr;

    /**
     * Pulls from Firebase Database then creates a Map with keys corresponding to dates within
     * the range from datepicker and values corresponding to the number of rat sightings each
     * month. It then graphs the data using GraphView.
     * @param savedInstanceState bundle object used upon creation
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
                for (int i = 0; i < ratDates.size(); i++) {
                    String ratMonthDayYear = ratDates.get(i).substring(0, ratDates.get(i).indexOf('/'))
                            + '/' + ratDates.get(i).substring(ratDates.get(i).indexOf('/') + 1, ratDates.get(i).lastIndexOf('/'))
                            + '/' + ratDates.get(i).substring(ratDates.get(i).lastIndexOf('/') + 1,
                            ratDates.get(i).indexOf(':') - 2);
                    if (occursCount.containsKey(ratMonthDayYear)) {
                        occursCount.put(ratMonthDayYear, occursCount.get(ratMonthDayYear) + 1);
                    } else {
                        occursCount.put(ratMonthDayYear, 1);
                    }
                }

                for (Map.Entry<String, Integer> entry : occursCount.entrySet()) {
                    Integer ratMonth = Integer.parseInt(entry.getKey().substring(0, entry.getKey().indexOf('/')));
                    System.out.println(ratMonth);
                    Integer ratYear = Integer.parseInt(entry.getKey().substring(entry.getKey().lastIndexOf('/') + 2,
                            entry.getKey().length())) + 2000;
                    Integer ratDay = Integer.parseInt(entry.getKey().substring(entry.getKey().indexOf('/') + 1,
                            entry.getKey().lastIndexOf('/')));
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(ratYear, (ratMonth - 1) % 12, ratDay);
                    Date date = calendar.getTime();
                    if (ratYear >= DatePickerforGraph.fromYear && ratYear <= DatePickerforGraph.toYear) {
                        System.out.println(date.toString());
                        series.appendData(new DataPoint(date, entry.getValue()), true, occursCount.size());
                    } else if (ratYear == DatePickerforGraph.fromYear && ratYear == DatePickerforGraph.toYear
                            && ratMonth >= DatePickerforGraph.fromMonth && ratMonth <= DatePickerforGraph.toMonth) {
                        series.appendData(new DataPoint(date, entry.getValue()), true, occursCount.size());
                    }
                }

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(DatePickerforGraph.fromYear, DatePickerforGraph.fromMonth, DatePickerforGraph.fromDay);
                Date date1 = calendar1.getTime();
                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(DatePickerforGraph.toYear, DatePickerforGraph.toMonth, DatePickerforGraph.toDay);
                Date date2 = calendar2.getTime();
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMaxY(100);
                graph.getViewport().setMinY(0);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(date1.getTime());
                graph.getViewport().setMaxX(date2.getTime());
                series.setDrawDataPoints(true);
                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraphActivity.this));
                graph.getGridLabelRenderer().setNumHorizontalLabels(4);
                graph.addSeries(series);
            }

            /**
             * This traverses the Firebase Database and pulls the creation dates
             * for each rat report.
             * @param users map of users to traverse
             */
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
