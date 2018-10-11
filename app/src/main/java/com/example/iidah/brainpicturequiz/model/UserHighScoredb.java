package com.example.iidah.brainpicturequiz.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserHighScoredb{

    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    public static final String DB_NAME = "userhighscore.db";
    public static final int DB_VERSION = 1;

    public static final String USERHIGHSCORE_TABLE = "userhighscore";

    public static final String ID = "_id";
    public static final int ID_COLUMN = 0;

    public static final String NAME = "name";
    public static final int NAME_COLUMN = 1;

    public static final String SCORE = "score";
    public static final int SCORE_COLUMN = 2;

    public static final String Create_UserHighScore_Table =
            "CREATE TABLE " + USERHIGHSCORE_TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    SCORE + " TEXT)";


    public UserHighScoredb(Context context){
        openHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Create_UserHighScore_Table);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USERHIGHSCORE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
    public UserHighScore saveUserHighScore(UserHighScore userHighScore){
        database = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, userHighScore.getName());
        values.put(SCORE, userHighScore.getScore());

        long dbId = database.insert(USERHIGHSCORE_TABLE, null, values);

        userHighScore.setDbId(dbId);
        database.close();
        return userHighScore;


    }
    public ArrayList<UserHighScore> getAllUserAndHighScore(){
        ArrayList<UserHighScore> userHighScores = new ArrayList<>();

        database = openHelper.getReadableDatabase();
        Cursor result = database.query(USERHIGHSCORE_TABLE,null,null,null,null,null,"name");

        while(result.moveToNext()){
            long dbId = result.getLong(ID_COLUMN);
            String name = result.getString(NAME_COLUMN);
            int score = result.getInt(SCORE_COLUMN);

            userHighScores.add(new UserHighScore(name,score,dbId));

        }
        result.close();
        database.close();
        return userHighScores;

    }



    public ArrayList<UserHighScore> getUserAndHighScoreByName(String name){
        ArrayList<UserHighScore> userHighScores = new ArrayList<>();

        database = openHelper.getReadableDatabase();
        String selectName = "name=?";
        String selectionArgs[] = new String[] {name};
        Cursor result = database.query(USERHIGHSCORE_TABLE,null,selectName,selectionArgs,null,null,null);

        while(result.moveToNext()){
            long dbId = result.getLong(ID_COLUMN);
            String thename = result.getString(NAME_COLUMN);
            int score = result.getInt(SCORE_COLUMN);

            userHighScores.add(new UserHighScore(thename,score,dbId));

        }
        result.close();
        database.close();
        return userHighScores;

    }

    public int deleteUserHighScoreById(String id){
        database = openHelper.getWritableDatabase();
        String where = "_id=?";
        String[] whereArgs = new String[]{id};
        int number = database.delete(USERHIGHSCORE_TABLE, where, whereArgs);
        database.close();
        return number;
    }


}
