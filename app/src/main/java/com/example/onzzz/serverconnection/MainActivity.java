package com.example.onzzz.serverconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore"); //拎GameScore呢個class
        query.whereExists("playerName"); //Set條件：有playerName既parse object
        query.whereEqualTo("playerName", "Sean Plott"); //Set條件：所有playerName=SeanPlott既parse object
        query.findInBackground(new FindCallback<ParseObject>() { //開始搵
            public void done(List<ParseObject> scoreList, ParseException e) { //所有result會store係list到
                if (e == null) {
                    Log.d("score", "Name: " + scoreList.get(0).getString("playerName")); //可以用條list去output返data
                } else {
                    Log.d("score", "Error: " + e.getMessage()); //Handle Failure
                }
            }
        });
    }
}
