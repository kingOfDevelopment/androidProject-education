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
public class AllResultAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;



    public AllResultAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_allresult, parent, false);
        TextView check1 = (TextView) rowView.findViewById(R.id.allresult_textView1);
        TextView check2 = (TextView) rowView.findViewById(R.id.allresult_textView2);
        ImageView moreBtn = (ImageView) rowView.findViewById(R.id.allresult_completeBtn);
        check1.setText(values[position]);
        SharedPreferences pref = getContext().getApplicationContext().getSharedPreferences("MyPref", 0);
        int mark = pref.getInt(values[position] + "Marks", 0);
        boolean bol = pref.getBoolean(values[position] + "categoryCompleted", false);
        if (bol) {
            moreBtn.setVisibility(View.VISIBLE);
            check2.setText(String.valueOf(mark) + "%");
            if (mark >= 90)
            {
                moreBtn.setImageDrawable(getContext().getResources().getDrawable(R.drawable.platium));
            }

            if (mark < 90 && mark >= 80) {
                moreBtn.setImageDrawable(getContext().getResources().getDrawable(R.drawable.golden));
            }
            if (mark < 80 && mark >= 70) {
                moreBtn.setImageDrawable(getContext().getResources().getDrawable(R.drawable.silver));
            }
            if (mark < 70 && mark >= 60) {
                moreBtn.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bronze));
            }
            if (mark < 60){
                moreBtn.setImageDrawable(getContext().getResources().getDrawable(R.drawable.custom));
            }
        } else {
            moreBtn.setVisibility(View.INVISIBLE);
            check2.setText("Not Completed");
        }
        return rowView;
    }

}
