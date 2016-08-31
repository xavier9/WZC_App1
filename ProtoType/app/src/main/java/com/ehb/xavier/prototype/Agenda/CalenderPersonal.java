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

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.ehb.xavier.api.eventEndpoint.model.Event;
import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by xavier on 14/02/2015.
 */
public class CalenderPersonal extends Activity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener, AdapterView.OnItemClickListener {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_WEEK_VIEW;
    private WeekView mWeekView1;
    AsyncTask<User, Void, List<User>> EE;
    ArrayList<Event> eventen = new ArrayList<Event>();
    AsyncTask<Event, Void, List<Event>> Even;
    ListView lv = null;
    EventArrayAdapter EAA;
    int mont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        EE = new Endpoint.EndpointsAsyncTaskUser(this).execute();
        // Get a reference for the week view in the layout.
        mWeekView1 = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView1.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView1.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView1.setEventLongPressListener(this);
        Even = new Endpoint.EndpointsAsyncTaskEvent(this).execute();

        try {
            eventen = (ArrayList<Event>) Even.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<User> evend = new ArrayList<User>();

        Globel.addglobal=false;
        if(!Globel.addglobal){
            evend.add(Globel.USER_G);
            User us = new User();
            us.setUserName("Global");
            evend.add(us);
            Globel.addglobal = true;
        }

        for(User e: evend){
            Log.e("testing", e.toString());
        }
        EAA=    new EventArrayAdapter(this, evend);

        lv = (ListView) findViewById(R.id.calenderlist);
        lv.setAdapter(EAA);
        lv.setOnItemClickListener(this);
        lv.setItemChecked(0, true);
Globel.customer1 = Globel.USER_G;
        mWeekView1.setEmptyViewClickListener(new WeekView.EmptyViewClickListener() {

            @Override
            public void onEmptyViewClicked(Calendar calendar) {
                // Log.e("testing", calendar.toString());
                Intent intent = new Intent(CalenderPersonal.this, AddEvent.class);
                Globel.CALENDAR = calendar;
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
            // Tapping on top left ActionBar icon navigates "up" to hierarchical parent screen.
            // The parent is defined in the AndroidManifest entry for this activity via the
            // parentActivityName attribute (and via meta-data tag for OS versions before API
            // Level 16). See the "Tasks and Back Stack" guide for more information:
            // http://developer.android.com/guide/components/tasks-and-back-stack.html
            NavUtils.navigateUpFromSameTask(this);
            return true;
            case R.id.action_today:
                mWeekView1.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView1.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView1.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView1.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView1.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
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
                    mWeekView1.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView1.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView1.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView1.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
    WeekViewEvent wve = new WeekViewEvent();
    public void setEvents(WeekViewEvent w){
        Log.e("testing", w.toString());
       wve = w;
    }
    public WeekViewEvent getEvents(){
        return wve;
    }
    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.
        ArrayList<Event> userevent = new ArrayList<Event>();
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        Log.e("testing", newYear + " " + newMonth);

        for(Event e : eventen){
            if(Globel.USER_G.getId().equals(e.getUserID())){
                userevent.add(e);
            }
        }
        if(!userevent.isEmpty())
            for(Event e: userevent){
                Calendar cal = Calendar.getInstance();
                Calendar cal1 = Calendar.getInstance();
                //2015-02-16T11:00
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date d = null;
                // Log.e("testing", e.getStartTime().toStringRfc3339());
                try {
                    d = sf.parse(e.getStartTime().toStringRfc3339());
                    //  Log.e("testing", d.toString());
                } catch (ParseException es) {
                    es.printStackTrace();
                }
                // Log.e("testing", d.toString());
                //d.setMonth(d.getMonth() + 1);
                cal.setTime(d);
                try {
                    d = sf.parse(e.getEndTime().toStringRfc3339());
                    //  Log.e("testing", d.toString());
                } catch (ParseException es) {
                    es.printStackTrace();
                }
                // d.setMonth(d.getMonth() + 1);
                cal1.setTime(d);
                //  Log.e("testing", cal.toString());
                //  Log.e("testing", cal1.toString());
                WeekViewEvent weekViews = new WeekViewEvent(e.getId(), e.getName(), cal, cal1);
                // Log.e("testing", weekViews.getId() + " "+ weekViews.getName()+" "+ weekViews.getStartTime() + " "+weekViews.getEndTime());
                events.add(weekViews);
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
        //events.add(getEvents());

        //return events;
    }



    private String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(final WeekViewEvent event, RectF eventRect) {
        new AlertDialog.Builder(this)
                .setTitle(event.getName())
                .setMessage("Verwijder?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {


                            //Log.e("event", event.getId()+"");
                        try {
                            Log.e("event", event.getId()+"");
                            Endpoint.myApiServiceEE.removeEvent(event.getId()).execute();
                            Log.e("event", event.getId()+"");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                            }
                        }).start();
                        Intent i = new Intent(CalenderPersonal.this, CalenderPersonal.class);
                       startActivity(i);
                    }
                })
                .setNegativeButton("Neen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("testing", "no");
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
        //Toast.makeText(CalenderPersonal.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        new AlertDialog.Builder(this)
                .setTitle(event.getName())
                .setMessage("Gaat u mee ?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("testing", "yes");
                    }
                })
                .setNegativeButton("Neen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("testing", "no");
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
        //Toast.makeText(CalenderPersonal.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        User con = EAA.getItem(i);
        //this.position = position;
        view.setSelected(true);
        lv.setSelection(1);
        lv.setItemChecked(1, true);
        ArrayList<Event> userevent = new ArrayList<Event>();
        ArrayList<Event> evends = new ArrayList<Event>();
        try {
            evends = (ArrayList<Event>) Even.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Event ed: evends){
            Log.e("User", con.getUserName());
            if(!con.getUserName().equals("Global")) {
                Log.e("Global", con.getUserName());
                if (con.getId().equals(ed.getUserID())) {
                    Globel.personcal = con;
                    Intent intent = new Intent(CalenderPersonal.this, CalenderPersonal.class);
                    //intent.putExtra("Event", userevent);
                    startActivity(intent);
                }
            }else{
                Globel.personcal = con;
                Intent intent = new Intent(CalenderPersonal.this, Calender.class);
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
                Log.e("testing", "testing");
                view.setTag(viewHolder);

            } else {
                view = convertView;

            }
            ViewHolder holder = (ViewHolder) view.getTag();

            Log.e("testing", String.valueOf(list.get(position).getUserName()));
            Log.e("testing",holder.usernaam.getId()+"");
            holder.usernaam.setText(String.valueOf(list.get(position).getUserName()));

            return view;
        }

        private class ViewHolder {
            protected TextView usernaam;


        }
    }
}
