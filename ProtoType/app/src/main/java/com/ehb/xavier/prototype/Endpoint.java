package com.ehb.xavier.prototype;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.ehb.xavier.api.eventEndpoint.EventEndpoint;
import com.ehb.xavier.api.contactEndpoint.ContactEndpoint;
import com.ehb.xavier.api.contactEndpoint.model.Contact;
import com.ehb.xavier.api.eventEndpoint.model.Event;
import com.ehb.xavier.api.menuinsertEndpoint.MenuinsertEndpoint;
import com.ehb.xavier.api.menuinsertEndpoint.model.MenuInsert;
import com.ehb.xavier.api.menukeuzeEndpoint.MenukeuzeEndpoint;
import com.ehb.xavier.api.menukeuzeEndpoint.model.MenuKeuze;
import com.ehb.xavier.api.rolEndpoint.RolEndpoint;
import com.ehb.xavier.api.rolEndpoint.model.Rol;
import com.ehb.xavier.api.userEndpoint.UserEndpoint;
import com.ehb.xavier.api.userEndpoint.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by xavier on 24/01/2015.
 */

public class Endpoint {
    public static ContactEndpoint myApiService = null;


    public static class EndpointsAsyncTaskContact extends AsyncTask<Contact, Void, List<Contact>> {

        private Context context;

