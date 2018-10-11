package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button btnPlay;
    private Button btnScoreBoard;

    View.OnClickListener playListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), GamePageActivity.class);

            startActivity(intent);
        }
    };

    View.OnClickListener ScoreBoardListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ScoreBoardPageActivity.class);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button)findViewById(R.id.btn_play);
        btnScoreBoard = (Button)findViewById(R.id.btn_score_board);

        btnPlay.setOnClickListener(playListener);
        btnScoreBoard.setOnClickListener(ScoreBoardListener);

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
