package com.ehb.xavier.prototype.Contact;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ehb.xavier.prototype.R;

/**
 * Created by xavier on 15/11/2014.
 */
public class Skype extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skype);
        final EditText nubers = (EditText) findViewById(R.id.edt_skypeusername);

        // Open skype button click event code here
                ((Button) findViewById(R.id.openskype)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mySkypeUri = nubers.getText().toString()+"";
                skype(mySkypeUri, Skype.this);
            }
        });




    }

    public static void skype(String number, Context ctx) {
        try {
            //Intent sky = new Intent("android.intent.action.CALL_PRIVILEGED");
            //the above line tries to create an intent for which the skype app doesn't supply public api

            Intent sky = new Intent("android.intent.action.VIEW");
 
            sky.setData(Uri.parse("tel:" + number));
            Log.d("UTILS", "tel:" + number);
            ctx.startActivity(sky);
        } catch (ActivityNotFoundException e) {
            Log.e("SKYPE CALL", "Skype failed", e);
        }

    }

    public static void skypen(String number, Context ctx) {
        try {
            //Intent sky = new Intent("android.intent.action.CALL_PRIVILEGED");
            //the above line tries to create an intent for which the skype app doesn't supply public api

            Intent sky = new Intent("android.intent.action.VIEW");

            sky.setData(Uri.parse("skype:" + number+"?call"));
            Log.d("UTILS", "skype:" + number);
            ctx.startActivity(sky);
        } catch (ActivityNotFoundException e) {
            Log.e("SKYPE CALL", "Skype failed", e);
        }

    }

}

