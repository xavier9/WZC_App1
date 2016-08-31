package com.ehb.xavier.prototype.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ehb.xavier.api.menuinsertEndpoint.model.MenuInsert;
import com.ehb.xavier.api.menukeuzeEndpoint.model.MenuKeuze;
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
 * Created by xavier on 20/11/2014.
 */

public class Menus extends Activity{

    public CheckBox ochten1, ochten2, ochtend3, middag1, middag2, middag3, avond1, avond2, avond3;
    private TextView textViewPrevDate;
    private TextView textViewDate;
    private TextView textViewNextDate;
    private ImageView prevMonth;
    private ImageView nextMonth;
    Date date = null;
boolean chechmenu = false;
    Calendar c;
    AsyncTask<MenuKeuze, Void, List<MenuKeuze>> EATMK;
    AsyncTask<MenuInsert, Void, List<MenuInsert>> EATMI;
    /**
     * Checkbox listener to check 1 out of tree
     * */
    private CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
            if(isChecked) {
                switch (arg0.getId()) {
                    case R.id.checkBox4:
                        ochten1.setChecked(true);
                        ochten2.setChecked(false);
                        ochtend3.setChecked(false);
                        break;
                    case R.id.checkBox5:
                        ochten2.setChecked(true);
                        ochten1.setChecked(false);
                        ochtend3.setChecked(false);
                        break;
                    case R.id.checkBox6:
                        ochtend3.setChecked(true);
                        ochten1.setChecked(false);
                        ochten2.setChecked(false);
                        break;
                }

                switch (arg0.getId()) {
                    case R.id.checkBox:
                        middag1.setChecked(true);
                        middag2.setChecked(false);
                        middag3.setChecked(false);
                        break;
                    case R.id.checkBox2:
                        middag2.setChecked(true);
                        middag1.setChecked(false);
                        middag3.setChecked(false);
                        break;
                    case R.id.checkBox3:
                        middag3.setChecked(true);
                        middag1.setChecked(false);
                        middag2.setChecked(false);
                        break;
                }

                switch (arg0.getId()) {
                    case R.id.checkBox7:
                        avond1.setChecked(true);
                        avond2.setChecked(false);
                        avond3.setChecked(false);
                        break;
                    case R.id.checkBox8:
                        avond2.setChecked(true);
                        avond1.setChecked(false);
                        avond3.setChecked(false);
                        break;
                    case R.id.checkBox9:
                        avond3.setChecked(true);
                        avond1.setChecked(false);
                        avond2.setChecked(false);
                        break;
                }
            }

        }
    };
    Mail m;
    ArrayList<MenuInsert> menuins = new ArrayList<MenuInsert>();
    ArrayList<MenuInsert> menuint = new ArrayList<MenuInsert>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        textViewPrevDate = (TextView) findViewById(R.id.textViewPrevDate);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewNextDate = (TextView) findViewById(R.id.textViewNextDate);


        //lastDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[6]);
        final Calendar c = Calendar.getInstance();

        //System.out.println("Current time => " + c.getTime());

        final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String[] formattedDate = {df.format(c.getTime())};

        textViewDate.setText(formattedDate[0]);


        prevMonth = (ImageView) this.findViewById(R.id.PrefDate);
        prevMonth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                c.add(Calendar.DATE, -1);
                formattedDate[0] = df.format(c.getTime());

                //Log.v("PREVIOUS DATE : ", formattedDate[0]);
                textViewDate.setText(formattedDate[0]);
                setTextCheckBox();
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
                setTextCheckBox();
            }
        });
        EATMI = new Endpoint.EndpointsAsyncTaskMenuInsert(getApplicationContext()).execute();
         menuins = new ArrayList<MenuInsert>();
        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {


        try {
            menuins = (ArrayList<MenuInsert>) EATMI.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ochten1 = (CheckBox) findViewById(R.id.checkBox4);
        ochten2 = (CheckBox) findViewById(R.id.checkBox5);
        ochtend3 = (CheckBox) findViewById(R.id.checkBox6);
        ochten1.setOnCheckedChangeListener(listener);
        ochten2.setOnCheckedChangeListener(listener);
        ochtend3.setOnCheckedChangeListener(listener);

        middag1 = (CheckBox) findViewById(R.id.checkBox);
        middag2 = (CheckBox) findViewById(R.id.checkBox2);
        middag3 = (CheckBox) findViewById(R.id.checkBox3);
        middag1.setOnCheckedChangeListener(listener);
        middag2.setOnCheckedChangeListener(listener);
        middag3.setOnCheckedChangeListener(listener);

        avond1 = (CheckBox) findViewById(R.id.checkBox7);
        avond2 = (CheckBox) findViewById(R.id.checkBox8);
        avond3 = (CheckBox) findViewById(R.id.checkBox9);
        avond1.setOnCheckedChangeListener(listener);
        avond2.setOnCheckedChangeListener(listener);
        avond3.setOnCheckedChangeListener(listener);




            }
        });
        t.start();
        t.run();
        setTextCheckBox();
        /**
         * Set Menu Items in DB
         * **/
        EATMK = new Endpoint.EndpointsAsyncTaskMenuKeuze(this).execute();
        Button select = (Button) findViewById(R.id.findSelected);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ochtend = null, Middag = null, Avond = null;
                if (ochten1.isChecked() || ochten2.isChecked() || ochtend3.isChecked()) {
                    if(ochten1.isChecked() ) {
                        Ochtend = "Ochtend: " + ochten1.getText() + "\n";
                    }else if(ochten2.isChecked()){
                        Ochtend = "Ochtend: " + ochten2.getText() + "\n";
                    }else if(ochtend3.isChecked()){
                        Ochtend = "Ochtend: " + ochtend3.getText() + "\n";
                    }
                }
                if (middag1.isChecked() || middag2.isChecked() || middag3.isChecked()) {
                    if(middag1.isChecked() ) {
                        Middag = "\n Middag: " + middag1.getText() + "\n";
                    }else if(middag2.isChecked()){
                        Middag = "\n Middag: " + middag2.getText() + "\n";
                    }else if(middag3.isChecked()){
                        Middag = "\n Middag: " + middag3.getText() + "\n";
                    }
                }
                if (avond1.isChecked() || avond2.isChecked() || avond3.isChecked()) {
                    if(avond1.isChecked()) {
                        Avond = "\n Avond: " + avond1.getText() + "\n";
                    }else if(avond2.isChecked()){
                        Avond = "\n Avond: " + avond2.getText() + "\n";
                    }else if(avond3.isChecked()){
                        Avond = "\n Avond: " + avond2.getText() + "\n";
                    }
                }
                chechmenu = false;
                final MenuKeuze mk = new MenuKeuze();
                mk.setUserID(Globel.USER_G.getId());
                if(Ochtend != null) {
                    mk.setOchtendKeuze(Ochtend);
                    chechmenu = true;
                }else{
                    chechmenu = false;
                }
                if(Middag != null) {
                    mk.setMiddagKeuze(Middag);
                    chechmenu = true;
                }else{
                    chechmenu = false;
                }
                if(Avond != null) {
                    mk.setAvondKeuze(Avond);
                    chechmenu = true;
                }else{
                    chechmenu = false;
                }
                Log.e("Date", textViewDate.getText().toString());
                String s  = textViewDate.getText().toString();


                        SimpleDateFormat SDF = new SimpleDateFormat("DD-MMM-yyyy");

                try {
                    Log.e("Date", SDF.parse(s).toString());
                    date = SDF.parse(s);
                    date.setMonth(date.getMonth()+1);
                    Log.e("date", date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                Log.e("date", date.toString());
                mk.setDag(new DateTime(date.getDate()));
                Log.e("MenuKeuze", mk.toString());
                if(chechmenu) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {


                            try {
                                MenuKeuze mks = Endpoint.myApiServiceMK.insertMenuKeuze(mk).execute();
                                Log.e("MenuKeuze", mks.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    m = new Mail("xavier_deaguirre002@hormail.com", "Varken90");

                    String[] toArr = {"xavier_deaguirre002@hormail.com"};
                    m.setTo(toArr);
                    m.setFrom("xavier_deaguirre002@hormail.com");
                    m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
                    m.setBody("Email body.");
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            m.addAttachment("/sdcard/filelocation");

                            if (m.send()) {
                                Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                            Log.e("MailApp", "Could not send email", e);
                        }
                    }
                }).start();*/
                    /**
                     * Setup menu sending to keuken
                     * */

                    Intent intentEmail = new Intent(Intent.ACTION_SEND);

                    //intentEmail.putExtra(Intent.Extra_F)
                    intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"xavier_deaguirre002@hotmail.com"});


                    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Eten voor datum " + textViewDate.getText().toString() + " van " + Globel.USER_G.getFirstName() + " " + Globel.USER_G.getLastName());


                    intentEmail.putExtra(Intent.EXTRA_TEXT, Ochtend + Middag + Avond);


                    intentEmail.setType("message/rfc822");


                    startActivity(Intent.createChooser(intentEmail, "Choose an email provider :"));
                }else{
                    Toast.makeText(getApplicationContext(), "Gelieve voor elke maaltijd uw keuze door te geven", Toast.LENGTH_LONG).show();
                }

