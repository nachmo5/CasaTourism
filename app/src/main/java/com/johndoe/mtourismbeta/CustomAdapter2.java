package com.johndoe.mtourismbeta;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by JohnDoe on 12/05/2016.
 */
public class CustomAdapter2 extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final String[] content;

    public CustomAdapter2(Activity context, String[] title, String[] content) {
        super(context, R.layout.custom_layout2, title);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.title=title;
        this.content=content;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_layout2, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
        TextView txtContent = (TextView) rowView.findViewById(R.id.content);

        txtTitle.setText(title[position]);
        txtContent.setText(content[position]);

        return rowView;

    };
}
