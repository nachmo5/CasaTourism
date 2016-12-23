package com.johndoe.mtourismbeta;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JohnDoe on 12/05/2016.
 */
public class CustomAdapter  extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public CustomAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.custom_layout, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_layout, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);

        return rowView;

    };
}
