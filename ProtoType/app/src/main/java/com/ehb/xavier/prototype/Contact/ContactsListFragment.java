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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ehb.xavier.api.contactEndpoint.model.Contact;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * This fragment displays a list of contacts stored in the Contacts Provider. Each item in the list
 * shows the contact's thumbnail photo and display name. On devices with large screens, this
 * fragment's UI appears as part of a two-pane layout, along with the UI of
 * {@link ContactDetailFragment}. On smaller screens, this fragment's UI appears as a single pane.
 * <p/>
 * This Fragment retrieves contacts based on a search string. If the user doesn't enter a search
 * string, then the list contains all the contacts in the Contacts Provider. If the user enters a
 * search string, then the list contains only those contacts whose data matches the string. The
 * Contacts Provider itself controls the matching algorithm, which is a "substring" search: if the
 * search string is a substring of any of the contacts data, then there is a match.
 * <p/>
 * On newer API platforms, the search is implemented in a SearchView in the ActionBar; as the user
 * types the search string, the list automatically refreshes to display results ("type to filter").
 * On older platforms, the user must enter the full string and trigger the search. In response, the
 * trigger starts a new Activity which loads a fresh instance of this fragment. The resulting UI
 * displays the filtered list and disables the search feature to prevent furthering searching.
 */
