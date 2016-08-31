package com.ehb.xavier.prototype.Agenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.ehb.xavier.api.eventEndpoint.model.Event;
import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;
import com.google.api.client.util.DateTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by xavier on 16/11/2014.
 */
public class Calender extends Activity implements
        WeekView.EventClickListener, WeekView.EventLongPressListener, AdapterView.OnItemClickListener {

    private static final int TYPE_DAY_VIEW = 1;
    //private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_WEEK_VIEW;
    private WeekView mWeekView;
    AsyncTask<User, Void, List<User>> EE;
    AsyncTask<Event, Void, List<Event>> Even;
    ListView lv = null;
    EventArrayAdapter EAA;
    Event even = new Event();
    int mont = 0;;
    WeekViewEvent weekViews;
    ArrayList<Event> evends = new ArrayList<Event>();
    ArrayList<Event> evends1 = new ArrayList<Event>();
    Event eve = new Event();
    String s = "";
    AlertDialog a;
    ArrayList<User> evend = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        EE = new Endpoint.EndpointsAsyncTaskUser(this).execute();
        // Get a reference for the week view in the layout
        Globel.addglobal=false;
        if(!Globel.addglobal){
        evend.add(Globel.USER_G);
        User us = new User();
        us.setUserName("Global");
        evend.add(us);
            Globel.addglobal = true;
        }
        //for(User e: evend){
        // Log.e("testing", e.toString());
        // }
        EAA=    new EventArrayAdapter(this, evend);

        lv = (ListView) findViewById(R.id.calenderlist);
        //lv.setItemChecked (1, true);;
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setAdapter(EAA);
        lv.setItemChecked(1, true);

        lv.setOnItemClickListener(this);

        Even = new Endpoint.EndpointsAsyncTaskEvent(this).execute();
        mWeekView = (WeekView) findViewById(R.id.weekView);
       // Log.e("cols", 0+"");
        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.

        mWeekView.setMonthChangeListener(new WeekView.MonthChangeListener() {

            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
                    Log.e("Calde", 0 + "");
                    // Populate the week view with some events.

                    ArrayList<Event> eventen = new ArrayList<Event>();
                    ArrayList<Event> event1 = new ArrayList<Event>();
                    try {
                        eventen = (ArrayList<Event>) Even.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    evends1 = eventen;

                    //events = new ArrayList<WeekViewEvent>();
                    //Log.e("testing", newYear + " " + newMonth);
                    if (eventen != null) {
                        for (Event e : eventen) {
                            if (e.getUserID() == null) {
                                event1.add(e);
                            }
                        }
                        for (Event ev : event1) {
                            Calendar cal = Calendar.getInstance();
                            Calendar cal1 = Calendar.getInstance();
                            //2015-02-16T11:00
                            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = null;
                            //Log.e("testing", ev.getStartTime().toStringRfc3339());
                            try {
                                d = sf.parse(ev.getStartTime().toStringRfc3339());
                                //  Log.e("testing", d.toString());
                            } catch (ParseException es) {
                                es.printStackTrace();
                            }
                            //Log.e("testing", d.toString());
                            //d.setMonth(d.getMonth() + 1);
                            cal.setTime(d);
                            try {
                                d = sf.parse(ev.getEndTime().toStringRfc3339());
                                //  Log.e("testing", d.toString());
                            } catch (ParseException es) {
                                es.printStackTrace();
                            }
                            // d.setMonth(d.getMonth() + 1);
                            cal1.setTime(d);
                            //Log.e("start", cal.toString());
                            //Log.e("end", cal1.toString());

                                weekViews = new WeekViewEvent(ev.getId(), ev.getName(), cal, cal1);
                                //weekViews.setColor(getResources().getColor(R.color.event_color_01));
                                // Log.e("testing", weekViews.getId() + " "+ weekViews.getName()+" "+ weekViews.getStartTime() + " "+weekViews.getEndTime());
                                events.add(weekViews);


                        }
                    }
                ArrayList<WeekViewEvent> wvevents = new ArrayList<WeekViewEvent>();
                    //return events;
                if(mont == 0) {
                    for (WeekViewEvent evend : events) {
                        wvevents.add(evend);
                        Log.e("evend", evend.toString());

                    }
                    mont++;
                }

                    return wvevents;
            }

        });


        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);
        if(Globel.testrol.getMenu() != 2) {
            mWeekView.setEmptyViewClickListener(new WeekView.EmptyViewClickListener() {

                @Override
                public void onEmptyViewClicked(Calendar calendar) {
                    // Log.e("testing", calendar.toString());
                    Intent intent = new Intent(Calender.this, AddEvent.class);
                    Globel.CALENDAR = calendar;
                    startActivity(intent);
                }
            });
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id = item.getItemId();
        //Log.e("testing", id+"");
        switch (item.getItemId()){
            case android.R.id.home:
            // Tapping on top left ActionBar icon navigates "up" to hierarchical parent screen.
            // The parent is defined in the AndroidManifest entry for this activity via the
            // parentActivityName attribute (and via meta-data tag for OS versions before API
            // Level 16). See the "Tasks and Back Stack" guide for more information:
            // http://developer.android.com/guide/components/tasks-and-back-stack.html
            NavUtils.navigateUpFromSameTask(this);
            return true;
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            /*case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;*/
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onEventClick(final WeekViewEvent events, RectF eventRect) {
        ArrayList<Event> activeEvends = new ArrayList<Event>();
        ArrayList<User> usergo = new ArrayList<User>();
        for(int i = 0; i < evends1.size(); i++){
            Event e = evends1.get(i);
            //Log.e("event", e.toString());
            if(e.getName().equals(events.getName()) && e.getStartTime() == evends1.get(i).getStartTime() && e.getEndTime() == evends1.get(i).getEndTime()){
                activeEvends.add(e);
                // Log.e("event", e.toString());
            }
        }
        for(User u : Globel.USER_LIST){
            //Log.e("users1", u.toString());
            for(Event es  : activeEvends){
                //Log.e("users2", u.toString());
                if(es.getUserID() != null) {
                    //Log.e("users3", u.toString());
                    if (es.getUserID().equals(u.getId())) {
                        //Log.e("users4", u.toString());
                        usergo.add(u);
                    }
                }
            }
        }
        Globel.USER_GO = usergo;
        for (User us : usergo){
            s += "Deze Persoon gaat mee \n"+ us.getFirstName() + " " + us.getLastName() + " \n";
        }
        //Toast.makeText(Calender.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
//        Log.e("testing", Endpoint.myApiServiceEE.toString());
        AlertDialog adb = new AlertDialog.Builder(this)
                .setTitle(events.getName())
                .setMessage("Inschrijven ? ")

                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("testing", events.getName() + " " + events.getStartTime().getTime() + " " + events.getEndTime().getTime());
                        //eve.setId(events.getId());
                        eve.setName(events.getName());

                        //events.getStartTime().getTime().setHours(events.getStartTime().getTime().getHours()+1);
                        eve.setStartTime(new DateTime(events.getStartTime().getTime()));
                        eve.setEndTime(new DateTime(events.getEndTime().getTime()));
                        eve.setUserID(Globel.USER_G.getId());
                        //Log.e("testing", eve.toString());
                        //CalenderPersonal cp = new CalenderPersonal();
                        boolean b = false;
                        for(Event es :  evends1){
                            if(eve.getName().equals(es.getName())&&eve.getStartTime().equals(es.getStartTime()) &&eve.getEndTime().equals(es.getEndTime())){
                                b = true;
                            }
                        }
                        Log.e("testerst", b+"");
                        if(!b) {
                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    //Log.e("testing", eve.toString());
                                    try {
                                        Log.e("testing", eve.toString());
                                        //Log.e("testing", eve.getStartTime().getTimeZoneShift()+"");

                                        Log.e("Enpoint", Endpoint.myApiServiceEE.insertEvent(eve).execute().toString());

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            t.start();
                            Intent i = new Intent(Calender.this, Calender.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(), "U bent al eens ingescheven", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Neen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("Lijst",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.e("testing", s);
                                Intent i = new Intent(Calender.this, EvendUserGoList.class);
                                startActivity(i);
                            }
                        })

        .setIcon(android.R.drawable.ic_dialog_info)

                .create();
                //.show();

        adb.show();
        //Toast.makeText(CalenderPersonal.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEventLongPress(final WeekViewEvent event, RectF eventRect) {
        ArrayList<Event> activeEvends = new ArrayList<Event>();
        ArrayList<User> usergo = new ArrayList<User>();
        for(int i = 0; i < evends1.size(); i++){
            Event e = evends1.get(i);
            //Log.e("event", e.toString());
            if(e.getName().equals(event.getName()) && e.getStartTime() == evends1.get(i).getStartTime() && e.getEndTime() == evends1.get(i).getEndTime()){
                activeEvends.add(e);
                // Log.e("event", e.toString());
            }
        }
        for(User u : Globel.USER_LIST){
            //Log.e("users1", u.toString());
            for(Event es  : activeEvends){
                //Log.e("users2", u.toString());
                if(es.getUserID() != null) {
                    //Log.e("users3", u.toString());
                    if (es.getUserID().equals(u.getId())) {
                        //Log.e("users4", u.toString());
                        usergo.add(u);
                    }
                }
            }
        }

        for (User us : usergo){
            s += "Deze Persoon gaat mee "+ us.getFirstName() + " " + us.getLastName() + " \n";
        }
        //Toast.makeText(Calender.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
//        Log.e("testing", Endpoint.myApiServiceEE.toString());
        AlertDialog adb = new AlertDialog.Builder(this)
                .setTitle(event.getName())
                .setMessage("Gaat u mee ? ")

                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("testing", event.getName() + " " + event.getStartTime().getTime() + " " + event.getEndTime().getTime());
                        //eve.setId(events.getId());
                        eve.setName(event.getName());

                        //events.getStartTime().getTime().setHours(events.getStartTime().getTime().getHours()+1);
                        eve.setStartTime(new DateTime(event.getStartTime().getTime()));
                        eve.setEndTime(new DateTime(event.getEndTime().getTime()));
                        eve.setUserID(Globel.USER_G.getId());
                        //Log.e("testing", eve.toString());
                        //CalenderPersonal cp = new CalenderPersonal();
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //Log.e("testing", eve.toString());
                                try {
                                    Log.e("testing", eve.toString());
                                    //Log.e("testing", eve.getStartTime().getTimeZoneShift()+"");

                                    Log.e("Enpoint", Endpoint.myApiServiceEE.insertEvent(eve).execute().toString());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();

                    }
                })
                .setNegativeButton("Neen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("List",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.e("testing", s);
                            }
                        })

                .setIcon(android.R.drawable.ic_dialog_info)

                .create();
        //.show();

        adb.show();
        //Toast.makeText(CalenderPersonal.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        User con = EAA.getItem(i);
        //this.position = position;
        view.setSelected(true);
        lv.setSelection(0);
        lv.setItemChecked(0, true);
        ArrayList<Event> userevent = new ArrayList<Event>();

        try {
            evends = (ArrayList<Event>) Even.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for(Event es:evends){
            //Log.e("User", con.getUserName());
            if(!con.getUserName().equals("Global")) {
                Log.e("Global", con.getUserName());
                Log.e("tzsting", con.getId()+"");
                Log.e("testing", es.getUserID()+"");
                Log.e("testing", con.getId().equals(es.getUserID())+"");
                if (con.getId().equals(es.getUserID())) {
                    Globel.personcal = con;
                    Intent intent = new Intent(Calender.this, CalenderPersonal.class);
                    //intent.putExtra("Event", userevent);
                    startActivity(intent);
                }else{
                    Globel.personcal = con;
                    Intent intent = new Intent(Calender.this, CalenderPersonal.class);
                    //intent.putExtra("Event", userevent);
                    startActivity(intent);
                }
            }else{
                Intent intent = new Intent(Calender.this, Calender.class);
                //intent.putExtra("Event", userevent);
                startActivity(intent);
            }
        }
    }

    public class EventArrayAdapter extends ArrayAdapter<User> {

        private final List<User> list;
        private final Activity context;

        public EventArrayAdapter(Activity context) throws ExecutionException, InterruptedException {
            super(context, R.layout.calender_list_item, EE.get());
            this.context = context;

            list = EE.get();
        }

        public EventArrayAdapter(Activity context, List<User> list) {
            super(context, R.layout.calender_list_item, list);
            this.context = context;
            this.list = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                LayoutInflater inflator = context.getLayoutInflater();
                view = inflator.inflate(R.layout.calender_list_item, null);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.usernaam = (TextView) view.findViewById(R.id.calenderbutton);
                //Log.e("testing", "testing");
                view.setTag(viewHolder);

            } else {
                view = convertView;

            }
            ViewHolder holder = (ViewHolder) view.getTag();

            //Log.e("testing", String.valueOf(list.get(position).getUserName()));
           // Log.e("testing",holder.usernaam.getId()+"");
            holder.usernaam.setText(String.valueOf(list.get(position).getUserName()));

            return view;
        }

        private class ViewHolder {
            protected TextView usernaam;


        }
    }

}


