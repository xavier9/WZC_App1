package com.ehb.xavier.prototype.User;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.BuildConfig;
import com.ehb.xavier.prototype.Contact.ContactDetailFragment;
import com.ehb.xavier.prototype.Contact.ContactEditFragment;
import com.ehb.xavier.prototype.Contact.Utils;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by xavier on 1/02/2015.
 */
public class UserlistActivity extends FragmentActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    // Defines a tag for identifying log entries
    private static final String TAG = "ContactsListActivity";
    User con;
    int position;
    ListView lists;
    private AsyncTask<User, Void, List<User>> EEATU;
    private UserArrayAdapter mAdapter;
    private ContactDetailFragment mContactDetailFragment;
    // If true, this is a larger screen device which fits two panes
    private boolean isTwoPaneLayout;
    // True if this activity instance is a search result view (used on pre-HC devices that load
    // search results in a separate instance of the activity rather than loading results in-line
    // as the query is typed.
    private boolean isSearchResultView = false;
    private ContactEditFragment mContactEditFragment;
    ArrayList<User> cons = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_fragment);
        // Check if two pane bool is set based on resource directories
        isTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);
        // Log.e("twee", isTwoPaneLayout+"");
// Set main content view. On smaller screen devices this is a single pane view with one
        // fragment. One larger screen devices this is a two pane view with two fragments.
        if (Utils.hasHoneycomb()) {
            // Enables action bar "up" navigation
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // Check if this activity instance has been triggered as a result of a search query. This
        // will only happen on pre-HC OS versions as from HC onward search is carried out using
        // an ActionBar SearchView which carries out the search in-line without loading a new
        // Activity.
       /* if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {

            // Fetch query from intent and notify the fragment that it should display search
            // results instead of all contacts.
            String searchQuery = getIntent().getStringExtra(SearchManager.QUERY);
            ContactsListFragment mContactsListFragment = (ContactsListFragment)
                    getSupportFragmentManager().findFragmentById(R.id.contact_list);

            // This flag notes that the Activity is doing a search, and so the result will be
            // search results rather than all contacts. This prevents the Activity and Fragment
            // from trying to a search on search results.
            isSearchResultView = true;


            // Set special title for search results
            String title = getString(R.string.contacts_list_search_results_title, searchQuery);
            setTitle(title);
        }*/

        if (isTwoPaneLayout) {
            // If two pane layout, locate the contact detail fragment
            mContactDetailFragment = (ContactDetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.contact_detail);
        }
        ((RadioButton) findViewById(R.id.radioButton))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radioButton2))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radioButton3))
                .setOnCheckedChangeListener(this);



        //mAdapter = new UserArrayAdapter(this, null);
        EEATU = new Endpoint.EndpointsAsyncTaskUser(this).execute();
        // cdb.addData(new Contact());
        // cdb.getSimpleDataDao().executeRaw("Alter table 'contact' ADD COLUMN email varchar2(20);");


        try {
            cons = (ArrayList<User>) EEATU.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        lists = (ListView) findViewById(R.id.listuser);
        Log.e("List", lists.toString());
        for (User c : cons) {
            Log.e("Contact", c.toString());
        }
        mAdapter = new UserArrayAdapter(this, cons);
        // Create the main contacts adapter
        // mAdapter = new InteractiveArrayAdapter(getActivity());
        Log.e("test", mAdapter.toString());
        if (mAdapter.list != null) {
            lists.setAdapter(mAdapter);
            lists.setOnItemClickListener(this);
        }
        lists.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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
        Intent intent;
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.radioButton:

                    intent = new Intent(getApplication(), Register.class);


                    startActivity(intent);

                    break;
                case R.id.radioButton2:

//                    ContactEditFragment fragment = (ContactEditFragment) getFragmentManager().findFragmentById(R.id.contact_edit);
                    con = getUser();//ContactDetailFragment.getContact();
                    if(con != null) {
                        Log.e("Con", con.toString());
                        Globel.customer = con;
                        intent = new Intent(getApplication(), EditUserActivity.class);

                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Geen Persoon aangeklikt", Toast.LENGTH_LONG).show();
                    }
                    Log.e("testers", buttonView.isChecked()+"");
                    buttonView.setChecked(false);
                    break;
                case R.id.radioButton3:


                    con = getUser();//ContactDetailFragment.getContact();
                    if(con != null) {
                        Log.e("con", con.toString());
                        if (con != null) {
                            // cdb.deletebyid(con.getId());
                            try {
                                Endpoint.myApiServiceU.removeUser(con.getId()).execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.e("contact", con.toString());
                            intent = new Intent(getApplication(), UserlistActivity.class);
                            startActivity(intent);
                        } else {
                            Log.e("con", "test");
                        }
                        Log.e("testers", buttonView.isChecked()+"");
                        buttonView.setChecked(false);
                    }else {
                        Toast.makeText(getApplicationContext(), "Geen Persoon aangeklikt", Toast.LENGTH_LONG).show();
                    }

                    break;


            }
        }

    }

    public User getUser() {
        return con;
    }

    public void setUser(User u) {
        con = u;
    }

    @Override
    public boolean onSearchRequested() {
        // Don't allow another search if this activity instance is already showing
        // search results. Only used pre-HC.
        return !isSearchResultView && super.onSearchRequested();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        User con = mAdapter.getItem(position);
        this.position = position;
        v.setSelected(true);
        lists.setSelection(position);
        lists.setItemChecked(position, true);
        Log.e("Con", con.toString());
        setUser(con);
    }

    /**
     * This is a subclass of CursorAdapter that supports binding Cursor columns to a view layout.
     * If those items are part of search results, the search string is marked by highlighting the
     * query text. An {@link android.widget.AlphabetIndexer} is used to allow quicker navigation up and down the
     * ListView.
     */
    public class UserArrayAdapter extends ArrayAdapter<User> {

        private final List<User> list;
        private final Activity context;

        public UserArrayAdapter(Activity context) throws ExecutionException, InterruptedException {
            super(context, R.layout.user_list_item, EEATU.get());
            this.context = context;

            list = EEATU.get();
        }

        public UserArrayAdapter(Activity context, List<User> list) {
            super(context, R.layout.user_list_item, list);
            this.context = context;
            this.list = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                LayoutInflater inflator = context.getLayoutInflater();
                view = inflator.inflate(R.layout.user_list_item, null);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.usernaam = (TextView) view.findViewById(R.id.Username);
                viewHolder.voornaam = (TextView) view.findViewById(R.id.Voor);
                view.setTag(viewHolder);

            } else {
                view = convertView;

            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.usernaam.setText(list.get(position).getUserName());
            holder.voornaam.setText(list.get(position).getFirstName() + " " + list.get(position).getLastName());
            return view;
        }

        private class ViewHolder {
            protected TextView usernaam;
            protected TextView voornaam;

        }
    }
}