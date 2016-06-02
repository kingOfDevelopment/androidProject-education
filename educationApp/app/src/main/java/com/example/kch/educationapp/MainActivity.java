package com.example.kch.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
import java.util.List;


public class MainActivity extends Activity {

    private Button startBtn;
    private static int countOfParse = 0;
    private QuestionHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();

    }
    public void onContinue(View view)
    {
        Intent i = new Intent(this, QuestionActivity.class);
        i.putExtra("continue", true);
        startActivity(i);
    }
    public void onStart(View view)
    {
        Intent i = new Intent(this, CategoryActivity.class);
        startActivity(i);
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

    public void initDB()
    {
        db = new QuestionHandler(this);
        db.setTableName("ChemicalBonding");
        db.cleanDB();
        String[] answer_t_1 = {"", "I "+ Html.fromHtml("&prop;") + " v", "I " + Html.fromHtml("&prop;") +" 1/V", "Current I = V", "", ""};
        int[] right_answer_t_1 = {0, 1, 0,0,0,0};
        db.addQuestion(new Question(1, 0, 1, "1.Concerning the relationship between current and potential difference in Ohmafs law:",
                "chemistry_1", 3, answer_t_1, 1, right_answer_t_1));

        String[] answer_t_2 = {"", "Covalent bonds", "Ionic bonds", "Hydrogen bonds", "Polar covalent bonds", ""};
        int[] right_answer_t_2 = {0, 3, 0, 0, 0, 0};
        db.addQuestion(new Question(2,0,1,"2.The bonds between water molecules (the blue dotted lines in the above sketch) are called:",
                "chemistry_2", 4, answer_t_2, 1, right_answer_t_2));

        String[] answer_t_3 = {"", "Polar covalent bonds", "Covalent bonds", "Coordinate covalent bonds", "Ionic bonds", ""};
        int[] right_answer_t_3 = {0, 1, 0, 0, 0, 0};
        db.addQuestion(new Question(3,0,1,"3.The bonds between the atoms in the ammonia molecule are called:",
                "chemistry_3", 4, answer_t_3, 1, right_answer_t_3));

        String[] answer_t_4 = {"", "Linear shape", "Trigonal planar shape", "Tetrahedral shape", "Trigonal bipyramidal shape", "Octahedral shape"};
        int[] right_answer_t_4 = {0, 3, 0, 0, 0, 0};
        db.addQuestion(new Question(4,0,1,"4.The shape of the carbon tetrachloride molecule is known as:",
                "chemistry_4", 5, answer_t_4, 1, right_answer_t_4));

        String[] answer_t_5 = {"", "Hydrogen bonds", "Dipole- dipole forces", "Dipole - induced dipole forces", "Induced dipole - induced dipole forces", ""};
        int[] right_answer_t_5 = {0, 2, 0, 0, 0, 0};
        db.addQuestion(new Question(5,0,1,"5.Name the weak intermolecular forces between the HCl molecules in the above sketch:",
                "chemistry_5", 4, answer_t_5, 1, right_answer_t_5));

        String[] answer_t_6 = {"", "Covalent", "Polar covalent", "Hydrogen", "London forces", ""};
        int[] right_answer_t_6 = {0, 3, 0, 0, 0, 0};
        db.addQuestion(new Question(6,0,1,"6.Which type of bond between molecules is responsible for the relatively high boiling points of H2O, HF and NH3?",
                "chemistry_6", 4, answer_t_6, 1, right_answer_t_6));

        String[] answer_t_7 = {"", "4", "3", "5", "6", "2"};
        int[] right_answer_t_7 = {0, 2, 0, 0, 0, 0};
        db.addQuestion(new Question(7,0,1,"7.The normal valency of Al in the molecule Al2O3 is:",
                "chemistry_7", 5, answer_t_7, 1, right_answer_t_7));

        String[] answer_t_8 = {"", "Capillarity", "Adhesion", "Evaporation", "Surface tension", ""};
        int[] right_answer_t_8 = {0, 4, 0, 0, 0, 0};
        db.addQuestion(new Question(8, 0, 1, "8.The phenomenon that allows a water strider to walk on water is:",
                "chemistry_8", 4, answer_t_8, 1, right_answer_t_8));
        // Reading all contacts

        db.setTableName("Physics");
        db.cleanDB();
        String[] answer_a_1 = {"", "I "+ Html.fromHtml("&prop;") + " v", "I " + Html.fromHtml("&prop;") +" 1/V", "Current I = V", "", ""};
        int[] right_answer_a_1 = {0, 1, 3,0,0,0};
        db.addQuestion(new Question(1, 0, 1, "1.Concerning the relationship between current and potential difference in Ohmafs law:",
                "chemistry_1", 3, answer_a_1, 2, right_answer_a_1));

        String[] answer_a_2 = {"", "Covalent bonds", "Ionic bonds", "Hydrogen bonds", "Polar covalent bonds", ""};
        int[] right_answer_a_2 = {0, 3, 0, 0, 0, 0};
        db.addQuestion(new Question(2,0,1,"2.The bonds between water molecules (the blue dotted lines in the above sketch) are called:",
                "chemistry_2", 4, answer_a_2, 1, right_answer_a_2));
    }
}
