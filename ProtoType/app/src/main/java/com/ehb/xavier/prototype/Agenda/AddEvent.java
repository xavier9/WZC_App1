package com.ehb.xavier.prototype.Agenda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ehb.xavier.api.eventEndpoint.model.Event;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;
import com.google.api.client.util.DateTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xavier on 15/02/2015.
 */
public class AddEvent extends Activity {
    EditText startdate;
    EditText enddate;
    EditText beschrijving;
    AsyncTask<Event, Void, List<Event>> event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_popup);
        event = new Endpoint.EndpointsAsyncTaskEvent(this).execute();
        Calendar c = Globel.CALENDAR;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String test = sdf.format(c.getTime());
        startdate = (EditText) findViewById(R.id.startdate);
        startdate.setText(test);
        enddate = (EditText) findViewById(R.id.enddate);
        sdf = new SimpleDateFormat("HH:mm");
        //c.getTime().setHours(c.getTime().getHours()+1);
        Log.e("testing", c.getTime().toString());
        test = sdf.format(c.getTime());

        enddate.setText(test);

        beschrijving = (EditText) findViewById(R.id.titelbeschrijving);

        Button b = (Button) findViewById(R.id.dialogButtonOK);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("start", startdate.getText().toString());
                Log.e("end", enddate.getText().toString());
                Log.e("beschrijvin", beschrijving.getText().toString());
                Calendar cal = Globel.CALENDAR;
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date test = null;
                try {
                    test = sdf.parse(startdate.getText().toString());
                    Log.e("start", test.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //cal = Globel.CALENDAR;
                cal.set(Calendar.HOUR_OF_DAY,test.getHours());
                cal.set(Calendar.MINUTE,test.getMinutes());
                cal.set(Calendar.SECOND,0);
                Log.e("start", cal.getTime().toString());
                final Event e = new Event();
                if(Globel.customer1 != null ){
                    e.setUserID(Globel.customer1.getId());
                }
                e.setName(beschrijving.getText().toString());
                e.setStartTime(new DateTime(cal.getTime()));
                Log.e("start", e.getStartTime().toString());

                Calendar cal1 =  Globel.CALENDAR;
                sdf = new SimpleDateFormat("HH:mm");
                Date test1 = null;
                try {
                    test1 = sdf.parse(enddate.getText().toString());
                    Log.e("end", test1.toString());
                } catch (ParseException es) {
                    es.printStackTrace();
                }
                ImageView im = (ImageView) findViewById(R.id.imageView12);
                im.setVisibility(View.INVISIBLE);
                //cal1 = Globel.CALENDAR;
                cal1.set(Calendar.HOUR_OF_DAY,test1.getHours());
                cal1.set(Calendar.MINUTE,test1.getMinutes());
                cal1.set(Calendar.SECOND,0);
                Log.e("end", cal1.getTime().toString());
                e.setEndTime(new DateTime(cal1.getTime()));
                Log.e("event", e.toString());
                if(!e.getName().isEmpty()){
                    if(e.getName() != null) {
                        im.setVisibility(View.INVISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {


                                try {
                                    Endpoint.myApiServiceEE.insertEvent(e).execute();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }).start();
                        Intent i = new Intent(AddEvent.this, CalenderPersonal.class);
                        startActivity(i);
                    }
                }else {
                    im.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
