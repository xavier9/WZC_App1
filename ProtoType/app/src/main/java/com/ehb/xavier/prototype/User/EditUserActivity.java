package com.ehb.xavier.prototype.User;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ehb.xavier.api.rolEndpoint.model.Rol;
import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.Contact.Utils;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;
import com.google.api.client.util.DateTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xavier on 2/02/2015.
 */
public class EditUserActivity extends Activity {
    private static User contact;
    List<Rol> rollen = null;
    FragmentActivity detailView;
    private AsyncTask<Rol, Void, List<Rol>> EEATR;
    // Progress Dialog
    private AsyncTask<User, Void, List<User>> EEATU;
    // Whether or not this fragment is showing in a two pane layout
    private boolean mIsTwoPaneLayout;
    // Used to store references to key views, layouts and menu items as these need to be updated
    // in multiple methods throughout this class.
    private ImageView mImageView;
    private TextView mEmptyView;
    private TextView mContactName;
    private EditText gbdag, gbmaand, gbjaar;
    User contacts;

    public EditUserActivity() {
    }
    ;

    public User getUser() {
        return contact;
    }

    public void setContact(User contact) {
        EditUserActivity.contact = contact;

    }

    /**
     * When the Fragment is first created, this callback is invoked. It initializes some key
     * class fields.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_fragment);

        // Check if this fragment is part of a two pane set up or a single pane

        setText(Globel.customer);
        Log.e("Contact", Globel.customer.toString());

        // Set a placeholder loading image for the image loader
//        mImageLoader.setLoadingImage(R.drawable.ic_contact_picture_180_holo_light);
        if (Utils.hasHoneycomb()) {
            // Enables action bar "up" navigation
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // Tell the image loader to set the image directly when it's finished loading
        // rather than fading in
        //  mImageLoader.setImageFadeIn(false);


    }
    boolean t = true;
    @SuppressLint("WrongViewCast")
    public User getText() {
        t = true;
        ImageView i1 = (ImageView) findViewById(R.id.imageView11);
        i1.setVisibility(View.INVISIBLE);
        User c = new User();
        EditText gnaam = (EditText) findViewById(R.id.gebruikersnaam1);
        c.setUserName(gnaam.getText().toString());
        EditText wwoord = (EditText) findViewById(R.id.wachtwoord1);
        c.setPassWord(wwoord.getText().toString());
        EditText lnaam = (EditText) findViewById(R.id.last1);
        c.setLastName(lnaam.getText().toString());
        EditText fname = (EditText) findViewById(R.id.first1);
        c.setFirstName(fname.getText().toString());
        //EditText gdate = (EditText) findViewById(R.id.gbdate1);
        if (!gbdag.getText().toString().isEmpty() && !gbmaand.getText().toString().isEmpty() && !gbjaar.getText().toString().isEmpty()) {
            c.setGeboortedatum(new DateTime(new Date(Integer.valueOf(gbjaar.getText().toString()), Integer.valueOf(gbmaand.getText().toString())
                    , Integer.valueOf(gbdag.getText().toString()))));
        }
        // c.setGeboortedatum(DateTime.parseRfc3339(gdate.getText().toString()));
        EditText email = (EditText) findViewById(R.id.email1);
        if(!email.getText().toString().isEmpty()) {
            if (email.getText().toString() != null) {
                if (isEmailValid(email.getText().toString())) {
                    c.setEmail(email.getText().toString());
                    // t = true;
                    i1.setVisibility(View.INVISIBLE);
                } else {
                    t = false;

                    i1.setVisibility(View.VISIBLE);
                }
            } else {
                i1.setVisibility(View.INVISIBLE);
            }
        }else{
            i1.setVisibility(View.INVISIBLE);
        }
        //c.setEmail(email.getText().toString());
        EditText knr = (EditText) findViewById(R.id.kamernr1);
        c.setKamernr(Integer.valueOf(knr.getText().toString()));
        Spinner rol = (Spinner) findViewById(R.id.rolkeuze1);
        Log.e("RolID", rol.getSelectedItem() + "");
        Rol r = (Rol) rol.getSelectedItem();
        c.setRol(r.getId());
        c.setKamernr(Integer.valueOf(knr.getText().toString()));
        return c;
    }

    @SuppressLint("WrongViewCast")
    public void setText(final User item) {
        Log.e("Conitem", item.toString());
        EEATR = new Endpoint.EndpointsAsyncTaskRol(getApplicationContext()).execute();

        try {
            rollen = EEATR.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Spinner rol = (Spinner) findViewById(R.id.rolkeuze1);
        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getApplicationContext(), R.layout.single_rol, (ArrayList) rollen);

        Log.e("adaptor", adapter.getItem(0).toString());
        rol.setAdapter(new MySimpleArrayAdapter(getApplicationContext(), R.layout.single_rol, (ArrayList) rollen));
        Rol ro = null;
        Log.e("RolID", item.getRol() + "");
        for(Rol r : Globel.ROL_LIST) {
            if(r.getId().equals(item.getRol())) {
                ro = r;
                Log.e("Rol", ro.toString());
            }
        }

        EditText view = (EditText) findViewById(R.id.gebruikersnaam1);
        view.setText(item.getUserName());
        EditText email = (EditText) findViewById(R.id.email1);
        email.setText(item.getEmail());
        EditText tel = (EditText) findViewById(R.id.wachtwoord1);
        tel.setText(item.getPassWord());
        EditText gsm = (EditText) findViewById(R.id.last1);
        gsm.setText(item.getLastName());
        EditText n = (EditText) findViewById(R.id.first1);
        n.setText(item.getFirstName());
        gbdag = (EditText) findViewById(R.id.gbdateDag);
        gbmaand = (EditText) findViewById(R.id.gbdateMaand);
        gbjaar = (EditText) findViewById(R.id.gbdateJaar);

        DateTime dt = item.getGeboortedatum();
        if (dt != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Log.e("date", dt.toString());
                Date date1 = simpleDateFormat.parse(dt.toString());

                gbdag.setText(date1.getDate() + "");
                gbmaand.setText(date1.getMonth() + "");
                gbjaar.setText(date1.getYear() + "");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        EditText k = (EditText) findViewById(R.id.kamernr1);
        k.setText(item.getKamernr() + "");

        Log.e("Rol", ro.toString());
        Log.e("Rol", rollen.indexOf(ro) + "");
        rol.setSelection(rollen.indexOf(ro));
        Button submit1 = (Button) findViewById(R.id.usertoevoegen1);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts = getText();
                contacts.setId(item.getId());
                if(t) {
                    Log.e("ContactOnClick", contacts.getId() + " " + contacts.toString());
                    new Thread( new Runnable() {
                        @Override
                        public void run() {


                    try {
                        Endpoint.myApiServiceU.updateUser(contacts).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        }
                    }).run();
                    //int i = cda.updateData(contacts);
                    //Log.e("i", i + "");
                    Intent intents = new Intent(getApplicationContext(), UserlistActivity.class);
                    startActivity(intents);
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
}

