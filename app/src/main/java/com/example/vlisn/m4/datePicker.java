
package com.example.vlisn.m4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by Divya on 10/31/2017.
 */


public class datePicker extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    DatePicker from;
    DatePicker to;
    Button done;
    static int fromDay, fromMonth, fromYear, toDay, toMonth, toYear;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);
        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(this);
        from = (DatePicker) findViewById(R.id.from);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        System.out.println("datePicker info: " + datePicker.toString());

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:
                fromDay = from.getDayOfMonth();
                fromMonth = from.getMonth();
                fromYear = from.getYear();

                to = (DatePicker) findViewById(R.id.to);
                toDay = to.getDayOfMonth();
                toMonth = to.getMonth();
                toYear = to.getYear();

                onDateSet(from, fromDay, fromMonth, fromYear);
                onDateSet(to, toDay, toMonth, toYear);

                if (fromYear > toYear){
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid Date Range!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if (fromYear == toYear && (fromMonth > toMonth)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid Date Range!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if (fromYear == toYear && fromMonth == toMonth && fromDay > toDay ) {
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid Date Range!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    startActivity(new Intent(this, MapsActivity.class));
                }
                System.out.println("days: " + fromDay + ", " + toDay);
                System.out.println("months: " + fromMonth + ", " + toMonth);
                System.out.println("years: " + fromYear + ", " + toYear);
        }
    }
}