        public EndpointsAsyncTaskContact(Context context) {
            this.context = context;
        }
        public int setContact(Contact c){
            int i = 0;
            Contact note = c;
            Log.e("test", note.toString());
            try {

                Contact result = myApiService.insertQuote(note).execute();
                Log.e("test", result.toString());
               i = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected List<Contact> doInBackground(Contact... params) {
            if (myApiService == null) { // Only do this once
                ContactEndpoint.Builder builder = new ContactEndpoint.Builder/*(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                        .setRootUrl(Globel.URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });*/
                        (AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(Globel.URL);
// end options for devappserver
                myApiService = builder.build();

            }
            try {
               // Log.e("test1", myApiService.list().execute().getItems().toString());
                return myApiService.list().execute().getItems();
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<Contact> result) {
            if (result != null) {
                for (Contact q : result) {
                    //Toast.makeText(context, q.getFirstname() + " : " + q.getLastname(), Toast.LENGTH_LONG).show();
                    Log.e("quote", q.toString());
                }
            }
        }

     }
    public static UserEndpoint myApiServiceU = null;
    public static class EndpointsAsyncTaskUser extends AsyncTask<User, Void, List<User>> {

        private Context context;

        public EndpointsAsyncTaskUser(Context context) {
            this.context = context;
        }
        public int setUser(User c){
            int i = 0;
            User note = c;
            Log.e("test", note.toString());
            try {

                User result = myApiServiceU.insertUser(note).execute();
                Log.e("test", result.toString());
                i = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected List<User> doInBackground(User... params) {
            if (myApiServiceU == null) { // Only do this once
                UserEndpoint.Builder builder = new UserEndpoint.Builder/*(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                        .setRootUrl(Globel.URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });*/
                        (AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(Globel.URL);
// end options for devappserver
                myApiServiceU = builder.build();

            }
            try {
//                 Log.e("test1", myApiService.list().execute().getItems().toString());
                return myApiServiceU.list().execute().getItems();
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<User> result) {
            if (result != null) {
                for (User q : result) {
                    //Toast.makeText(context, q.getFirstName() + " : " + q.getLastName(), Toast.LENGTH_LONG).show();
                    Log.e("quote", q.toString());
                }
            }
        }

    }

    public static RolEndpoint myApiServiceR = null;
    public static class EndpointsAsyncTaskRol extends AsyncTask<Rol, Void, List<Rol>> {

        private Context context;

        public EndpointsAsyncTaskRol(Context context) {
            this.context = context;
        }
        public int setRol(Rol c){
            int i = 0;
            Rol note = c;
            Log.e("test", note.toString());
            try {

                Rol result = myApiServiceR.insertRol(note).execute();
                Log.e("test", result.toString());
                i = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected List<Rol> doInBackground(Rol... params) {
            if (myApiServiceR == null) { // Only do this once
                RolEndpoint.Builder builder = new RolEndpoint.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                        .setRootUrl(Globel.URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
// end options for devappserver
                myApiServiceR = builder.build();

            }
            try {
                // Log.e("test1", myApiService.list().execute().getItems().toString());
                return myApiServiceR.list().execute().getItems();
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<Rol> result) {
            if (result != null) {
                for (Rol q : result) {
                   // Toast.makeText(context, q.getNaam(), Toast.LENGTH_LONG).show();
                    Log.e("quote", q.toString());
                }
            }
        }

    }

    public static MenukeuzeEndpoint myApiServiceMK = null;
    public static class EndpointsAsyncTaskMenuKeuze extends AsyncTask<MenuKeuze, Void, List<MenuKeuze>> {

        private Context context;

        public EndpointsAsyncTaskMenuKeuze(Context context) {
            this.context = context;
        }
        public int setMenuKeuze(MenuKeuze c){
            int i = 0;
            MenuKeuze note = c;
            Log.e("test", note.toString());
            try {

                MenuKeuze result = myApiServiceMK.insertMenuKeuze(note).execute();
                Log.e("test", result.toString());
                i = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected List<MenuKeuze> doInBackground(MenuKeuze... params) {
            if (myApiServiceMK == null) { // Only do this once
                MenukeuzeEndpoint.Builder builder = new MenukeuzeEndpoint.Builder/*(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                        .setRootUrl(Globel.URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });*/
                        (AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(Globel.URL);
// end options for devappserver
                myApiServiceMK = builder.build();

            }
            try {
                // Log.e("test1", myApiService.list().execute().getItems().toString());
                return myApiServiceMK.list().execute().getItems();
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<MenuKeuze> result) {
            if (result != null) {
                for (MenuKeuze q : result) {
                    // Toast.makeText(context, q.getNaam(), Toast.LENGTH_LONG).show();
                    Log.e("quote", q.toString());
                }
            }
        }

    }

    public static MenuinsertEndpoint myApiServiceMI = null;
    public static class EndpointsAsyncTaskMenuInsert extends AsyncTask<MenuInsert, Void, List<MenuInsert>> {

        private Context context;

        public EndpointsAsyncTaskMenuInsert(Context context) {
            this.context = context;
        }
        public int setMenuKeuze(MenuInsert c){
            int i = 0;
            MenuInsert note = c;
            Log.e("test", note.toString());
            try {

                MenuInsert result = myApiServiceMI.insertMenuKeuze(note).execute();
                Log.e("test", result.toString());
                i = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected List<MenuInsert> doInBackground(MenuInsert... params) {
            if (myApiServiceMI == null) { // Only do this once
                MenuinsertEndpoint.Builder builder = new MenuinsertEndpoint.Builder/*(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                        .setRootUrl(Globel.URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });*/
                        (AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(Globel.URL);
// end options for devappserver
                myApiServiceMI = builder.build();

            }
            try {
                // Log.e("test1", myApiService.list().execute().getItems().toString());
                return myApiServiceMI.list().execute().getItems();
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<MenuInsert> result) {
            if (result != null) {
                for (MenuInsert q : result) {
                    // Toast.makeText(context, q.getNaam(), Toast.LENGTH_LONG).show();
                    Log.e("quote", q.toString());
                }
            }
        }

    }

    public static EventEndpoint myApiServiceEE = null;
    public static class EndpointsAsyncTaskEvent extends AsyncTask<Event, Void, List<Event>> {

        private Context context;

        public EndpointsAsyncTaskEvent(Context context) {
            this.context = context;
        }
        public int setMenuKeuze(Event c){
            int i = 0;
            Event note = c;
            Log.e("test", note.toString());
            try {

                Event result = myApiServiceEE.insertEvent(note).execute();
                Log.e("test", result.toString());
                i = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected List<Event> doInBackground(Event... params) {
            if (myApiServiceEE == null) { // Only do this once
                EventEndpoint.Builder builder = new EventEndpoint.Builder/*(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                        .setRootUrl(Globel.URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });*/
                        (AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(Globel.URL);
// end options for devappserver
                myApiServiceEE = builder.build();

            }
            try {
                // Log.e("test1", myApiService.list().execute().getItems().toString());
                return myApiServiceEE.list().execute().getItems();
            } catch (IOException e) {
                return Collections.EMPTY_LIST;
            }
        }

        @Override
        protected void onPostExecute(List<Event> result) {
            if (result != null) {
                for (Event q : result) {
                    // Toast.makeText(context, q.getNaam(), Toast.LENGTH_LONG).show();
                    Log.e("quote", q.toString());
                }
            }
        }

    }
}