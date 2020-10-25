package com.dc.msu.wwbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper helper;
    TextView txtPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPlay=findViewById(R.id.txtPlay);
        //  Inserting/Loading questions on App start.
        helper=new DatabaseHelper(getApplicationContext());
        if (helper.get().getCount()<1){
            insertQuestions();
        }
        txtPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QuizActivity.class));
            }
        });

    }
    //Questions to be inserted
    private void insertQuestions(){
        Boolean isInserted=helper.insert("On February 22, 1989, what group won the first Grammy award for Best Hard Rock/Metal Performance?","Jethro Tull","Metallica,AC/DC,Living Colour,Jethro Tull","$10,000");
        helper.insert("Which of these U.S. Presidents appeared on the television series \"Laugh-In\"?","Richard Nixon","Lyndon Johnson,Richard Nixon,Jimmy Carter,Gerald Ford","$20,000");
        helper.insert("In what language was Anne Frank's original diary first published?","Dutch","Dutch,English,French,German","$30,000");
        helper.insert("The Earth is approximately how many miles away from the Sun?","93 million","93 million,9.3 million,39 million,193 million","$40,000");
        helper.insert("In what country are all U.S. Major League baseballs currently manufactured?","Costa Rica","Costa Rica,Haiti,Dominican Republic,Cuba","$50,000");
        helper.insert("What Shakespeare character says, \"Something is rotten in the state of Denmark\"?","Marcellus","Hamlet,Marcellus,Horatio,Laertes","$1,00,000");
        helper.insert("What insect shorted out an early supercomputer and inspired the term \"computer bug\"?","Moth","Moth,Roach,Fly,Japanese beetle","$1,25,000");
        helper.insert("Which of the following pieces of currency was the first to use the motto \"In God We Trust\"?","Two-cent piece","Nickel,One dollar-bill,Two-cent piece,Five dollar bill","$2,50,000");
        helper.insert("Who was the first NFL player to answer \"I'm going to Disneyland\" in the popular series of TV ads?","Phil Simms","Doug Williams,Marcus Allen,Phil Simms,Joe Montana","$5,00,000");
        helper.insert("Before the American colonies switched to the Gregorian calendar, on what date did their new year start?","March 25","March 25,July 1,September 25,December 1","$10,00,000");
        Log.d("isInserted", String.valueOf(isInserted));
    }
}