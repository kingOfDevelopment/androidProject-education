package com.example.kch.educationapp;

/**
 * Created by ric on 2015/10/29.
 */

public class Question {

    //private variables
    int _id;
    int ask_no;
    int ask_kind;
    int ask_kind_pic;
    String ask;
    String ask_pic;
    int num_of_answer;
    String[] answer;
    int num_of_right_answer;
    int[] right_answer;

    // Empty constructor
    public Question(){
    }
    // constructor
    public Question(int ask_no_t, int ask_kind_t, int ask_kind_pic_t, String ask_t, String ask_pic_t, int num_of_answer_t, String[] answer_t, int num_of_right_answer_t, int[] right_answer_t){
        ask_no = ask_no_t;
        ask_kind = ask_kind_t;
        ask_kind_pic = ask_kind_pic_t;
        ask = ask_t;
        ask_pic = ask_pic_t;
        num_of_answer = num_of_answer_t;
        answer = new String[6];
        for(int i = 1; i <= 5; i++)
        {
            answer[i] = answer_t[i];
        }
        num_of_answer = num_of_answer_t;
        num_of_right_answer = num_of_right_answer_t;
        right_answer = new int[6];
        for(int i = 1; i <= 5; i++)
        {
            right_answer[i] = right_answer_t[i];
        }
    }

    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    public int getAsk_no(){
        return this.ask_no;
    }

    public void setAsk_no(int ask_no_t){
        this.ask_no = ask_no_t;
    }

    public int getAsk_kind(){
        return this.ask_kind;
    }

    public void setAsk_kind(int ask_kind_t){
        this.ask_kind = ask_kind_t;
    }

    public int getAsk_kind_pic(){
        return this.ask_kind_pic;
    }

    public void setAsk_kind_pic(int ask_kind_pic_t){
        this.ask_kind_pic = ask_kind_pic_t;
    }

    public String getAsk(){
        return this.ask;
    }

    public void setAsk(String ask_t){
        this.ask = ask_t;
    }

    public String getAsk_pic(){
        return this.ask_pic;
    }

    public void setAsk_pic(String ask_pic_t){
        this.ask_pic = ask_pic_t;
    }

    public int getNum_of_answer(){
        return this.num_of_answer;
    }

    public void setNum_of_answer(int num_of_answer_t){
        this.num_of_answer = num_of_answer_t;
    }

    public String getAnswer_1(){
        return this.answer[1];
    }

    public void setAnswer_1(String answer_1_t){
        this.answer[1] = answer_1_t;
    }

    public String getAnswer_2(){
        return this.answer[2];
    }

    public void setAnswer_2(String answer_2_t){
        this.answer[2] = answer_2_t;
    }

    public String getAnswer_3(){
        return this.answer[3];
    }

    public void setAnswer_3(String answer_3_t){
        this.answer[3] = answer_3_t;
    }

    public String getAnswer_4(){
        return this.answer[1];
    }

    public void setAnswer_4(String answer_4_t){
        this.answer[4] = answer_4_t;
    }

    public String getAnswer_5(){
        return this.answer[5];
    }
    public String[] getAnswer()
    {
        return this.answer;
    }
    public void setAnswer_5(String answer_5_t){
        this.answer[5] = answer_5_t;
    }

    public int getNum_of_right_answer(){
        return this.num_of_right_answer;
    }

    public void setNum_of_right_answer(int num_of_right_answer_t){
        this.num_of_right_answer = num_of_right_answer_t;
    }

    public int getRight_answer_1(){
        return this.right_answer[1];
    }

    public void setRight_answer_1(int right_answer_1_t){
        this.right_answer[1] = right_answer_1_t;
    }

    public int getRight_answer_2(){
        return this.right_answer[2];
    }

    public void setRight_answer_2(int right_answer_2_t){
        this.right_answer[2] = right_answer_2_t;
    }

    public int getRight_answer_3(){
        return this.right_answer[3];
    }

    public void setRight_answer_3(int right_answer_3_t){
        this.right_answer[3] = right_answer_3_t;
    }

    public int getRight_answer_4(){
        return this.right_answer[4];
    }

    public void setRight_answer_4(int right_answer_4_t){
        this.right_answer[4] = right_answer_4_t;
    }

    public int getRight_answer_5(){
        return this.right_answer[5];
    }

    public void setRight_answer_5(int right_answer_5_t){
        this.right_answer[5] = right_answer_5_t;
    }

    public int[] getRight_answer(){
        return right_answer;
    }

    public void setAnswer(String[] answer_t) {
        answer = new String[6];
        for(int i = 1; i <= 5; i++)
            answer[i] = answer_t[i];
    }

    public void setRight_answer(int[] right_answer_t) {
        right_answer = new int[6];
        for(int i = 1; i <= 5; i++)
            right_answer[i] = right_answer_t[i];
    }
    // getting name
    /*public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }*/
}