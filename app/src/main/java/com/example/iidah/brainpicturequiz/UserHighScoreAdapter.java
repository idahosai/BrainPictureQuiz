package com.example.iidah.brainpicturequiz;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.iidah.brainpicturequiz.model.UserHighScore;
import java.util.ArrayList;


public class UserHighScoreAdapter extends ArrayAdapter<UserHighScore> {
    private TextView txtUserPlacement;
    int num = 0;
    //private TextView txtName;
    //private TextView txtSavedScore;
    private Context context;
    private int resource;
    public UserHighScoreAdapter(Context context, int resource, ArrayList<UserHighScore> userHighScores) {
        super(context, resource, userHighScores);

        // set this class's context to be used to get LayoutInflator
        this.context = context;

        // resource id for the list_media_info.xml
        this.resource = resource;
    }

    // generating item's view of a ListView
    public View getView(int position, View convertView, ViewGroup parent) {

        // check to see if we have a valid View to work with
        if (convertView == null) {

            // inflate the row from list_media to a View structure
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);

        }

        // initializing feild variables
        //txtName = convertView.findViewById(R.id.txt_name);
        //txtSavedScore = convertView.findViewById(R.id.txt_saved_score);
        txtUserPlacement = convertView.findViewById(R.id.txt_user_placement);

        // calling the getItem method of the superclass that will get the corresponding SocialMedia that we want to display in the current row
        UserHighScore profile = getItem(position);

        // populate the two views with info from the profile
        txtUserPlacement.setText(context.getResources().getString(R.string.empty_insert, String.valueOf(num), profile.getName(),String.valueOf(profile.getScore())));
        num++;
        //txtName.setText(context.getResources().getString(R.string.empty_insert, profile.getName()));
        //txtSavedScore.setText(context.getResources().getString(R.string.empty_insert, String.valueOf(profile.getScore())));



        // return the modified view, Will be used to display a list row
        return convertView;
    }
}
