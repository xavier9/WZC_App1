package com.ehb.xavier.prototype.Agenda;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ehb.xavier.api.userEndpoint.model.User;
import com.ehb.xavier.prototype.BuildConfig;
import com.ehb.xavier.prototype.Contact.Utils;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by xavier on 20/02/2015.
 * een lijst van persoonen die naar het event gaan
 */
public class EvendUserGoList extends Activity implements AdapterView.OnItemClickListener{
    private static final String TAG = "ContactsListActivity";
    User con;
    int position;
    ListView lists;
    private AsyncTask<User, Void, List<User>> EEATU;
    private UserArrayAdapter mAdapter;

    ArrayList<User> cons = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_user_list);






        //mAdapter = new UserArrayAdapter(this, null);
        EEATU = new Endpoint.EndpointsAsyncTaskUser(this).execute();
        // cdb.addData(new Contact());
        // cdb.getSimpleDataDao().executeRaw("Alter table 'contact' ADD COLUMN email varchar2(20);");


        cons = Globel.USER_GO;


        lists = (ListView) findViewById(R.id.Usergo);
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
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        User con = mAdapter.getItem(position);
        this.position = position;
        v.setSelected(true);
        lists.setSelection(position);
        lists.setItemChecked(position, true);
        Log.e("Con", con.toString());
        //setUser(con);
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
