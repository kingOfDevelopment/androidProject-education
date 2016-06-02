package com.example.kch.educationapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "QuestionDB";

    // Contacts table name


    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static String table_name;

    public void setTableName(String tb_name) {
        table_name = tb_name;
    }

    public QuestionHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);

        String CREATE_QUESTION_TABLE = "CREATE TABLE " + table_name + "("
                + "ask_no " + " INTEGER ,"
                + "ask_kind " + "INTEGER ," + "ask_kind_pic " + "INTEGER ," + "ask " + "TEXT, " + "ask_pic " + "TEXT," + "num_of_answer " + "INTEGER,"
                + "answer_1 " + "TEXT,"+ "answer_2 " + "TEXT,"+ "answer_3 " + "TEXT,"+ "answer_4 " + "TEXT,"+ "answer_5 " + "TEXT,"
                + "num_of_right_answer " + "INTEGER,"
                + "right_answer_1 " + "INTEGER,"+ "right_answer_2 " + "INTEGER,"+ "right_answer_3 " + "INTEGER,"+ "right_answer_4 " + "INTEGER,"+ "right_answer_5 " + "INTEGER"
                + ")";
        db.execSQL(CREATE_QUESTION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + table_name);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact\

    void cleanDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ask_no", question.getAsk_no()); // Contact Name
        values.put("ask_kind", question.getAsk_kind()); // Contact Phone
        values.put("ask", question.getAsk());
        values.put("ask_pic", question.getAsk_pic());
        values.put("ask_kind_pic", question.getAsk_kind_pic());
        values.put("num_of_answer", question.getNum_of_answer());
        for(int i = 1; i <= 5; i++)
            values.put("answer_" + Integer.toString(i), question.getAnswer()[i]);
        values.put("num_of_right_answer", question.getNum_of_right_answer());
        for(int i = 1; i <= 5; i++)
            values.put("right_answer_" + Integer.toString(i), question.getRight_answer()[i]);
        // Inserting Row
        db.insert(table_name, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Question getQuestion(int ask_no_t) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table_name, new String[] { "ask_no",
                        "ask_kind", "ask_kind_pic", "ask", "ask_pic", "num_of_answer", "answer_1", "answer_2", "answer_3",
                "answer_4", "answer_5", "num_of_right_answer", "right_answer_1", "right_answer_2", "right_answer_3"
                        , "right_answer_4", "right_answer_5"}, KEY_ID + "=?",
                new String[] { String.valueOf(ask_no_t) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String[] answer_t = new String[6];
        int[] right_answer_t = new int[6];
        for(int i = 1; i <= Integer.parseInt(cursor.getString(5)); i++)
        {
            answer_t[i] = cursor.getString(5 + i);
        }
        for (int i = 1; i <= Integer.parseInt(cursor.getString(11)); i++)
        {
            right_answer_t[i] = Integer.parseInt(cursor.getString(11 + i));
        }
        Question question = new Question(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)),
                answer_t, Integer.parseInt(cursor.getString(11)), right_answer_t);
        // return contact
        return question;
    }

    // Getting All Contacts
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + table_name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setAsk_no(Integer.parseInt(cursor.getString(0)));
                question.setAsk_kind(Integer.parseInt(cursor.getString(1)));
                question.setAsk_kind_pic(Integer.parseInt(cursor.getString(2)));
                question.setAsk(cursor.getString(3));
                question.setAsk_pic(cursor.getString(4));
                question.setNum_of_answer(Integer.parseInt(cursor.getString(5)));
                question.setNum_of_right_answer(Integer.parseInt(cursor.getString(11)));
                String[] answer_t = new String[6];
                int[] right_answer_t = new int[6];
                for(int i = 1; i <= 5; i++)
                {
                    answer_t[i] = cursor.getString(5 + i);
                }
                for (int i = 1; i <= 5; i++)
                {
                    right_answer_t[i] = Integer.parseInt(cursor.getString(11 + i));
                }
                question.setAnswer(answer_t);
                question.setRight_answer(right_answer_t);
                // Adding contact to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionList;
    }

    public int getCountOfAsk(){
        List<Question> questionList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + table_name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setAsk_no(Integer.parseInt(cursor.getString(0)));
                question.setAsk_kind(Integer.parseInt(cursor.getString(1)));
                question.setAsk_kind_pic(Integer.parseInt(cursor.getString(2)));
                question.setAsk(cursor.getString(3));
                question.setAsk_pic(cursor.getString(4));
                question.setNum_of_answer(Integer.parseInt(cursor.getString(5)));
                question.setNum_of_right_answer(Integer.parseInt(cursor.getString(11)));
                String[] answer_t = new String[6];
                int[] right_answer_t = new int[6];
                for(int i = 1; i <= 5; i++)
                {
                    answer_t[i] = cursor.getString(5 + i);
                }
                for (int i = 1; i <= 5; i++)
                {
                    right_answer_t[i] = Integer.parseInt(cursor.getString(11 + i));
                }

                question.setAnswer(answer_t);
                question.setRight_answer(right_answer_t);
                // Adding contact to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionList.size();
    }
    public ArrayList<String> getTableNames(){
        final ArrayList<String> dirArray = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int index = c.getColumnIndex("name");
            dirArray.add(c.getString(index));
            c.moveToNext();
        }
        c.close();
        return dirArray;
    }

    // Updating single contact
    /*public int updateContact(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, question.getName());
        values.put(KEY_PH_NO, question.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }*/


    // Getting contacts Count
    public int getQuestionsCount() {
        String countQuery = "SELECT  * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}