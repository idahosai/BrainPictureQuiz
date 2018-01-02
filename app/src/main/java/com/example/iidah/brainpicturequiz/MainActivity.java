package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button btnPlay;
    private Button btnScoreBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button)findViewById(R.id.btn_play);
        btnScoreBoard = (Button)findViewById(R.id.btn_score_board);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                        //activity and lay out are different this here takes the activity
                Intent intent = new Intent(getApplicationContext(), GamePageActivity.class);

                startActivityForResult(intent, 1);
            }
        });
        getPastedSavings();

    }
    protected  void onPause(){
        super.onPause();
        //what would i save here nothing?
    }


    public void getPastedSavings(){

    }
}
