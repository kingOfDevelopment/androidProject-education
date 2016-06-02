package com.example.kch.educationapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ric on 2015/10/27.
 */
public class CategoryAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;



    public CategoryAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_category, parent, false);
        TextView check = (TextView) rowView.findViewById(R.id.textView3);
        Button testBtn = (Button) rowView.findViewById(R.id.button);
        Button moreBtn = (Button) rowView.findViewById(R.id.button2);
        check.setText(values[position]);
        ImageView completedBtn = (ImageView) rowView.findViewById(R.id.completeBtn);
        SharedPreferences pref = getContext().getApplicationContext().getSharedPreferences("MyPref", 0);
        boolean bol = pref.getBoolean(values[position] + "categoryCompleted", false);
        if (bol) {
            completedBtn.setVisibility(View.VISIBLE);
        }

        testBtn.setOnClickListener(new MyOnClickListener(position) {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), QuestionActivity.class);
                i.putExtra("category", values[position]);
                getContext().startActivity(i);
            }
        });

        moreBtn.setOnClickListener(new MyOnClickListener(position) {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse("http://app.mathsandscience.com/states-of-matter-and-kinetic-theory/") );
                getContext().startActivity(browse);
            }
        });

        return rowView;
    }
    public class MyOnClickListener implements View.OnClickListener
    {
        public int position;
        public MyOnClickListener(int position)
        {
            this.position = position;
        }

        @Override
        public void onClick(View v)
        {

        }
    }
}
