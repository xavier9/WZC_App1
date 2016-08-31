package com.ehb.xavier.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;

import java.io.IOException;
import java.util.Collections;

public class MyActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        /*try {
            setUp();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }*/
        new Thread(new Runnable() {
            public void run() {
       HttpTransport httpTransport = null;//newProxyTransport();

             httpTransport = new NetHttpTransport.Builder().build();
//            httpTransport = new NetHttpTransport.Builder().trustCertificates(GoogleUtils.getCertificateTrustStore())
                  //  .build();
            //httpTransport = newProxyTransport();
          // httpTransport = GoogleNetHttpTransport.newTrustedTransport();
               JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // The clientId and clientSecret can be found in Google Developers Console
        String clientId="36772493767-l0meid9shkjdtktjvecqiab3n1nfi26s.apps.googleusercontent.com";//"YOUR_CLIENT_ID";
        String clientSecret="wJfGjbCPvlH_a5qUbPIZvhHl";//"YOUR_CLIENT_SECRET";

        // Or your redirect URL for web based applications.
        String redirectUrl = "https://www.example.com/oauth2callback";
        String scope = "https://www.googleapis.com/auth/calendar";

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow(
                httpTransport, jsonFactory, clientId, clientSecret, Collections.singleton(scope));
        // Step 1: Authorize
        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUrl).build();

        // Point or redirect your user to the authorizationUrl.
        Log.e("Go to the following link in your browser:", "Go to the following link in your browser:");
        Log.e("Go to the following link in your browser:",authorizationUrl);

        // Read the authorization code from the standard input stream.
        // Read the authorization code from the standard input stream.

        // End of Step 1

        // Step 2: Exchange
        GoogleTokenResponse response = null;
        try {
            Log.e("Rederect url", redirectUrl.toString());

            Log.e("flow", flow.toString());

            Log.e(flow.getAccessType(), flow.getTokenServerEncodedUrl());
            response = flow.newTokenRequest("").setRedirectUri(redirectUrl).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

                // End of Step 2
        //Log.e("Response", response.toString());
        Credential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setClientSecrets(clientId, clientSecret)
               .build().setFromTokenResponse(response);
        Log.e("credencials", credential.toString());
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("prototype").build();
                Log.e("calendar", service.toString());

        //initiateSkypeUri(this, "0494739039");
       // String mySkypeUri = "xavier1732";
       // skype(mySkypeUri, Skype);
        //Intent i = new Intent(this, ContactsListActivity.class);
       // startActivity(i);

            }
        }).start();

    }

}
