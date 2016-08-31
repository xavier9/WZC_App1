package com.ehb.xavier.prototype.Contact;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehb.xavier.api.contactEndpoint.model.Contact;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xavier on 17/11/2014.
 */
public class ContactAdd extends Activity {
    TextView voornaam ;
    TextView achternaam ;
    TextView telnr;
    TextView gsm ;
    TextView email ;
    TextView skypeid ;
    ImageView imv;
    AsyncTask<Contact, Void, List<Contact>> EEATC;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactadd);

        voornaam = (TextView) findViewById(R.id.voornaam);
        achternaam = (TextView) findViewById(R.id.achtarnaam);
        telnr = (TextView) findViewById(R.id.telnr);
        gsm = (TextView) findViewById(R.id.gsm);
        email = (TextView) findViewById(R.id.email);
        skypeid = (TextView) findViewById(R.id.skypeID);


        imv = (ImageView) findViewById(R.id.userfoto);
        imv.setImageResource(R.drawable.ic_launcher);
        Button imagbutton = (Button) findViewById(R.id.imageButton);
        imagbutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


            }
        });


        EEATC = new Endpoint.EndpointsAsyncTaskContact(this).execute();
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView i1 = (ImageView) findViewById(R.id.imageView4);
                ImageView i2 = (ImageView) findViewById(R.id.imageView5);
                ImageView i3 = (ImageView) findViewById(R.id.imageView6);
                i1.setVisibility(View.INVISIBLE);
                i2.setVisibility(View.INVISIBLE);
                i3.setVisibility(View.INVISIBLE);
                voornaam = (TextView) findViewById(R.id.voornaam);
                achternaam = (TextView) findViewById(R.id.achtarnaam);
                telnr = (TextView) findViewById(R.id.telnr);
                gsm = (TextView) findViewById(R.id.gsm);
                email = (TextView) findViewById(R.id.email);
                skypeid = (TextView) findViewById(R.id.skypeID);
                boolean t = true;
                Contact contacts = new Contact();
                contacts.setFirstname(voornaam.getText().toString());
                contacts.setLastname(achternaam.getText().toString());
                Log.e("testers", email.getText().toString());
                if(!email.getText().toString().isEmpty()) {
                    if (email.getText().toString() != null) {
                        if (isEmailValid(email.getText().toString())) {
                            contacts.setEmail(email.getText().toString());
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
                Log.e("testers", gsm.getText().toString());
                if(!gsm.getText().toString().isEmpty()){
                if(gsm.getText().toString()!= null) {
                    char[] chars = gsm.getText().toString().toCharArray();
                    Log.e("lengte", chars.length+"");
                    if (chars.length == 10) {
                        contacts.setGsm(gsm.getText().toString());
                        //t= true;
                        i3.setVisibility(View.INVISIBLE);
                    }else{
                        t = false;

                        i3.setVisibility(View.VISIBLE);
                    }
                }else {
                    i3.setVisibility(View.INVISIBLE);
                }
                }else {
                    i3.setVisibility(View.INVISIBLE);
                }
                Log.e("testers", telnr.getText().toString());
                if(!telnr.getText().toString().isEmpty()) {
                    if (telnr.getText().toString() != null) {
                        char[] chars1 = telnr.getText().toString().toCharArray();
                        if (chars1.length == 9) {
                            contacts.setTelnr(telnr.getText().toString());
                           // t = true;
                            i2.setVisibility(View.INVISIBLE);
                        } else {
                            t = false;

                            i2.setVisibility(View.VISIBLE);
                        }
                    } else {
                        i2.setVisibility(View.INVISIBLE);
                    }
                }else{
                    i2.setVisibility(View.INVISIBLE);
                }
                contacts.setSkypename(skypeid.getText().toString());
                contacts.setUserID(Globel.USER_G.getId());
               // Toast.makeText(ContactAdd.this, contacts.toString() + "", Toast.LENGTH_LONG).show();

                Log.e("Contact", contacts.toString());
                BitmapDrawable bitmapDrawable = ((BitmapDrawable) imv.getDrawable());
                Bitmap bitmap = bitmapDrawable .getBitmap();
                Log.e("image", bitmap.toString());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageInByte = stream.toByteArray();

                Image im = new Image(imageInByte,  "Test image");
                Log.e("image", im.toString());
                //cda.addData(im);
                //boolean i =  EEATC.execute(contacts);

                Log.e("ContactonClick", contacts.toString());
                if(t ) {
                    try {

                        Contact result = Endpoint.myApiService.insertQuote(contacts).execute();
                        Log.e("test", result.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                // Log.e("i", i + "");

                /*Bitmap bmp = imv.getDrawingCache();
                Log.e("image", bmp.toString());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] data = bos.toByteArray();
                cda.addData(new Image(data,  "Test image"));
                int i = cda.addData(contacts);
                Log.e("i", i + "");*/

                    Intent intents = new Intent(ContactAdd.this, ContactsListActivity.class);
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
}