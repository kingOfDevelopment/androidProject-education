package com.example.kch.educationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ResultActivity extends Activity {

    private Button againBtn;
    private int percent;
    private ImageView imgView;
    private TextView text1;
    private ProgressBar pro;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        percent = intent.getIntExtra("percent", 0);
        str = intent.getStringExtra("category");
        getActionBar().setTitle(str);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(str + "categoryCompleted", true);
        editor.putInt(str + "Marks", percent);
        editor.commit();

        text1 = (TextView) findViewById(R.id.resultCon);
        if (percent >= 60)
            text1.setText("Well done, You got " + String.valueOf(percent) + "%!");
        if (percent < 60)
            text1.setText("OK, You got " + String.valueOf(percent) + "%, Learn more or try again!");
        imgView = (ImageView) findViewById(R.id.resultImg);
        if (percent >= 90)
        {
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.platium));
        }

        if (percent < 90 && percent >= 80) {
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.golden));
        }
        if (percent < 80 && percent >= 70) {
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.silver));
        }
        if (percent < 70 && percent >= 60) {
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.bronze));
        }
        if (percent < 60){
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.custom));
        }

    }


    public void onAgain(View view)
    {
        Intent i = new Intent(this, QuestionActivity.class);
        i.putExtra("category", str);
        startActivity(i);
    }

    public void onLearn(View view)
    {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse("http://app.mathsandscience.com/states-of-matter-and-kinetic-theory/") );
        startActivity(browse);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case  R.id.action_settings:

                return true;
            case R.id.action_blend:
                Intent i = new Intent(this, BlendActivity.class);
                startActivity(i);
                return true;
            case R.id.action_about:
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return true;
            case R.id.action_theme:
                i = new Intent(this, CategoryActivity.class);
                startActivity(i);
                return true;
            case R.id.action_result:
                i = new Intent(this, AllresultActivity.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void handle(String ptr){
        Intent i = new Intent(this, QuestionActivity.class);
        i.putExtra("category", ptr);
        startActivity(i);
    }

}
