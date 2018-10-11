package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iidah.brainpicturequiz.model.UserHighScore;
import com.example.iidah.brainpicturequiz.model.UserHighScoredb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GamePageActivity extends Activity {

    // make feilds
    private ImageView imgChangingPictures;
    private ImageView imgSelectedPicture;
    private Button btnAnswer;
    private TextView txtLives;
    private TextView txtUserLives;
    private TextView txtScore;
    private TextView txtUserScore;
    private static int THE_NOTIFICATION_NUMBER;
    private int arrayImage[] = new int[]{R.drawable.batman,
            R.drawable.blackpanther,
            R.drawable.captainamerica,
            R.drawable.greenlantern,
            R.drawable.ironfist,
            R.drawable.ironman,
            R.drawable.spiderman,
            R.drawable.superman,
            R.drawable.thor};
    private ArrayList<Integer> answerStorage = new ArrayList<>();  // so my thing crash cus i forgot to innitialize a ArrayList
    private ArrayList<Integer> correctAnswers = new ArrayList<>();
    private int counter = 0;
    private int pastScore;
    private int newLivesNumber;
    private View.OnClickListener answerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PicturePageActivity.class);
            startActivityForResult(intent,2);
        }
    };

    private View.OnClickListener changeImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            btnAnswer.setEnabled(false);

            MyTask myTask = new MyTask();
            myTask.execute(arrayImage);
            //randomImage();

            if(counter==1){
                counter = 0;
                GamePageActivity.this.imgChangingPictures.setOnClickListener(null);

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
        // innitialize feilds
        imgChangingPictures = (ImageView)findViewById(R.id.img_changing_pictures);
        imgSelectedPicture = (ImageView)findViewById(R.id.img_selected_picture);
        btnAnswer = (Button)findViewById(R.id.btn_answer);
        txtLives = (TextView)findViewById(R.id.txt_lives);
        txtUserLives = (TextView)findViewById(R.id.txt_user_lives);
        txtScore = (TextView)findViewById(R.id.txt_score);
        txtUserScore = (TextView)findViewById(R.id.txt_user_score);
        newLivesNumber = Integer.valueOf(txtUserLives.getText().toString());
        pastScore = Integer.valueOf(txtUserScore.getText().toString());
        // set widget listeners
        imgChangingPictures.setOnClickListener(changeImageListener);
        btnAnswer.setOnClickListener(answerListener);
        btnAnswer.setEnabled(false);


    }
    // sets the top image on the game page and stores the that new image in a the ArrayList
    // that will eventually contain all the random images displayed on the top screen
    public void randomizing(int pictureValue){
        // sets the top image on the game page and stores that new image in a a ArrayList
        imgChangingPictures.setImageResource(pictureValue);

        answerStorage.add(pictureValue);
    }
    // takes the stored pictures in the ArrayList answerStorage and from that make a new Arraylist
    // that contains only the most reacurring pictures, there can be more than one reocurring picture
    public void correctAnswerCalculator(){
        // innitialize feild
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int tempInt;
        int mostRepeatedPictureCount = 0;
        int mostRepeatedPicture = 0;

        // looping through answerStorage to put all it's element in Hash map because Hashmap has a
        // key and a value and the value can tell us how many times that key(picture) occured.
        for (int i = 0; i < answerStorage.size(); i++)
        {
            // set a variable to hold each element that is from the arrayList with the random pictures
            tempInt = answerStorage.get(i);
            // check if picture is already in the Hashmap, if it is then increase the hashMaps value of that key
            if(map.containsKey(tempInt))
            {
                map.put(tempInt, map.get(tempInt) + 1);
            }
            else
            {
                map.put(tempInt,1);
            }
        }
        // check Hashmap for the picture that appears the most and set it to the local feild in this method
        for (int key: map.keySet())
        {
            // put the first picture that appears the most into the feild values.
            // if there is two that appear thesame ammount of times then it will put the one that appeared first in the HashMap
            if(map.get(key) > mostRepeatedPictureCount){
                // set local feilds to new most repeated picture
                mostRepeatedPicture = key;
                mostRepeatedPictureCount = map.get(key);
            }
        }
        // loop through HashMap
        for(int key: map.keySet()){
            // check if picture appears thesame amount of times as updated local picture
            if(map.get(key) == mostRepeatedPictureCount){
                correctAnswers.add(key);
            }
        }

        answerStorage.clear();
    }
    // called when the intent comes back, check if the user choose the correct picture and punish or reward the user acordingly
    // by calling other methods or doing it manually here
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // make button not clickable again
        btnAnswer.setEnabled(false);
        //
        imgChangingPictures.setOnClickListener(changeImageListener);
        if(resultCode == RESULT_OK){
            int answerResourceId = data.getIntExtra("Answer",0);

            imgSelectedPicture.setImageResource(answerResourceId);
            int count = 0;
            for(int i = 0; i < correctAnswers.size(); i++){
                if(answerResourceId == correctAnswers.get(i)){
                    count = count + 1;
                }
            }

            correctAnswers.clear();
            if(count > 0){
                //int pastScore = Integer.valueOf(txtUserScore.getText().toString());
                //int pastScore = Integer.valueOf(getResources().getString(R.string.txt_user_score_value));
                pastScore++;
                txtUserScore.setText(getResources().getString(R.string.empty_here,String.valueOf(pastScore)));    //it must have %s to format into
                Toast.makeText(this, "You got it correct!!", Toast.LENGTH_SHORT).show();
            }
            else{
                //int newLivesNumber = Integer.valueOf(txtUserLives.getText().toString());
                //int newLivesNumber = Integer.valueOf(getResources().getString(R.string.txt_user_lives_value));
                newLivesNumber--;
                txtUserLives.setText(getResources().getString(R.string.empty_here,String.valueOf(newLivesNumber)));
                Toast.makeText(this, "Ops wronge answer", Toast.LENGTH_SHORT).show();
                if(newLivesNumber == 0){
                    displayDialog();
                    //finish();  //finish shouldn't be here must be after they click something on the dialog screen
                }
            }
        }
    }

    private class MyTask extends AsyncTask<int[],Integer, Void> {

        @Override
        protected Void doInBackground(int[]... ints) {
            int[] holdPicturesInts = ints[0];
            for(int i = 0; i < (holdPicturesInts.length); i++){ // remember to add 2 again
                try {
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int theChosenPicture = holdPicturesInts[new Random().nextInt(9)];
                publishProgress(theChosenPicture);
            }



            return null;
        }

        protected void onProgressUpdate(Integer... values){
            randomizing(values[0]); //so this is how you access these AsyncTask parameters

        }


        @Override
        protected void onPostExecute(Void aVoid) {

            correctAnswerCalculator();
            btnAnswer.setEnabled(true);

        }
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

    public void save (View view){
        /*
        UserHighScore userHighScore = loadUserHighScore();
        UserHighScoredb userHighScoredb = new UserHighScoredb(this);

        userHighScoredb.saveUserHighScore(userHighScore);

        Toast.makeText(this, "student db id is: " + userHighScore.getDbId(), Toast.LENGTH_LONG).show();
        */
    }

    private UserHighScore loadUserHighScore(String userName, int theUserScore) {

        return new UserHighScore(userName,theUserScore);
    }
    private void displayDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText edtUserInfo = new EditText(this);

        DialogInterface.OnClickListener editTextListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String userName = edtUserInfo.getText().toString();
                int theUserScore = Integer.valueOf(txtUserScore.getText().toString());
                UserHighScore userHighScore = loadUserHighScore(userName,theUserScore);
                UserHighScoredb userHighScoredb = new UserHighScoredb(GamePageActivity.this);

                userHighScoredb.saveUserHighScore(userHighScore);

                displayNotification();
                //Toast.makeText(GamePageActivity.this, "student db id is: " + userHighScore.getDbId(), Toast.LENGTH_LONG).show();
                //i don't think this toast will work cus we immediatly move on from that context that it uses
                finish();//finish should go after they click something ON the dialog screen not after the dialog call
            }
        };
        DialogInterface.OnClickListener hereListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        };
        edtUserInfo.setHint("Enter your name");

        builder.setTitle("Player Information")
                .setView(edtUserInfo)
                .setPositiveButton("OK",editTextListener)
                .setNegativeButton("Cancel", hereListener);

        AlertDialog dialog = builder.create();
        dialog.show();

        //displayNotification(); shouldn't be here


    }
    public void displayNotification(){
        //long[] vibrating = new long[]{400,800,400,800};

        PendingIntent notyfyPIntent = PendingIntent.getActivity(getApplicationContext(),0,new Intent(),0);

        Notification notify = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.gobutton)
                .setTicker("Notification")
                .setContentIntent(notyfyPIntent)
                .setAutoCancel(true)
                .setContentTitle("Your score has been added to the Score Board Page!")
                //.setVibrate(vibrating)
                .getNotification();
        NotificationManager manage = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manage.notify(THE_NOTIFICATION_NUMBER,notify);
    }

    public void cancelNotication(View view){
        NotificationManager manage = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        manage.cancel(THE_NOTIFICATION_NUMBER);

    }
}

