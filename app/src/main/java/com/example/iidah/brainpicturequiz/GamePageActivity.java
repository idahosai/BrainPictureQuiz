package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePageActivity extends Activity {

    private ImageView imgChangingPictures;
    private ImageView imgSelectedPicture;
    private Button btnAnswer;
    private TextView txtLives;
    private TextView txtUserLives;
    private TextView txtScore;
    private TextView txtUserScore;

    View.OnClickListener answerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PicturePageActivity.class);
            startActivityForResult(intent,1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        imgChangingPictures = (ImageView)findViewById(R.id.img_changing_pictures);
        imgSelectedPicture = (ImageView)findViewById(R.id.img_selected_picture);
        btnAnswer = (Button)findViewById(R.id.btn_answer);
        txtLives = (TextView)findViewById(R.id.txt_lives);
        txtUserLives = (TextView)findViewById(R.id.txt_user_lives);
        txtScore = (TextView)findViewById(R.id.txt_score);
        txtUserScore = (TextView)findViewById(R.id.txt_user_score);

    }
}
