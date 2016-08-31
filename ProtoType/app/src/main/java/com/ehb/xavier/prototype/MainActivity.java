package com.ehb.xavier.prototype;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ehb.xavier.api.rolEndpoint.model.Rol;
import com.ehb.xavier.prototype.Agenda.CalenderPersonal;
import com.ehb.xavier.prototype.Contact.ContactsListActivity;
import com.ehb.xavier.prototype.Menu.MenuInserts;
import com.ehb.xavier.prototype.Menu.Menus;
import com.ehb.xavier.prototype.User.Login;
import com.ehb.xavier.prototype.User.UserlistActivity;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity {

    Bitmap[] images = new Bitmap[3];
    int i;

    /**
     * Called when the activity is first created.
     */

    private ArrayList<ImageButton> ibs = new ArrayList<ImageButton>();
    private ArrayList<Integer> draw = new ArrayList<Integer>();
    Rol r = null;
    Rol rol = null;
    int tag = 0;
    int rs = 0;
    Intent inent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout linear = (LinearLayout) findViewById(R.id.eersteset);
        final LinearLayout ll = new LinearLayout(getApplicationContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        //Intent inent = new Intent(MainActivity.this, ImageUploade.class);

        // calling an activity using <intent-filter> action name
        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");
        new Thread(new Runnable() {
            @Override
            public void run() {


                Log.e("User", Globel.USER_G.toString());
                Log.e("User", Globel.USER_G.getRol() + "");
                try {
                    r = Endpoint.myApiServiceR.findRol(Globel.USER_G.getRol()).execute();
//                    Log.e("menu", r.getMenu() + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Log.e("menu", r.getMenu() + "");
            }
        }).start();

        //getActionBar().setTitle(Globel.USER_G.getUserName());
//                MenuItem mi = (MenuItem) findViewById(R.id.User);
        // mi.setTitle(Globel.USER_G.getUserName());

        //startActivity(inent);

        //Images


        draw.add(R.drawable.ic_agend);
        draw.add(R.drawable.ic_calling);
        draw.add(R.drawable.ic_film);
        draw.add(R.drawable.ic_menu);
        draw.add(R.drawable.ic_registers);
        draw.add(R.drawable.ic_beheer);
        int mt = -600;
        int ml = 200;
        //Het aantal
        for (int j = 0; j <= 6; j++) {
            //scrl.addView(ll);
            int tree = j % 3;
            //Log.i("tree", tree + "");
            final ImageButton add_btn = new ImageButton(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
            //add_btn.setBackgroundResource(R.drawable.ic_agenda);
            add_btn.setLayoutParams(params);
            if (j > 2) {
                if (j < 6) {
                    if (tree == 0) {
                        params.setMargins(ml, mt, 0, 0);
                    } else
                        params.setMargins(ml, 0, 0, 0);

                    add_btn.setLayoutParams(params);

                } else {
                    //if(j==6){mt=-600;}
                    if (tree == 0) {
                        params.setMargins(ml + 200, mt, 0, 0);

                    } else
                        params.setMargins(ml + 200, 0, 0, 0);


                    add_btn.setLayoutParams(params);
                }

            }
            //onCreate
            final int finalJ = j;
            add_btn.setTag(finalJ);
            add_btn.setId(finalJ);
            tag = (int) add_btn.getId();
            //Log.e("Tag", tag + "");


            //Log.e("User", Globel.USER_G.toString());
            //Log.e("User", Globel.USER_G.getRol() + "");
            new Thread(new Runnable() {
                @Override
                public void run() {


                    try {
                        r = Endpoint.myApiServiceR.findRol(Globel.USER_G.getRol()).execute();
//                        Log.e("menu", r.getMenu() + "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    Log.e("menu", r.getMenu() + "");
                    rol = r;
                }
            }).start();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    //Log.e("Menu", Globel.USER_G.getRol() + "");
                    try {
                        rs = Endpoint.myApiServiceR.findRol(Globel.USER_G.getRol()).execute().getMenu();
                        //Log.e("Menu1", rs+"");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();




            switch (tag) {
                case 0:


                case 1:

                    break;
                case 2:
                    //add_btn.setBackgroundResource(draw.get(1));
                    add_btn.setVisibility(View.INVISIBLE);
                    //inent = new Intent(MainActivity.this, Film.class);

                    // calling an activity using <intent-filter> action name
                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                    // startActivity(inent);
                    break;
                case 3:

                    break;
                case 4:
                    // t.run();

                   // Log.e("menu", Globel.testrol.getMenu() + "");
                    rs= Globel.testrol.getMenu();
                   // Log.e("Menu2", rs+"");
                    if (rs == 2) {
                        add_btn.setVisibility(View.INVISIBLE);
                    }
                    //if (r.getMenu() == 2) {
                    //  add_btn.setVisibility(View.INVISIBLE);
                    // }


                    break;
                case 5:
                    //add_btn.setBackgroundResource(draw.get(4));
                    //add_btn.setVisibility(View.INVISIBLE);

                    add_btn.setVisibility(View.INVISIBLE);

                    break;

                //Button toevoegen hiervoor
                default:
                    add_btn.setVisibility(View.INVISIBLE);

            }


            if (finalJ < 6) {
                add_btn.setBackgroundResource(draw.get(finalJ));
            }


            ll.addView(add_btn);
            //onClick
            add_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {


                            int tag = (int) add_btn.getId();
                            // Log.i("tag", tag + "");

                            switch (tag) {
                                case 0:


                                    inent = new Intent(MainActivity.this, CalenderPersonal.class);

                                    // calling an activity using <intent-filter> action name
                                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                                    startActivity(inent);
                                    break;

                                case 1:
                                    // add_btn.setBackgroundResource(draw.get(0));
                                    inent = new Intent(MainActivity.this, ContactsListActivity.class);

                                    // calling an activity using <intent-filter> action name
                                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                                    startActivity(inent);
                                    break;
                                case 2:
                                    //add_btn.setBackgroundResource(draw.get(1));
                                    // add_btn.setVisibility(View.INVISIBLE);
                                    //inent = new Intent(MainActivity.this, Film.class);

                                    // calling an activity using <intent-filter> action name
                                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                                    // startActivity(inent);
                                    break;
                                case 3:
                                    //add_btn.setBackgroundResource(draw.get(2));
                                    inent = null;
                                    try {
                                        if (Endpoint.myApiServiceR.findRol(Globel.USER_G.getRol()).execute().getMenu() == 2) {
                                            inent = new Intent(MainActivity.this, Menus.class);
                                        } else {
                                            inent = new Intent(getApplicationContext(), MenuInserts.class);
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //Log.e("testing", inent.toString());
                                    // calling an activity using <intent-filter> action name
                                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                                    startActivity(inent);
                                    break;
                                case 4:
                                    //add_btn.setBackgroundResource(draw.get(3));
                                    inent = new Intent(MainActivity.this, UserlistActivity.class);

                                    // calling an activity using <intent-filter> action name
                                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                                    startActivity(inent);
                                    break;
                                case 5:
                                    //add_btn.setBackgroundResource(draw.get(4));
                                    inent = new Intent(MainActivity.this, CalenderPersonal.class);

                                    // calling an activity using <intent-filter> action name
                                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                                    startActivity(inent);
                                    break;
                                //Als je op de button clickt moet het hier achter

                            }

                            // Toast.makeText(MainActivity.this, add_btn.getTag() + "", Toast.LENGTH_SHORT).show();
                        }

                    }).start();

                }
            });


        }

        this.setContentView(ll);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        //Log.e("Username", Globel.USER_G.getUserName());
        //Log.e("menuItem", R.id.User + "");
//        menu.findItem(R.id.User).setTitle(Globel.USER_G.getUserName());
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                Intent in = new Intent(getApplicationContext(), Login.class);
                startActivity(in);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
