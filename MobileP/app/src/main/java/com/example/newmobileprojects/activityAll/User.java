package com.example.newmobileprojects.activityAll;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public int user_no;
    public int student_id;
    public int student_pw;
    public String user_name;
    public String user_place;
    public int user_phone;

    //=============== Getter and Setter ===============

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getStudent_pw() {
        return student_pw;
    }

    public void setStudent_pw(int student_pw) {
        this.student_pw = student_pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_place() {
        return user_place;
    }

    public void setUser_place(String user_place) {
        this.user_place = user_place;
    }

    public int getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(int user_phone) {
        this.user_phone = user_phone;
    }


    //=============== Getter and Setter ===============

    //Construct

    public User() {

    }

    public User(String user_name,  int student_id, int student_pw, String user_place, int user_phone) {
        this.student_id = student_id;
        this.user_name = user_name;
        this.user_place = user_place;
        this.user_phone = user_phone;
        this.student_pw = student_pw;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + user_name + '\'' +
                ", Student ID='" + student_id + '\'' +
                '}';
    }






}