package com.ehb.xavier.prototype.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehb.xavier.api.menuinsertEndpoint.model.MenuInsert;
import com.ehb.xavier.prototype.Endpoint;
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
 * Created by xavier on 1/02/2015.
 */
public class MenuInserts extends Activity{

    final ArrayList<Button> bs = new ArrayList<Button>();
    public String dateFormat, logInID;
    public String[] weekDays;
    public String NextPreWeekday;
    public String dateFormate;
    public String firstDayOfWeek;
    public String lastDayOfWeek;
    //public CheckBox ochten1, ochten2, ochtend3, middag1, middag2, middag3, avond1, avond2, avond3;
    private EditText ochtend1, ochtend2, ochtend3, middag11, middag12, middag13, middag21, middag22, middag23, middag31, middag32, middag33, avond11, avond12, avond21, avond22, avont31, avond32;
    public int weekDaysCount = 0;

    String tapMargin;
    String buttonHight;
    private TextView textViewPrevDate;
    private TextView textViewDate;
    private TextView textViewNextDate;
    private ImageView buttonBack;
    private ImageView buttonHome;
    private ImageView prevMonth;
    private ImageView nextMonth;
    boolean tests = false;
    AsyncTask<com.ehb.xavier.api.menuinsertEndpoint.model.MenuInsert, Void, List<com.ehb.xavier.api.menuinsertEndpoint.model.MenuInsert>> EATMI = new Endpoint.EndpointsAsyncTaskMenuInsert(this);
    ArrayList<MenuInsert> menus = new ArrayList<MenuInsert>();
    ArrayList<MenuInsert> menuin = new ArrayList<MenuInsert>();
Date date;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menukeuze);

        EATMI = new Endpoint.EndpointsAsyncTaskMenuInsert(getApplicationContext()).execute();
        textViewPrevDate = (TextView) findViewById(R.id.textViewPrevDate);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        final Calendar c = Calendar.getInstance();

        try {
            menus = (ArrayList<MenuInsert>) EATMI.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //System.out.println("Current time => " + c.getTime());


        final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String[] formattedDate = {df.format(c.getTime())};

        textViewDate.setText(formattedDate[0]);




        SimpleDateFormat SDF = new SimpleDateFormat("DD-MMM-yyyy");
        String s  = textViewDate.getText().toString();
        try {
            //Log.e("Date", SDF.parse(s).toString());
            date = SDF.parse(s);
            date.setMonth(date.getMonth()+1);
            //Log.e("date", date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        getEditText();
        setMenulist();
        prevMonth = (ImageView) this.findViewById(R.id.PrefDate);
        prevMonth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                c.add(Calendar.DATE, -1);
                formattedDate[0] = df.format(c.getTime());

                //Log.v("PREVIOUS DATE : ", formattedDate[0]);
                textViewDate.setText(formattedDate[0]);
                setMenulist();
            }
        });

        nextMonth = (ImageView) this.findViewById(R.id.NextDate);
        nextMonth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                c.add(Calendar.DATE, 1);
                formattedDate[0] = df.format(c.getTime());

                //Log.v("NEXT DATE : ", formattedDate[0]);
                textViewDate.setText(formattedDate[0]);
                setMenulist();
            }
        });


        Button Keuzes = (Button) findViewById(R.id.keuzeVanDeDag);
        Keuzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                /*Log.e("Ochtend 1 ", ochtend1.getText().toString());
                Log.e("Ochtend 2 ", ochtend2.getText().toString());
                Log.e("Ochtend 3 ", ochtend3.getText().toString());

                Log.e("Middag 1 ", middag11.getText().toString());
                Log.e("Middag 1 ", middag12.getText().toString());
                Log.e("Middag 1 ", middag13.getText().toString());
                Log.e("Middag 2 ", middag21.getText().toString());
                Log.e("Middag 2 ", middag22.getText().toString());
                Log.e("Middag 2 ", middag23.getText().toString());
                Log.e("Middag 3 ", middag31.getText().toString());
                Log.e("Middag 3 ", middag32.getText().toString());
                Log.e("Middag 3 ", middag33.getText().toString());

                Log.e("Avond 1 ", avond11.getText().toString());
                Log.e("Avond 1 ", avond12.getText().toString());
                Log.e("Avond 2 ", avond21.getText().toString());
                Log.e("Avond 2 ", avond22.getText().toString());
                Log.e("Avond 3 ", avont31.getText().toString());
                Log.e("Avond 3 ", avond32.getText().toString());*/

                MenuInsert mi = new MenuInsert();
                mi.setOchtenKeuze(ochtend1.getText().toString());
                mi.setMiddagKeuze(middag11.getText().toString()+ ", "+ middag12.getText().toString() + ", "+ middag13.getText().toString());
                mi.setAvondKeuze(avond11.getText().toString() + ", " + avond12.getText().toString());
                Log.e("Date", textViewDate.getText().toString());
                String s  = textViewDate.getText().toString();


                SimpleDateFormat SDF = new SimpleDateFormat("DD-MMM-yyyy");

                try {
                    //Log.e("Date", SDF.parse(s).toString());
                    date = SDF.parse(s);
                    date.setMonth(date.getMonth()+1);
                    //Log.e("date", date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mi.setDagkeuze(new DateTime(date));
                Log.e("date", date.toString());

                Log.e("MenuKeuze", mi.toString());
                try {
                    Endpoint.myApiServiceMI.insertMenuKeuze(mi).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mi.setOchtenKeuze(ochtend2.getText().toString());
                mi.setMiddagKeuze(middag21.getText().toString()+ ", "+ middag22.getText().toString() + ", "+ middag23.getText().toString());
                mi.setAvondKeuze(avond21.getText().toString() + ", "+ avond22.getText().toString());
                Log.e("Date", textViewDate.getText().toString());
                s  = textViewDate.getText().toString();


                SDF = new SimpleDateFormat("DD-MMM-yyyy");

                try {
                   // Log.e("Date", SDF.parse(s).toString());
                    date = SDF.parse(s);
                    date.setMonth(date.getMonth()+1);
                    //Log.e("date", date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mi.setDagkeuze(new DateTime(date));
               // Log.e("date", date.toString());

                Log.e("MenuKeuze", mi.toString());
                try {
                    Endpoint.myApiServiceMI.insertMenuKeuze(mi).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mi.setOchtenKeuze(ochtend3.getText().toString());
                mi.setMiddagKeuze(middag31.getText().toString()+ ", "+ middag32.getText().toString() + ", "+ middag33.getText().toString());
                mi.setAvondKeuze(avont31.getText().toString() + ", "+ avond32.getText().toString());
                Log.e("Date", textViewDate.getText().toString());
                s  = textViewDate.getText().toString();


                SDF = new SimpleDateFormat("DD-MMM-yyyy");

                try {
                   // Log.e("Date", SDF.parse(s).toString());
                    date = SDF.parse(s);
                    date.setMonth(date.getMonth()+1);
                   // Log.e("date", date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mi.setDagkeuze(new DateTime(date));
                //Log.e("date", date.toString());

                Log.e("MenuKeuze", mi.toString());
                try {
                    Endpoint.myApiServiceMI.insertMenuKeuze(mi).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                        Intent inents = new Intent(MenuInserts.this, MenuInserts.class);
                        //Intent inent = new Intent(HomeActivity.this, ContactsListActivity.class);
                        // calling an activity using <intent-filter> action name
                        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                        startActivity(inents);
                    }



                }).start();
            }
        });
    }

    private void getEditText(){
        ochtend1 = (EditText) findViewById(R.id.editText);
        ochtend2 = (EditText) findViewById(R.id.editText2);
        ochtend3 = (EditText) findViewById(R.id.editText3);

        middag11 = (EditText) findViewById(R.id.editText4);
        middag12 = (EditText) findViewById(R.id.editText5);
        middag13 = (EditText) findViewById(R.id.editText16);
        middag21 = (EditText) findViewById(R.id.editText6);
        middag22 = (EditText) findViewById(R.id.editText7);
        middag23 = (EditText) findViewById(R.id.editText17);
        middag31 = (EditText) findViewById(R.id.editText8);
        middag32 = (EditText) findViewById(R.id.editText9);
        middag33 = (EditText) findViewById(R.id.editText18);

        avond11 = (EditText) findViewById(R.id.editText10);
        avond12 = (EditText) findViewById(R.id.editText11);
        avond21 = (EditText) findViewById(R.id.editText12);
        avond22 = (EditText) findViewById(R.id.editText13);
        avont31 = (EditText) findViewById(R.id.editText14);
        avond32 = (EditText) findViewById(R.id.editText15);
    }

