package com.ehb.xavier.prototype.User;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ehb.xavier.api.rolEndpoint.model.Rol;
import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.MainActivity;
import com.ehb.xavier.prototype.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by xavier on 14/12/2014.
 * Dit wordt gebruikt voor in tel logen en elke gebruiker te verificeren
 *
 */
public class Login extends Activity {

    List<User> users = null;
    private EditText user, pass;
    private Button mSubmit;
    private AsyncTask<User, Void, List<User>> EEATU;
    private AsyncTask<Rol, Void, List<Rol>> EEATR;
    ArrayList<Rol> r = new ArrayList<Rol>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Globel.addglobal = false;
        // setup input fields
        new Thread(new Runnable() {
            @Override
            public void run() {

                EEATU = new Endpoint.EndpointsAsyncTaskUser(getApplicationContext()).execute();
                EEATR = new Endpoint.EndpointsAsyncTaskRol(getApplicationContext()).execute();
                // cdb.addData(new Contact());
                // cdb.getSimpleDataDao().executeRaw("Alter table 'contact' ADD COLUMN email varchar2(20);");


                try {
                    users = EEATU.get();
                    Globel.USER_LIST = (ArrayList<User>) users;
                    if (users != null) {
                        for (User c : users) {
                            //Log.e("uses", c.toString());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                user = (EditText) findViewById(R.id.username);
                pass = (EditText) findViewById(R.id.password);

                // setup buttons
                mSubmit = (Button) findViewById(R.id.login);
                //mRegister = (Button) findViewById(R.id.register);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User us = new User();
                        if (users != null) {
                            for (User u : users) {
                                if (u.getUserName() == "admin" && u.getPassWord() == "admin") {
                                    //Log.e("user", u.toString());

                                    us = u;
                                }else{
                                    us = u;
                                }

                            }
                        } else {
                            if (us.isEmpty()) {
                                Rol r = new Rol();
                                Rol ro = null;
                                r.setNaam("admin");
                                r.setMenu(1);
                                try {
                                    ro = Endpoint.myApiServiceR.insertRol(r).execute();
                                    Log.e("Rol", ro.toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                us.setUserName("admin");
                                us.setPassWord("admin");
                                us.setRol(ro.getId());
                                try {
                                    Log.e("Endpoind User3", Endpoint.myApiServiceU.insertUser(us).execute().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        if (
                                us.isEmpty()) {

                            if (us.getUserName() != "admin" && us.getPassWord() != "admin") {
                                Rol r = new Rol();
                                Rol ro = null;
                                r.setNaam("admin");
                                r.setMenu(1);
                                try {
                                    ro = Endpoint.myApiServiceR.insertRol(r).execute();
                                    Log.e("Rol", ro.toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                us.setUserName("admin");
                                us.setPassWord("admin");
                                us.setRol(ro.getId());
                                try {
                                    Log.e("Endpoind User1", Endpoint.myApiServiceU.insertUser(us).execute().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        try {
                            users = EEATU.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();


                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                List<Rol> rols = null;
                                try {
                                    rols = EEATR.get();
                                    Globel.ROL_LIST = (ArrayList<Rol>) rols;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }


                                if (rols != null) {
                                    if (rols.size() == 1) {
                                        Log.e("Rol", Globel.rols + "");
                                        Globel.rols++;
                                        Rol r = new Rol();
                                        Rol ro = null;
                                        r.setNaam("senior");
                                        r.setMenu(2);
                                        try {
                                            Log.e("Rol", Globel.rols + "");
                                            ro = Endpoint.myApiServiceR.insertRol(r).execute();
                                            Log.e("Rol", ro.toString());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        r.setNaam("Verzorgend personeel");
                                        r.setMenu(1);
                                        try {
                                            ro = Endpoint.myApiServiceR.insertRol(r).execute();
                                            Log.e("Rol", ro.toString());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        r.setNaam("Keuken personeel");
                                        r.setMenu(1);
                                        try {
                                            ro = Endpoint.myApiServiceR.insertRol(r).execute();
                                            Log.e("Rol", ro.toString());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            }
                        }).start();
                    }
                }).start();

                /*for(User ust : users){
                    Log.e("users", ust.toString());
                }*/
                // register listeners
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                mSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User u = null;

                        for (int i = 0; i < users.size(); i++) {
                           // Log.e("Userarray", users.get(i).toString());
                            if (users.get(i).getUserName().equals(user.getText().toString()) && users.get(i).getPassWord().equals(pass.getText().toString())) {
                               // Log.e("User", users.get(i).getUserName() + " " + users.get(i).getPassWord());
                                Globel.USER_G = users.get(i);
                               // Log.e("testing", Globel.USER_G.toString());

                               Thread t= new Thread(new Runnable() {
                                    @Override
                                    public void run() {


                                try {
                                    r = (ArrayList<Rol>) EEATR.get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                            for(Rol rs : r){
                                if(Globel.USER_G.getRol().equals(rs.getId())){
                                    Globel.testrol = rs;
                                }
                            }
//                        Log.e("menu", r.getMenu() + "");

//                    Log.e("menu", r.getMenu() + "");
                                        //Globel.testrol = r;
                                        Log.e("testrol", Globel.testrol+"");
                                    }
                                });
                                t.start();
//                                t.run();
                                u = users.get(i);
                                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(in);
                            }

                        }
                        if (u == null) {
                            Toast.makeText(getApplicationContext(), "Gegevesn kloppen niet",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }


                });
                    }
                }).start();
            }

        }).start();
    }

}