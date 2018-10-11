package com.example.iidah.brainpicturequiz.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class UserHighScore {
    private String name;
    private int score;

    private long dbId;

    public UserHighScore(String name, int score){
        this.name = name;
        this.score = score;
    }
    public UserHighScore(String name, int score, long dbId){
        this(name, score);
        this.dbId = dbId;
    }


    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public int getScore(){return score;}
    public void setScore(int score){this.score = score;}
    public long getDbId(){return dbId;}
    public void setDbId(long dbId){this.dbId = dbId;}

    public String toString(){return name;}

}