private void setMenulist(){
    SimpleDateFormat SDF = new SimpleDateFormat("DD-MMM-yyyy");
    String s  = textViewDate.getText().toString();
    try {
       // Log.e("Date", SDF.parse(s).toString());
        date = SDF.parse(s);
        date.setMonth(date.getMonth()+1);
       // Log.e("date", date.toString());
    } catch (ParseException e) {
        e.printStackTrace();
    }

    if(menus!=null){
        //int i =0;
        menuin.clear();
        for(MenuInsert menui : menus){

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-DD");
                Date d = null;
                try {
                    d = sf.parse(menui.getDagkeuze().toStringRfc3339());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                d.setMonth(d.getMonth() + 1);
                //Log.e("testing", d.toString());
                //Log.e("testing", menui.getDagkeuze().toStringRfc3339());
                //Log.e("testing", new DateTime(date).toStringRfc3339());
                //Log.e("testing", date.toString());
                Log.e("testing", d.equals(date) + "");
                if (d.equals(date)) {
                    menuin.add(menui);

                    tests = true;


                } else {
                    ochtend1.setText("");
                    middag11.setText("");
                    middag12.setText("");
                    middag13.setText("");
                    avond11.setText("");
                    avond12.setText("");
                    ochtend2.setText("");
                    middag21.setText("");
                    middag22.setText("");
                    middag23.setText("");
                    avond21.setText("");
                    avond22.setText("");
                    ochtend3.setText("");
                    middag31.setText("");
                    middag32.setText("");
                    middag33.setText("");
                    avont31.setText("");
                    avond32.setText("");

                }
            }
        }
    for(MenuInsert mif : menuin){
        Log.e("testing", mif.toString());
        ochtend1.setText(menuin.get(0).getOchtenKeuze());
        String[] split = menuin.get(0).getMiddagKeuze().split(", ");
        //Log.e("testing", split.length+"");
        if (split.length != 0) {
            middag11.setText(split[0]);
            if(split.length !=1) {
                middag12.setText(split[1]);

                middag13.setText(split[2]);
            }

        }
        String[] split1 = menuin.get(0).getAvondKeuze().split(", ");
        if (split1.length != 0) {
            avond11.setText(split1[0]);
            if(split1.length != 1) {
                avond12.setText(split1[1]);
            }

        }

        ochtend2.setText(menuin.get( 1).getOchtenKeuze());
        String[] split11 = menuin.get( 1).getMiddagKeuze().split(", ");
        if (split11.length != 0) {
            middag21.setText(split11[0]);
            if(split11.length !=1) {
                middag22.setText(split11[1]);
                middag23.setText(split11[2]);
            }
        }
        String[] split12 = menuin.get(1).getAvondKeuze().split(", ");
        if (split12.length != 0) {
            avond21.setText(split12[0]);
            if(split12.length !=1) {
                avond22.setText(split12[1]);
            }
        }

        ochtend3.setText(menuin.get(2).getOchtenKeuze());
        String[] split21 = menuin.get(2).getMiddagKeuze().split(", ");
        if (split21.length != 0) {
            middag31.setText(split21[0]);
            if(split21.length !=1) {
                middag32.setText(split21[1]);
                middag33.setText(split21[2]);
            }
        }
        String[] split22 = menuin.get(2).getAvondKeuze().split(", ");
        if (split22.length != 0) {
            avont31.setText(split22[0]);
            if(split22.length !=1) {
                avond32.setText(split22[1]);
            }
        }
    }


}



}
