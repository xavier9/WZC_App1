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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehb.xavier.api.contactEndpoint.model.Contact;
import com.ehb.xavier.prototype.R;

/**
 * This fragment displays details of a specific contact from the contacts provider. It shows the
 * contact's display photo, name and all its mailing addresses. You can also modify this fragment
 * to show other information, such as phone numbers, email addresses and so forth.
 * <p/>
 * This fragment appears full-screen in an activity on devices with small screen sizes, and as
 * part of a two-pane layout on devices with larger screens, alongside the
 * {@link ContactsListFragment}.
 */
public class ContactDetailFragment extends Fragment {


    private static Contact contact;
    View detailView;
    // Whether or not this fragment is showing in a two pane layout
    private boolean mIsTwoPaneLayout;
    // Used to store references to key views, layouts and menu items as these need to be updated
    // in multiple methods throughout this class.
    private ImageView mImageView;
    private TextView mEmptyView;
    private TextView mContactName;

    /**
     * Fragments require an empty constructor.
     */
    public ContactDetailFragment() {
    }

    /**
     * Factory method to generate a new instance of the fragment given a contact Uri. A factory
     * method is preferable to simply using the constructor as it handles creating the bundle and
     * setting the bundle as an argument.
     *
     * @param contactUri The contact Uri to load
     * @return A new instance of {}
     */
    public static ContactDetailFragment newInstance(Contact contactUri) {
        // Create new instance of this fragment
        final ContactDetailFragment fragment = new ContactDetailFragment();

        // Create and populate the args bundle
        final Bundle args = new Bundle();

        Log.e("test", contactUri.toString());

        contact = contactUri;

        // Assign the args bundle to the new fragment
        fragment.setArguments(args);
        //Contact c = this.setContac(contact);
        // Inflates the main layout to be used by this fragment

        //TextView name = (TextView) detailView.findViewById(R.id.contact_name);
        //name.setText(c.getFirstname()+ " " + c.getLastname());
        // Return fragment
        return fragment;
    }

    public static Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        ContactDetailFragment.contact = contact;

    }

    /**
     * When the Fragment is first created, this callback is invoked. It initializes some key
     * class fields.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if this fragment is part of a two pane set up or a single pane
        mIsTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);

        // Let this fragment contribute menu items
        setHasOptionsMenu(true);


        // Set a placeholder loading image for the image loader
//        mImageLoader.setLoadingImage(R.drawable.ic_contact_picture_180_holo_light);

        // Tell the image loader to set the image directly when it's finished loading
        // rather than fading in
        //  mImageLoader.setImageFadeIn(false);7

    }

    public void setText(Contact item) {
        Log.e("Contact1", item.toString());
        TextView view = (TextView) getView().findViewById(R.id.contact_name);
        view.setText(item.getFirstname() + " " + item.getLastname());
        TextView email = (TextView) getView().findViewById(R.id.contract_email);
        if(item.getEmail() != null) {
            email.setText(item.getEmail());
        }else{
            email.setText("");
        }
        TextView tel = (TextView) this.getView().findViewById(R.id.contact_tel);
        if(item.getTelnr() != null) {
            Log.e("test1", item.getTelnr());
            tel.setText(item.getTelnr());
        }else {
            tel.setText("");
        }
        TextView gsm = (TextView) getView().findViewById(R.id.contact_gsm);
        if(item.getGsm() != null) {
            gsm.setText(item.getGsm());
        }else {
            gsm.setText("");
        }
        TextView skype = (TextView) getView().findViewById(R.id.skype);
        if(item.getSkypename() != null){
            skype.setText(item.getSkypename());
        }else {
            skype.setText("");
        }
        contact = item;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflates the main layout to be used by this fragment
        detailView =
                inflater.inflate(R.layout.contact_detail_fragment, container, false);

        // Gets handles to view objects in the layout
        mImageView = (ImageView) detailView.findViewById(R.id.contact_image);
        //mDetailsLayout = (LinearLayout) detailView.findViewById(R.id.contact_details_layout);
        mEmptyView = (TextView) detailView.findViewById(android.R.id.empty);

        if (mIsTwoPaneLayout) {
            // If this is a two pane view, the following code changes the visibility of the contact
            // name in details. For a one-pane view, the contact name is displayed as a title.
            mContactName = (TextView) detailView.findViewById(R.id.contact_name);
            mContactName.setVisibility(View.VISIBLE);
        }
        ImageButton telb = (ImageButton) detailView.findViewById(R.id.telnrButton);
        final TextView tel = (TextView) detailView.findViewById(R.id.contact_tel);
        telb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Skype.skype(tel.getText().toString(), getActivity());
            }
        });

        ImageButton gsmb = (ImageButton) detailView.findViewById(R.id.gsmnrButton);
        final TextView gsm = (TextView) detailView.findViewById(R.id.contact_gsm);
        gsmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Skype.skype(gsm.getText().toString(), getActivity());
            }
        });
        ImageButton skypeb = (ImageButton) detailView.findViewById(R.id.skypebutton);
        final TextView skypenaam = (TextView) detailView.findViewById(R.id.skype);
        skypeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Skype.skypen(skypenaam.getText().toString(), getActivity());
            }
        });
        return detailView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // If not being created from a previous state
        if (savedInstanceState == null) {
            // Sets the argument extra as the currently displayed contact
            setContact(getContact() != null ?
                    getContact() : null);
        } else {
            // If being recreated from a saved state, sets the contact from the incoming
            // savedInstanceState Bundle
            setContact(getContact());
        }
    }

    /**
     * When the Fragment is being saved in order to change activity state, save the
     * currently-selected contact.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Saves the contact Uri

    }


}
