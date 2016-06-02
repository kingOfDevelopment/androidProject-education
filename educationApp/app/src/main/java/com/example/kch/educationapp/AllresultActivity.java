package com.example.kch.educationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AllresultActivity extends Activity {

    private Button startBtn;
    private static int countOfParse = 0;
    private ListView listView;
    private QuestionHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allresult);
        listView = (ListView) findViewById(R.id.allresult_listView);
        db = new QuestionHandler(this);
        ArrayList<String> temp = db.getTableNames();
        int listSize = temp.size();
        String[] values = new String[listSize - 1];
        for (int i = 1; i < listSize; i++){
            values[i - 1] = temp.get(i);
        }

        AllResultAdapter adapter = new AllResultAdapter(this, values);
        listView.setAdapter(adapter);
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
