package com.ehb.xavier.prototype.User;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ehb.xavier.api.rolEndpoint.model.Rol;
import com.ehb.xavier.prototype.R;

import java.util.ArrayList;

/**
 * Created by xavier on 16/12/2014.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<Rol> {
    private final Context context;
    private final ArrayList<Rol> values;
    private LayoutInflater inflater;

    public MySimpleArrayAdapter(Context context, int textViewResourceid, ArrayList<Rol> values) {
        super(context, textViewResourceid, values);
        this.context = context;
        this.values = values;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getDropDownView(int pos, View convertView, ViewGroup parent) {
        return getView(pos, convertView, parent);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = inflater.inflate(R.layout.single_rol, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.Username);
        TextView text = (TextView) rowView.findViewById(R.id.menuid);
        // textView.setid.setId(values.get(position).getId());
        Log.e("testing", values.get(position).toString());
        textView.setText(values.get(position).getNaam());
//        text.setText(values.get(position).getMenu());

        return rowView;
    }
}
