package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PicturePageActivity extends Activity {
    private ImageView imgOne;
    private ImageView imgTwo;
    private ImageView imgThree;
    private ImageView imgFour;
    private ImageView imgFive;
    private ImageView imgSix;
    private ImageView imgSeven;
    private ImageView imgEight;
    private ImageView imgNine;
    View.OnClickListener imgListener = new View.OnClickListener() {

        public void onClick(View v) {
            //Log.d("DisplayImage", "on click");

            Intent intent = new Intent();
            ImageView img = (ImageView)v;
            int imgResourceId = Integer.valueOf(img.getTag().toString());

            intent.putExtra("Answer", imgResourceId);

            setResult(RESULT_OK,intent);
            finish();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_page);

        imgOne = (ImageView)findViewById(R.id.img_one);
        imgOne.setTag(R.drawable.ironman);
        imgOne.setOnClickListener(imgListener);

        imgTwo = (ImageView)findViewById(R.id.img_two);
        imgTwo.setTag(R.drawable.batman);
        imgTwo.setOnClickListener(imgListener);

        imgThree = (ImageView)findViewById(R.id.img_three);
        imgThree.setTag(R.drawable.blackpanther);
        imgThree.setOnClickListener(imgListener);

        imgFour = (ImageView)findViewById(R.id.img_four);
        imgFour.setTag(R.drawable.captainamerica);
        imgFour.setOnClickListener(imgListener);

        imgFive = (ImageView)findViewById(R.id.img_five);
        imgFive.setTag(R.drawable.greenlantern);
        imgFive.setOnClickListener(imgListener);

        imgSix = (ImageView)findViewById(R.id.img_six);
        imgSix.setTag(R.drawable.ironfist);
        imgSix.setOnClickListener(imgListener);


        imgSeven = (ImageView)findViewById(R.id.img_seven);
        imgSeven.setTag(R.drawable.thor);
        imgSeven.setOnClickListener(imgListener);

        imgEight = (ImageView)findViewById(R.id.img_eight);
        imgEight.setTag(R.drawable.spiderman);
        imgEight.setOnClickListener(imgListener);

        imgNine = (ImageView)findViewById(R.id.img_nine);
        imgNine.setTag(R.drawable.superman);
        imgNine.setOnClickListener(imgListener);



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
