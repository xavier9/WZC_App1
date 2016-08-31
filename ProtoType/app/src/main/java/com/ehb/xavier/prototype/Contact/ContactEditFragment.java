package com.ehb.xavier.prototype.Contact;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehb.xavier.api.contactEndpoint.model.Contact;
import com.ehb.xavier.prototype.Endpoint;
import com.ehb.xavier.prototype.Globel;
import com.ehb.xavier.prototype.R;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xavier on 30/12/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ContactEditFragment extends Fragment {
    private static Contact contact;

    FragmentActivity detailView;
    // Whether or not this fragment is showing in a two pane layout
    private boolean mIsTwoPaneLayout;
    // Used to store references to key views, layouts and menu items as these need to be updated
    // in multiple methods throughout this class.
    private ImageView mImageView;
    private TextView mEmptyView;
    private TextView mContactName;
    public ContactEditFragment() {
    }

    /**
     * Factory method to generate a new instance of the fragment given a contact Uri. A factory
     * method is preferable to simply using the constructor as it handles creating the bundle and
     * setting the bundle as an argument.
     *
     * @param contactUri The contact Uri to load
     * @return A new instance of {@link ContactDetailFragment}
     */
    public static ContactEditFragment newInstance(Contact contactUri) {
        // Create new instance of this fragment
        final ContactEditFragment fragment = new ContactEditFragment();

        // Create and populate the args bundle
        final Bundle args = new Bundle();

        Log.e("ContactEditFragment", contactUri.toString());

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

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflates the main layout to be used by this fragment
        detailView =
                inflater.inflate(R.layout.contactedit, container, false);

        // Gets handles to view objects in the layout
        mImageView = (ImageView) detailView.findViewById(R.id.contact_image);
        //mDetailsLayout = (LinearLayout) detailView.findViewById(R.id.contact_details_layout);
        mEmptyView = (TextView) detailView.findViewById(android.R.id.empty);

        if (mIsTwoPaneLayout) {
            // If this is a two pane view, the following code changes the visibility of the contact
            // name in details. For a one-pane view, the contact name is displayed as a title.
            mContactName = (TextView) detailView.findViewById(R.id.contact_name);
//            mContactName.setVisibility(View.VISIBLE);
        }


        return detailView;
    }*/

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        ContactEditFragment.contact = contact;

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

        detailView = getActivity();

        setText(contact);
            Log.e("Contact", contact.toString());

            // Set a placeholder loading image for the image loader
//        mImageLoader.setLoadingImage(R.drawable.ic_contact_picture_180_holo_light);

        // Tell the image loader to set the image directly when it's finished loading
        // rather than fading in
        //  mImageLoader.setImageFadeIn(false);




    }
    boolean t = true;
    @SuppressLint("WrongViewCast")
    public Contact getText() {
        t = true;
        ImageView i1 = (ImageView) detailView.findViewById(R.id.imageView8);
        ImageView i2 = (ImageView) detailView.findViewById(R.id.imageView9);
        ImageView i3 = (ImageView) detailView.findViewById(R.id.imageView10);
        i1.setVisibility(View.INVISIBLE);
        i2.setVisibility(View.INVISIBLE);
        i3.setVisibility(View.INVISIBLE);
        Contact c = new Contact();
        EditText view = (EditText) detailView.findViewById(R.id.contact_name1);
        String[] split = view.getText().toString().split(" ");

        String acht = split[1];
        if(split[1] != split[split.length-1]){
            acht +=  " " + split[split.length-1];
        }
        c.setFirstname(split[0]);
        c.setLastname(acht);
        EditText email = (EditText) detailView.findViewById(R.id.contract_email);

        EditText tel = (EditText) detailView.findViewById(R.id.contact_tel1);

        EditText gsm = (EditText) detailView.findViewById(R.id.contact_gsm1);

        if(!email.getText().toString().isEmpty()) {
            if (email.getText().toString() != null) {
                if (isEmailValid(email.getText().toString())) {
                    c.setEmail(email.getText().toString());
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
                    c.setGsm(gsm.getText().toString());
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
        Log.e("testers", tel.getText().toString());
        if(!tel.getText().toString().isEmpty()) {
            if (tel.getText().toString() != null) {
                char[] chars1 = tel.getText().toString().toCharArray();
                if (chars1.length == 9) {
                    c.setTelnr(tel.getText().toString());
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
        EditText skype = (EditText) detailView.findViewById(R.id.skypeID1);
        c.setSkypename(skype.getText().toString());
        c.setUserID(Globel.USER_G.getId());
        Log.e("contact", t+"");

            return c;



        //return c;
    }
    Contact contacts;

    EditText view;
    EditText email;
    EditText tel;
    EditText gsm;
    EditText skype;
    @SuppressLint("WrongViewCast")
    public void setText(final Contact item) {
        Log.e("Conitem", item.toString());
         view = (EditText) detailView.findViewById(R.id.contact_name1);
        view.setText(item.getFirstname() + " " + item.getLastname());
        email = (EditText) detailView.findViewById(R.id.contract_email);
        email.setText(item.getEmail());
        tel = (EditText) detailView.findViewById(R.id.contact_tel1);
        tel.setText(item.getTelnr());
        gsm = (EditText) detailView.findViewById(R.id.contact_gsm1);
        gsm.setText(item.getGsm());
        skype = (EditText) detailView.findViewById(R.id.skypeID1);

        if(item.getSkypename() != null) {
            skype.setText(item.getSkypename());
        }
        Button submit1 = (Button) detailView.findViewById(R.id.button2);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts = getText();
                //Log.e("contact", contact.isEmpty()+"");
                Log.e("contact", contact.toString()+"");
                if (t) {

                        contacts.setId(item.getId());
                        Log.e("ContactOnClick", contacts.getId() + " " + contacts.toString());
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {


                                try {
                                    Endpoint.myApiService.updateQuote(contacts).execute();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();

                        //int i = cda.updateData(contacts);
                        //Log.e("i", i + "");
                        Intent intents = new Intent(getActivity(), ContactsListActivity.class);
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

        ContactEditFragment fragment = (ContactEditFragment) getFragmentManager()
                .findFragmentById(R.id.contactedit);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(contact);
            Log.e("Contact", contact.toString());
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