package com.example.layoutdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LayoutAdapter extends BaseAdapter {

    private Context context;
    private String[] titles;
    private String[] descriptions;

    public LayoutAdapter(Context context, String[] titles, String[] descriptions) {
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.itemTitle);
        TextView description = convertView.findViewById(R.id.itemDescription);

        title.setText(titles[position]);
        description.setText(descriptions[position]);

        return convertView;
    }
}
