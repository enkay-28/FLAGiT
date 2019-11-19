package com.test.flagit;

import android.app.Activity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private  ArrayList<String> namestr = new ArrayList<String>();
    private  ArrayList<String> loc = new ArrayList<String>();
    private  ArrayList<String> crime = new ArrayList<String>();
    private ArrayList<String> photo = new ArrayList<String>();
    private  ArrayList<Boolean> flag = new ArrayList<Boolean>();
    Switch s;
    public ListAdapter(Activity context,  ArrayList<String> namestr, ArrayList<String> loc, ArrayList<String> crime, ArrayList<String> photo, ArrayList<Boolean> flag) {
        super(context,R.layout.layout_language_list_item,namestr );
        this.context = context;
        this.namestr = namestr;
        this.loc = loc;
        this.crime = crime;
        this.photo = photo;
        this.flag = flag;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_language_list_item, null,true);

        TextView name = (TextView) rowView.findViewById(R.id.nametrr);
        TextView loc1 = (TextView) rowView.findViewById(R.id.location1);
        TextView crime1 = (TextView) rowView.findViewById(R.id.crime1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        s = (Switch)rowView.findViewById(R.id.switch1);

        name.setText(namestr.get(position));
        loc1.setText(loc.get(position));
        crime1.setText(crime.get(position));
        imageView.setImageBitmap(BitmapFactory.decodeFile(photo.get(position)));

        s.setChecked(flag.get(position));
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s.setChecked(true);
                }
                else
                {
                    s.setChecked(true);
                }

            }
        });


        return rowView;

    };
}