package com.ehb.xavier.prototype.User;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ehb.xavier.api.rolEndpoint.model.Rol;
import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.R;
import com.google.api.client.util.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xavier on 14/12/2014.
 */
public class Register extends Activity {

    static final int DATE_PICKER_ID = 1111;
    List<Rol> rollen = null;
    private EditText user, pass, passcon, first, last, kamernr, email, gbdag, gbmaand, gbjaar;
    private Spinner rol;
    private Button mRegister;
    private AsyncTask<Rol, Void, List<Rol>> EEATR;
    // Progress Dialog
    private AsyncTask<User, Void, List<User>> EEATU;
    private DatePicker Output;
    private Button changeDate;
    private int year;
    private int month;
    private int day;
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        i1  = (ImageView) findViewById(R.id.imageView7);

        user = (EditText) findViewById(R.id.gebruikersnaam);
        pass = (EditText) findViewById(R.id.wachtwoord);
        passcon = (EditText) findViewById(R.id.Confirmwachtwoord);
        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        rol = (Spinner) findViewById(R.id.rolkeuze);
        kamernr = (EditText) findViewById(R.id.kamernr);
        //gbdate = (EditText) findViewById(R.id.gbdate);
        gbdag = (EditText) findViewById(R.id.gbdateDag);
        gbmaand = (EditText) findViewById(R.id.gbdateMaand);
        gbjaar = (EditText) findViewById(R.id.gbdateJaar);
        email = (EditText) findViewById(R.id.email);
        EEATR = new Endpoint.EndpointsAsyncTaskRol(getApplicationContext()).execute();
        //Output = (DatePicker) findViewById(R.id.gdate);
        //changeDate = (Button) findViewById(R.id.changeDate);

        // Get current date by calender


        // Button listener to show date picker dialog


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    rollen = EEATR.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Rol us = new Rol();
                if (rollen != null) {
                    for (Rol u : rollen) {

                        Log.e("user", u.toString());


                    }
                }

                try {
                    rollen = EEATR.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                for (Rol r : rollen) {
                    Log.e("Rol", r.toString());
                }





            }
        });
        t.start();
        t.run();
        roladdaptor();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Tapping on top left ActionBar icon navigates "up" to hierarchical parent screen.
                // The parent is defined in the AndroidManifest entry for this activity via the
                // parentActivityName attribute (and via meta-data tag for OS versions before API
                // Level 16). See the "Tasks and Back Stack" guide for more information:
                // http://developer.android.com/guide/components/tasks-and-back-stack.html
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        // Otherwise, pass the item to the super implementation for handling, as described in the
        // documentation.
        return super.onOptionsItemSelected(item);
    }
    User u = new User();
    boolean t = true;
    Rol r = new Rol();
    private void roladdaptor(){

        i1.setVisibility(View.INVISIBLE);
        ArrayList<Rol> rons = new ArrayList<Rol>();
        rons.addAll(rollen);
        rol.setAdapter(new MySimpleArrayAdapter(getApplicationContext(), R.layout.single_rol, rons));
        mRegister = (Button) findViewById(R.id.persoontoevoegen);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i1.setVisibility(View.INVISIBLE);
                t = true;
                Log.e(pass.getText().toString(), passcon.getText().toString());
                if (pass.getText().toString().equals(passcon.getText().toString())) {
                    EEATU = new Endpoint.EndpointsAsyncTaskUser(getApplicationContext()).execute();
                    // TODO Auto-generated method stub

                    u.setUserName(user.getText().toString());
                    u.setPassWord(pass.getText().toString());
                    u.setFirstName(first.getText().toString());
                    u.setLastName(last.getText().toString());
                    Log.e("Rol", rol.getSelectedItem().toString());
                    Object o = rol.getSelectedItem();
                    Log.e("Rolid", o.toString());
                    r=(Rol) o;
                    Log.e("Rols", r.toString());
                    if (!kamernr.getText().toString().isEmpty()) {
                        u.setKamernr(Integer.valueOf(kamernr.getText().toString()));
                    }
                    if (!email.getText().toString().isEmpty()) {
                        if (email.getText().toString() != null) {
                            if (isEmailValid(email.getText().toString())) {
                                u.setEmail(email.getText().toString());
                                // t = true;
                                i1.setVisibility(View.INVISIBLE);
                            } else {
                                t = false;

                                i1.setVisibility(View.VISIBLE);
                            }
                        } else {
                            i1.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        i1.setVisibility(View.INVISIBLE);
                    }
                    //u.setEmail(email.getText().toString());
                    if (!gbdag.getText().toString().isEmpty() && !gbmaand.getText().toString().isEmpty() && !gbjaar.getText().toString().isEmpty()) {
                        u.setGeboortedatum(new DateTime(new Date(Integer.valueOf(gbjaar.getText().toString()), Integer.valueOf(gbmaand.getText().toString())
                                , Integer.valueOf(gbdag.getText().toString()))));
                    }
                    //rol.getSelectedItem()
                    new Thread(new Runnable() {
                        @Override
                        public void run() {      //
                            u.setRol(r.getId());
                            if (t) {
                                try {
                                    Log.e("Endpoind User", Endpoint.myApiServiceU.insertUser(u).execute().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Intent inent = new Intent(Register.this, UserlistActivity.class);

                                // calling an activity using <intent-filter> action name
                                //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                                startActivity(inent);
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(), "Wachtwoord niet gelijk",
                            Toast.LENGTH_SHORT).show();
                }


            }

        });
    }
    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
