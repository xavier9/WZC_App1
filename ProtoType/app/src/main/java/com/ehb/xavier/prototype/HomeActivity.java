package com.ehb.xavier.prototype;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.User.Login;

import java.util.List;

public class HomeActivity extends Activity {

    private AsyncTask<User, Void, List<User>> EEATU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EEATU  = new Endpoint.EndpointsAsyncTaskUser(this).execute();

      Intent inent = new Intent(HomeActivity.this, Login.class);
        //Intent inent = new Intent(HomeActivity.this, ContactsListActivity.class);
                    // calling an activity using <intent-filter> action name
                    //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                    startActivity(inent);


       // dialog.show();




    }





}
