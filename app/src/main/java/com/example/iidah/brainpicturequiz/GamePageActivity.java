package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GamePageActivity extends Activity {

    private ImageView imgChangingPictures;
    private ImageView imgSelectedPicture;
    private Button btnAnswer;
    private TextView txtLives;
    private TextView txtUserLives;
    private TextView txtScore;
    private TextView txtUserScore;

    int arrayImage[] = new int[]{R.drawable.batman,
            R.drawable.blackpanther,
            R.drawable.captainamerica,
            R.drawable.greenlantern,
            R.drawable.ironfist,
            R.drawable.ironman,
            R.drawable.spiderman,
            R.drawable.superman,
            R.drawable.thor};

    int correctAnswer;
    int counter = 0;

    View.OnClickListener answerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PicturePageActivity.class);
            startActivityForResult(intent,2);
        }
    };

    View.OnClickListener changeImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            randomImage();

            if(counter==1){
                view.setOnClickListener(null);
                counter = 0;
            }
            else{
                counter++;
            }

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


        imgChangingPictures.setOnClickListener(changeImageListener);
        btnAnswer.setOnClickListener(answerListener);
    }

    public void randomImage(){

        int imgResource = arrayImage[new Random().nextInt(9)];
        imgChangingPictures.setImageResource(imgResource);
        correctAnswer = imgResource;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        imgChangingPictures.setOnClickListener(changeImageListener);
        if(resultCode == RESULT_OK){
            int answerResourceId = data.getIntExtra("Answer",0);
            imgSelectedPicture.setImageResource(answerResourceId);
            if(answerResourceId == correctAnswer){
                //int pastScore = Integer.valueOf(txtUserScore.getText().toString());
                int pastScore = Integer.valueOf(getResources().getString(R.string.txt_user_score_value));
                pastScore++;
                txtUserScore.setText(getResources().getString(R.string.empty_insert,String.valueOf(pastScore)));    //it must have %s to format into
                Toast.makeText(this, "You got it correct!!", Toast.LENGTH_SHORT);
            }
            else{
                //int newLivesNumber = Integer.valueOf(txtUserLives.getText().toString());
                int newLivesNumber = Integer.valueOf(getResources().getString(R.string.txt_user_lives_value));
                newLivesNumber--;
                txtUserLives.setText(getResources().getString(R.string.empty_insert,String.valueOf(newLivesNumber)));
                Toast.makeText(this, "Ops wronge answer", Toast.LENGTH_SHORT);
            }
        }
    }
}

