package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

            Toast.makeText(PicturePageActivity.this,String.valueOf(imgResourceId), Toast.LENGTH_SHORT);
            setResult(RESULT_OK,intent);
            finish();
            //v.setOnClickListener(null);//Remove setOnClickListener
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_page);

        imgOne = (ImageView)findViewById(R.id.img_one);
        imgOne.setTag(R.drawable.ic_launcher_background);
        imgOne.setOnClickListener(imgListener);

        imgTwo = (ImageView)findViewById(R.id.img_two);
        imgTwo.setTag(R.drawable.ic_launcher_background);
        imgTwo.setOnClickListener(imgListener);

        imgThree = (ImageView)findViewById(R.id.img_three);
        imgThree.setTag(R.drawable.ic_launcher_background);
        imgThree.setOnClickListener(imgListener);

        imgFour = (ImageView)findViewById(R.id.img_four);
        imgFour.setTag(R.drawable.ic_launcher_background);
        imgFour.setOnClickListener(imgListener);

        imgFive = (ImageView)findViewById(R.id.img_five);
        imgFive.setTag(R.drawable.ic_launcher_background);
        imgFive.setOnClickListener(imgListener);

        imgSix = (ImageView)findViewById(R.id.img_six);
        imgSix.setTag(R.drawable.ic_launcher_background);
        imgSix.setOnClickListener(imgListener);

        imgSeven = (ImageView)findViewById(R.id.img_seven);
        imgSeven.setTag(R.drawable.ic_launcher_background);
        imgSeven.setOnClickListener(imgListener);

        imgEight = (ImageView)findViewById(R.id.img_eight);
        imgEight.setTag(R.drawable.ic_launcher_background);
        imgEight.setOnClickListener(imgListener);

        imgNine = (ImageView)findViewById(R.id.img_nine);
        imgNine.setTag(R.drawable.ic_launcher_background);
        imgNine.setOnClickListener(imgListener);


        //imgOne.setImageResource(R.drawable.ic_launcher_background);

    }
}
