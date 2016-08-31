/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ehb.xavier.prototype.Contact;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ehb.xavier.api.contactEndpoint.model.Contact;
import com.ehb.xavier.prototype.BuildConfig;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.R;

import java.io.IOException;

/**
 * FragmentActivity to hold the main {@link ContactsListFragment}. On larger screen devices which
 * can fit two panes also load {@link ContactDetailFragment}.
 */
public class ContactsListActivity extends FragmentActivity implements CompoundButton.OnCheckedChangeListener {

    // Defines a tag for identifying log entries
    private static final String TAG = "ContactsListActivity";

    Contact con;
    private ContactDetailFragment mContactDetailFragment;
    // If true, this is a larger screen device which fits two panes
    private boolean isTwoPaneLayout;
    // True if this activity instance is a search result view (used on pre-HC devices that load
    // search results in a separate instance of the activity rather than loading results in-line
    // as the query is typed.
    private boolean isSearchResultView = false;
    private ContactEditFragment mContactEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check if two pane bool is set based on resource directories
        isTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);
        // Log.e("twee", isTwoPaneLayout+"");


        if (isTwoPaneLayout) {
            // If two pane layout, locate the contact detail fragment
            mContactDetailFragment = (ContactDetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.contact_detail);
        }
        ((RadioButton) findViewById(R.id.radio_button0))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2))
                .setOnCheckedChangeListener(this);



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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        final Intent intent;
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.radio_button0:
                    new Thread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(getApplication(), ContactAdd.class);


                        startActivity(intent);
                    }
                        }).start();
                    break;
                case R.id.radio_button1:

//                    ContactEditFragment fragment = (ContactEditFragment) getFragmentManager().findFragmentById(R.id.contact_edit);
                    con = ContactDetailFragment.getContact();
                    if(con != null) {
                        Log.e("Con", con.toString());
                        intent = new Intent(getApplication(), ContactEditActivity.class);

                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Geen Persoon aangeklikt", Toast.LENGTH_LONG).show();
                    }
                    Log.e("testers", buttonView.isChecked()+"");
                    buttonView.setChecked(false);
                    break;
                case R.id.radio_button2:
                    if(con != null) {
                        AlertDialog a = new AlertDialog.Builder(this)
                                .setTitle("Contact")
                                .setMessage("Verwijderen ? ")

                                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        con = ContactDetailFragment.getContact();
                                        //Log.e("con", con.toString());
                                        if (con != null) {
                                            // cdb.deletebyid(con.getId());
                                            try {
                                                Endpoint.myApiService.removeQuote(con.getId()).execute();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            Log.e("contact", con.toString());
                                            Intent intent = new Intent(getApplication(), ContactsListActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Log.e("con", "test");
                                        }


                                    }
                                })
                                .setNegativeButton("Neen", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_info)

                                .create();

                        a.show();
                        Log.e("testers", buttonView.isChecked()+"");
                        buttonView.setChecked(false);
                    }else{
                        Toast.makeText(getApplicationContext(),"Geen Persoon aangeklikt", Toast.LENGTH_LONG).show();
                    }

                    break;


            }
        }

    }


    @Override
    public boolean onSearchRequested() {
        // Don't allow another search if this activity instance is already showing
        // search results. Only used pre-HC.
        return !isSearchResultView && super.onSearchRequested();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_list_menu, menu);
        Log.e("test1", "test");
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }*/
}
