package com.ehb.xavier.prototype.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ehb.xavier.api.contactEndpoint.model.Contact;
import com.ehb.xavier.prototype.R;

import java.util.List;

public class NameAdapter extends ArrayAdapter<Contact> {
    private Context mContext;
    private int row;
    private List<Contact> list;

    public NameAdapter(Context context, int textViewResourceId, List<Contact> list) {
        super(context, textViewResourceId, list);

        this.mContext = context;
        this.row = textViewResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        if ((list == null) || ((position + 1) > list.size()))
            return view; // Can't extract item

        Contact obj = list.get(position);

        holder.name = (TextView) view.findViewById(R.id.Username);

        if (null != holder.name && null != obj && obj.getFirstname()
                .length() != 0) {
            holder.name.setText(obj.getFirstname());
        }


        return view;
    }

    public static class ViewHolder {
        public TextView name;
    }
}