public class ContactsListFragment extends ListFragment implements
        AdapterView.OnItemClickListener {

    ArrayList<Contact> contacts = null;
    private InteractiveArrayAdapter mAdapter; // The main query adapter

    private String mSearchTerm; // Stores the current search query term
    private ArrayList<Contact> filteredList;
    // Whether or not this fragment is showing in a two-pane layout
    private boolean mIsTwoPaneLayout;
    // Whether or not this is a search result view of this fragment, only used on pre-honeycomb
    // OS versions as search results are shown in-line via Action Bar search from honeycomb onward
    private boolean mIsSearchResultView = false;
    private AsyncTask<Contact, Void, List<Contact>> EEATC;
    private Intent intent;
    private InteractiveArrayAdapter adapterTaak;
    private ContactsListFragment activity;

    private Typeface typeface;
    private ArrayList<Contact> arraylist;
    String charText = null;
    /**
     * Fragments require an empty constructor.
     */
    public ContactsListFragment() {
    }
    List<Contact> cons = new ArrayList<Contact>();
    List<Contact> cons1 = new ArrayList<Contact>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contacts = new ArrayList<Contact>();
        // Check if this fragment is part of a two-pane set up or a single pane by reading a
        // boolean from the application resource directories. This lets allows us to easily specify
        // which screen sizes should use a two-pane layout by setting this boolean in the
        // corresponding resource size-qualified directory.
        mIsTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);

        // Let this fragment contribute menu items
        setHasOptionsMenu(true);


        mAdapter = new InteractiveArrayAdapter(getActivity(), null);
        EEATC = new Endpoint.EndpointsAsyncTaskContact(getActivity()).execute();
        // cdb.addData(new Contact());
        // cdb.getSimpleDataDao().executeRaw("Alter table 'contact' ADD COLUMN email varchar2(20);");
        //cons.setTextFilterEnabled(true);

        Log.e("userid", Globel.USER_G.getId()+"");
        try {
            cons = EEATC.get();
            if(cons != null) {
                for (Contact c : cons) {
                    if(Globel.USER_G.getId().equals(c.getUserID())){
                        cons1.add(c);
                    }
                    Log.e("Contact", c.toString());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        final InteractiveArrayAdapter iaa = new InteractiveArrayAdapter(getActivity(), cons1);

        mAdapter = iaa;
                // Create the main contacts adapter
        // mAdapter = new InteractiveArrayAdapter(getActivity());

        // If there's a previously selected search item from a saved state then don't bother
        // initializing the loader as it will be restarted later when the query is populated into
        // the action bar search view (see onQueryTextChange() in onCreateOptionsMenu()).

    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up ListView, assign adapter and set some listeners. The adapter was previously
        // created in onCreate().
        Log.e("test", mAdapter.toString());


        if(mAdapter.list != null) {
            setListAdapter(mAdapter);
            getListView().setOnItemClickListener(this);
        }


        if (mIsTwoPaneLayout) {
            // In a two-pane layout, set choice mode to single as there will be two panes
            // when an item in the ListView is selected it should remain highlighted while
            // the content shows in the second pane.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }


    }
    

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // Gets the Cursor object currently bound to the ListView
        //final Cursor cursor = mAdapter.ge);

        // Moves to the Cursor row corresponding to the ListView item that was clicked
        //cursor.moveToPosition(position);
        Contact con = mAdapter.getItem(position);
        Log.e("Con", con.toString());
        // Creates a contact lookup Uri from contact ID and lookup_key
        //final Contact uri = con;

        //);

        // Notifies the parent activity that the user selected a contact. In a two-pane layout, the
        // parent activity loads a ContactDetailFragment that displays the details for the selected
        // contact. In a single-pane layout, the parent activity starts a new activity that
        // displays contact details in its own Fragment.
        //

        ContactDetailFragment fragment = (ContactDetailFragment) getFragmentManager()
                .findFragmentById(R.id.contact_detail);

        if (fragment != null && fragment.isInLayout()) {
            Log.e("Contact", con.toString());
            fragment.setText(con);
        }
        //Log.e("Test", position +" "+ id+ "");

        // If two-pane layout sets the selected item to checked so it remains highlighted. In a
        // single-pane layout a new activity is started so this is not needed.
        if (mIsTwoPaneLayout) {
            getListView().setItemChecked(position, true);
        }

        //ContactDetailFragment.newInstance(con);

    }

    // This method uses APIs from newer OS versions than the minimum that this app supports. This
    // annotation tells Android lint that they are properly guarded so they won't run on older OS
    // versions and can be ignored by lint.
    ArrayList<Contact> taken = new ArrayList<Contact>();
    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_list_menu, menu);

        // get the searview
        MenuItem zoekveld = menu.findItem(R.id.menu_search);
        SearchView zoekview = (SearchView) zoekveld.getActionView();

        // Execute this when searching
        zoekview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public InteractiveArrayAdapter adapterTaak;

            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            public boolean onQueryTextChange(String zoekterm) {

                Activity activity = getActivity();
                Context context = activity.getApplicationContext();
                Log.e("testing", zoekterm);
                taken = (ArrayList<Contact>) cons;
                //taken.clear();
                ArrayList<Contact> conste = new ArrayList<Contact>();
                conste.addAll(cons);
                // Search the task by the inputed value
                if(!zoekterm.isEmpty()) {
                    Log.e("testing", zoekterm);
                    //ContactsListFragment modelTaken = ContactsListFragment.instantiate(context);
                   // taken.clear();
                    /*for(Iterator<Contact> it = conste.iterator(); it.hasNext();) {
                        Contact s = it.next();
                        if(s.getFirstname().contains(zoekterm)) {
                            taken.add(s);
                            Log.e("Contact", s.toString());
                        }
                    }*/
                    //Contact v = null;
                    try {
                        taken = (ArrayList<Contact>) EEATC.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    for (Contact c : conste) {
                        //Log.e("Contact", c.toString());
                        if (c.getFirstname().contains(zoekterm)) {
                           // v = c;
                            //taken.add(c);
                            Log.e("Contact", c.toString());
                        } else {
                            //taken.clear();
                            taken.remove(c);
                            Log.e("Contact", "test");
                        }
                    }

                    /*if(v == null){
                        taken.clear();
                        taken =  conste;
                    }*/
                }



                    /*for(Contact c : taken){
                        Log.e("Contact", c.toString());
                    }*/
                    // Return the found items to the list

                    this.adapterTaak = new InteractiveArrayAdapter(getActivity(), taken);
                    //taken.clear();
                    setListAdapter(adapterTaak);

                //taken.clear();
                return true;

            }


        });
        zoekview.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.e("Close", "close");
                return true;
            }
        });

        /*super.onCreateOptionsMenu(menu, inflater);
        Log.e("test", "test1");
        getActivity().getMenuInflater().inflate(R.menu.contact_list_menu, menu);
        // Inflate the menu items
        //inflater.inflate(R.menu.contact_list_menu, menu);
        // Locate the search item
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        Log.e("test", searchItem.toString());
        // In versions prior to Android 3.0, hides the search item to prevent additional
        // searches. In Android 3.0 and later, searching is done via a SearchView in the ActionBar.
        // Since the search doesn't create a new Activity to do the searching, the menu item
        // doesn't need to be turned off.
        if (mIsSearchResultView) {
            searchItem.setVisible(false);

        }*/

        // In version 3.0 and later, sets up and configures the ActionBar SearchView


    }

    private MenuItem mSearchAction;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // For platforms earlier than Android 3.0, triggers the search activity
            case R.id.menu_search:
                Log.e("test", "test2");
                Log.e("test", item.toString());

                if (!Utils.hasHoneycomb()) {
                    getActivity().onSearchRequested();

                }
                //mIsSearchResultView = true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }



   



    /**
     * This is a subclass of CursorAdapter that supports binding Cursor columns to a view layout.
     * If those items are part of search results, the search string is marked by highlighting the
     * query text. An {@link android.widget.AlphabetIndexer} is used to allow quicker navigation up and down the
     * ListView.
     */
    public class InteractiveArrayAdapter extends ArrayAdapter<Contact> implements Filterable {

        private final List<Contact> list;
        private final Activity context;

        public InteractiveArrayAdapter(Activity context) throws ExecutionException, InterruptedException {
            super(context, R.layout.contact_list_item, EEATC.get());
            this.context = context;

            list = EEATC.get();
        }

        public InteractiveArrayAdapter(Activity context, List<Contact> list) {
            super(context, R.layout.contact_list_item, list);
            this.context = context;
            this.list = list;
            getFilter();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                LayoutInflater inflator = context.getLayoutInflater();
                view = inflator.inflate(R.layout.contact_list_item, null);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.text = (TextView) view.findViewById(R.id.Username);
                //viewHolder.gsm = (TextView) view.findViewById(R.id.gsm);
                view.setTag(viewHolder);

            } else {
                view = convertView;

            }
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.text.setText(list.get(position).getFirstname() + " " + list.get(position).getLastname());
            //holder.gsm.setText(list.get(position).getGsm());
            return view;
        }

        public void filter(String searchString) {
            charText = charText.toLowerCase(Locale.getDefault());
            arraylist.clear();
            if (charText.length() == 0) {
                arraylist.addAll(cons);
            } else {
                for (Contact cs : arraylist) {
                    if (cs.getFirstname().contains(charText)) {
                        cons.add(cs);
                    }
                }
            }
            notifyDataSetChanged();
        }

        private class ViewHolder {
            protected TextView text;
            //protected TextView gsm;

        }

    }

}
