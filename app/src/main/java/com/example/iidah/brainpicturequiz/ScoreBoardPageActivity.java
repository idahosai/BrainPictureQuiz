package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.iidah.brainpicturequiz.model.UserHighScore;
import com.example.iidah.brainpicturequiz.model.UserHighScoredb;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoardPageActivity extends Activity {

    private Button btnBackValue;
    private ListView lstTopThree;
    private ListView lstTopFive;
    UserHighScoreAdapter adapter;
    UserHighScoreAdapter adapterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_page);

        lstTopThree = (ListView)findViewById(R.id.lst_top_three);
        lstTopFive = (ListView)findViewById(R.id.lst_top_five);
        btnBackValue = (Button)findViewById(R.id.btn_back);
        btnBackValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        displayUserHighScores();
    }


    private void displayUserHighScores() {
        UserHighScoredb userHighScoredb = new UserHighScoredb(this);

        ArrayList<UserHighScore> userHighScores;
        ArrayList<UserHighScore> topThreeStored;
        ArrayList<UserHighScore> lastFiveEntryStored;


        int maxLength = 9;

        userHighScores = userHighScoredb.getAllUserAndHighScore();
        int holdSize = userHighScores.size();
        topThreeStored = getTopThreeStoredEntry(userHighScores);
        lastFiveEntryStored = getLastFiveEntryStored(userHighScores);
            //*****this is where i need to limit the number of elements in the ArrayList

        adapter = new UserHighScoreAdapter(this, R.layout.list_score_info, topThreeStored);
        adapterTwo = new UserHighScoreAdapter(this, R.layout.list_score_info, lastFiveEntryStored);

        lstTopThree.setAdapter(adapter);
        lstTopFive.setAdapter(adapterTwo);

        /*if(holdSize >= maxLength){
            deleteUnseenUser(userHighScores, topThreeStored, lastFiveEntryStored);  //for some reason it never goes tho this code
        }*/

        deleteUnseenUser(userHighScores,topThreeStored, lastFiveEntryStored);

    }

    private void deleteUnseenUser(ArrayList<UserHighScore> userHighScores, ArrayList<UserHighScore> topThreeStored, ArrayList<UserHighScore> lastFiveEntryStored) {

        UserHighScoredb userHighScoredb = new UserHighScoredb(this);
        int numberDeleted;

        ArrayList<UserHighScore> theUsersToBeDeleted = new ArrayList<>();

        for(int i = 0; i < userHighScores.size(); i++){
            boolean InFirst = false;
            boolean InSecond = false;
            for(UserHighScore y: topThreeStored){
                if((userHighScores.get(i).getDbId() == y.getDbId() && (userHighScores.get(i).getScore() == y.getScore()) && (userHighScores.get(i).getName() == y.getName()))){
                    //theUsersToBeDeleted.add(userHighScores.get(i));
                    InFirst = true;
                }
            }
            if(InFirst == false){
                for(UserHighScore m: lastFiveEntryStored){
                    if((userHighScores.get(i).getDbId() == m.getDbId() && (userHighScores.get(i).getScore() == m.getScore()) && (userHighScores.get(i).getName() == m.getName()))){
                        InSecond = true;


                    }
                }
            }
            if((InFirst == true) || (InSecond == true)){
                // Do nothing
            }
            else{
                theUsersToBeDeleted.add(userHighScores.get(i));
            }
        }

        if(theUsersToBeDeleted.size() > 0){
            for(UserHighScore w: theUsersToBeDeleted){
                //delete here
                numberDeleted = userHighScoredb.deleteUserHighScoreById(String.valueOf(w.getDbId()));
            }
        }
    }


    private ArrayList<UserHighScore> getLastFiveEntryStored(ArrayList<UserHighScore> userHighScores) {

        UserHighScore temptop = new UserHighScore(null,0);
        temptop.setDbId(0);


        ArrayList<UserHighScore> fiveRecentUsers = new ArrayList<>();



        int theRequireLength = 5;

        if(userHighScores.size() < 5){
            theRequireLength = userHighScores.size();
        }

        while(fiveRecentUsers.size() != theRequireLength) {


            for (int i = 0; i < userHighScores.size(); i++) {

                boolean canItGo = true;
                for (int k = 0; k < fiveRecentUsers.size(); k++) {
                    if (userHighScores.get(i).getDbId() == fiveRecentUsers.get(k).getDbId()) {
                        canItGo = false;
                    }
                }
                if (canItGo == true) {
                    if (userHighScores.get(i).getDbId() > temptop.getDbId()) {
                            temptop.setName(userHighScores.get(i).getName());
                            temptop.setScore(userHighScores.get(i).getScore());
                            temptop.setDbId(userHighScores.get(i).getDbId());
                    }

                }



            }
            fiveRecentUsers.add(temptop);  //does this make it's own copy of is it a referece?
            temptop = new UserHighScore(null,-1);
            temptop.setDbId(0);


        }
        Collections.reverse(fiveRecentUsers);
        return fiveRecentUsers;

    }






    private ArrayList<UserHighScore> getTopThreeStoredEntry(ArrayList<UserHighScore> userHighScores) {
        UserHighScore temptop = new UserHighScore(null,0);
        temptop.setDbId(0);


        ArrayList<UserHighScore> allTopUsers = new ArrayList<>();



        int theRequireLength = 3;

        if(userHighScores.size() < 3){
            theRequireLength = userHighScores.size();
        }

        while(allTopUsers.size() != theRequireLength) {


            for (int i = 0; i < userHighScores.size(); i++) {

                boolean canItGo = true;
                for (int k = 0; k < allTopUsers.size(); k++) {
                    if (userHighScores.get(i).getDbId() == allTopUsers.get(k).getDbId()) {
                        canItGo = false;
                    }
                }
                if (canItGo == true) {
                    if (userHighScores.get(i).getScore() >= temptop.getScore()) {
                        if (userHighScores.get(i).getScore() == temptop.getScore() && (userHighScores.get(i).getDbId() > temptop.getDbId())) {
                            temptop.setName(userHighScores.get(i).getName());
                            temptop.setScore(userHighScores.get(i).getScore());
                            temptop.setDbId(userHighScores.get(i).getDbId());
                        } else if (userHighScores.get(i).getScore() > temptop.getScore()) {
                            temptop.setName(userHighScores.get(i).getName());
                            temptop.setScore(userHighScores.get(i).getScore());
                            temptop.setDbId(userHighScores.get(i).getDbId());
                        }
                    }

                }

            }
            allTopUsers.add(temptop);  //does this make it's own copy of is it a referece?
            temptop = new UserHighScore(null,-1);
            temptop.setDbId(0);


        }
        return allTopUsers;

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item_how_to_play){
            Intent intent = new Intent(getApplicationContext(),HowToPlayActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