/*}
                try {
                    GMailSender sender = new GMailSender("xavier_deaguirre002@hotmail.com", "Varken90");
                    sender.sendMail("Eten van "+ Globel.USER_G.getUserName() + " voor "+ textViewDate.getText().toString()  ,
                            Ochtend + Middag + Avond,
                            "xavier_deaguirre002@hotmai.com",
                            null);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }*/

            }



        });


    }

    private void setTextCheckBox() {
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
        if(menuins != null){
            //int i =0;
            menuint.clear();

            for(MenuInsert menui : menuins){

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-DD");
                Date d = null;
                try {
                    d = sf.parse(menui.getDagkeuze().toStringRfc3339());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                d.setMonth(d.getMonth() + 1);
                Log.e("testing", d.toString());
                //Log.e("testing", menui.getDagkeuze().toStringRfc3339());
                //Log.e("testing", new DateTime(date).toStringRfc3339());
                Log.e("testing", date.toString());
                Log.e("testing", d.equals(date) + "");
                if (d.equals(date)) {
                    menuint.add(menui);




                } else {//menuins.clear();
                    ochten1.setText("");
                    middag1.setText("");
                    //middag12.setText("");
                   // middag13.setText("");
                    avond1.setText("");
                    avond2.setText("");
                    ochten2.setText("");
                    middag2.setText("");
                    //middag22.setText("");
                    //middag23.setText("");
                    //avond21.setText("");
                    avond2.setText("");
                    ochtend3.setText("");
                    middag3.setText("");
                    //middag32.setText("");
                    //middag33.setText("");
                    //avont3.setText("");
                    avond3.setText("");

                }
            }
        }

            for (MenuInsert mif : menuint) {
                Log.e("Menu", mif.toString());
                ochten1.setText(menuint.get(0).getOchtenKeuze());
                middag1.setText(menuint.get(0).getMiddagKeuze());

                avond1.setText(menuint.get(0).getAvondKeuze());


                ochten2.setText(menuint.get(1).getOchtenKeuze());
                middag2.setText(menuint.get(1).getMiddagKeuze());

                avond2.setText(menuint.get(1).getAvondKeuze());

                ochtend3.setText(menuint.get(2).getOchtenKeuze());
                middag3.setText(menuint.get(2).getMiddagKeuze());

                avond3.setText(menuint.get(2).getAvondKeuze());
            }


    }
}













