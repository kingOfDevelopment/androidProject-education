package com.example.kch.educationapp;

import android.content.Context;
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
public class CheckboxAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public CheckboxAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_content, parent, false);
        final CheckBox check = (CheckBox) rowView.findViewById(R.id.checkbox);
        check.setText(values[position]);

        check.setOnClickListener(new MyOnClickListener(position) {
            @Override
            public void onClick(View v) {

                if(check.isChecked())
                    QuestionActivity.checked[position + 1] = 1;
                else
                    QuestionActivity.checked[position + 1] = 0;
                int k = 0;
                for(int i = 1; i <= 5; i++)
                {
                    if (QuestionActivity.checked[i] == 1){
                        k++;
                    }
                }
                if (QuestionActivity.nextBtn.getText().equals("Go")) {
                    if (k > 0) {
                        QuestionActivity.nextBtn.setBackground(getContext().getResources().getDrawable(R.drawable.buttonshape));
                        QuestionActivity.nextBtn.setClickable(true);
                    }
                    if (k == 0) {
                        QuestionActivity.nextBtn.setBackground(getContext().getResources().getDrawable(R.drawable.buttondisable));
                        QuestionActivity.nextBtn.setClickable(false);
                    }
                }
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
