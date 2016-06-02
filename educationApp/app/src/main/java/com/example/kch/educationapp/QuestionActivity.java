package com.example.kch.educationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class QuestionActivity extends Activity {

    private ListView listView;
    private TextView textView;
    private ImageView imgView;
    private Button previousBtn;
    public static Button nextBtn;
    float mark;
    private int curLevel;
    private int countOfAsk;
    private Question curQuestion;
    public static int selectedAnswerInRadio;
    private QuestionHandler db;
    List<Question> questions;
    private RadioButton listRadioButton = null;

    int percentOfResult;
    String str;
    public static int[] checked = {0,0,0,0,0,0};
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        Boolean con = intent.getBooleanExtra("continue", false);
        if (!con) {
            str = intent.getStringExtra("category");
            mark = 0;
            initDB();
            getActionBar().setTitle(str);
            countOfAsk = db.getCountOfAsk();
            curLevel = 1;
            setView(curLevel);
        }
        else
        {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            str = sharedPref.getString("category", "Chemical Bonding");
            curLevel = sharedPref.getInt("level", 1);
            mark = sharedPref.getFloat("mark", 0);
            getActionBar().setTitle(str);
            initDB();
            countOfAsk = db.getCountOfAsk();
            setView(curLevel);
        }
    }

    public void onExp(View view) {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse("http://app.mathsandscience.com/states-of-matter-and-kinetic-theory/") );
        startActivity(browse);
    }

    public void onNext(View view) {
        if(nextBtn.getText().equals("Next"))
        {
            curLevel = curLevel + 1;
            if (curLevel > countOfAsk) {
                percentOfResult = (int) (mark / countOfAsk * 100);
                Intent i = new Intent(this, ResultActivity.class);
                i.putExtra("category", str);
                i.putExtra("percent", percentOfResult);
                startActivity(i);
                return;
            }
            nextBtn.setText("Go");
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("on", true);
            editor.putString("category", str);
            editor.putInt("level", curLevel);
            editor.putFloat("mark", mark);
            editor.commit();
            setView(curLevel);
        }
        else if (nextBtn.getText().equals("Go")){
            nextBtn.setText("Next");
            int curmark = 0;
            int k = 0;
            for(int i = 1; i <= curQuestion.getNum_of_answer(); i++){
                listView.getChildAt(i - 1).findViewById(R.id.checkbox).setClickable(false);
            }
            for(int i = 1; i <= curQuestion.getNum_of_right_answer(); i++){
                listView.getChildAt(curQuestion.getRight_answer()[i] - 1).findViewById(R.id.checkbox).setBackgroundColor(Color.argb(255,107, 186, 43));
                int temp = curQuestion.getRight_answer()[i];
                if(checked[temp] == 1)
                {
                    k++;
                }
            }
            int countOfSelectedAsk = 0;
            for(int p = 1; p <= 5; p++)
            {
                if(checked[p] == 1)
                {
                    countOfSelectedAsk++;
                }
            }
            if(k == curQuestion.getNum_of_right_answer() && k == countOfSelectedAsk)
                curmark = 1;
            mark = mark + curmark;
            if(curmark == 1)
            {
                ((ImageView)(findViewById(R.id.answerAccuracyImageView))).setVisibility(View.VISIBLE);
                ((ImageView)(findViewById(R.id.answerAccuracyImageView))).setImageDrawable(getResources().getDrawable(R.drawable.up));
            }
            else{
                ((ImageView)(findViewById(R.id.answerAccuracyImageView))).setVisibility(View.VISIBLE);
                ((ImageView)(findViewById(R.id.answerAccuracyImageView))).setImageDrawable(getResources().getDrawable(R.drawable.down));
            }
        }

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


    public void setView(int lvl) {


        // Create the text view
        setContentView(R.layout.activity_question);
        getAllDataFromBackEnd(lvl);
        ((ImageView)(findViewById(R.id.answerAccuracyImageView))).setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.questionTxt);
        textView.setText(curQuestion.getAsk());
        nextBtn =  ((Button) (findViewById(R.id.nextBtn)));
        nextBtn.setClickable(false);
        nextBtn.setBackground(getResources().getDrawable(R.drawable.buttondisable));
        for (int i = 1; i <= 5; i++)
        {
            checked[i] = 0;
        }
        imgView = (ImageView) findViewById(R.id.imgView);
        setImageSource(lvl);
        if(curQuestion.getAsk_kind_pic() == 0)
            imgView.setVisibility(View.INVISIBLE);
        listView = (ListView) findViewById(R.id.listView);
        String[] values = new String[curQuestion.getNum_of_answer()];
        for(int i = 1; i <= curQuestion.getNum_of_answer(); i++)
            values[i - 1] = curQuestion.getAnswer()[i];
        CheckboxAdapter adapter = new CheckboxAdapter(this, values);
        listView.setAdapter(adapter);


    }

    public void getAllDataFromBackEnd(int lvl)
    {
        curQuestion = questions.get(lvl - 1);
        int i = 1;
    }

    public void initDB()
    {
        db = new QuestionHandler(this);
        questions = db.getAllQuestions();
    }
    public void setImageSource(int lvl){
        if (str.equals("ChemicalBonding")){
            if(lvl == 1)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_1));
            }
            if(lvl == 2)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_2));
            }
            if(lvl == 3)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_3));
            }
            if(lvl == 4)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_4));
            }
            if(lvl == 5)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_5));
            }
            if(lvl == 6)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_6));
            }
            if(lvl == 7)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_7));
            }
            if(lvl == 8)
            {
                imgView.setImageDrawable(getResources().getDrawable(R.drawable.chemistry_8));
            }
        }
    }
}