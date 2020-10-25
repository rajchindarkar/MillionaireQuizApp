package com.dc.msu.wwbm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    DatabaseHelper helper;
    ArrayList<Quiz> questions=new ArrayList<>();
    TextView txtQues,txtOpt1,txtOpt2,txtOpt3,txtOpt4,txtAmt;
    int currentQues=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initView();
        helper=new DatabaseHelper(getApplicationContext());
        loadData();
        setData(questions.get(currentQues).getQuestion(),questions.get(currentQues).getOptions());
        txtOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer(txtOpt1);
            }
        });
        txtOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer(txtOpt2);
            }
        });
        txtOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer(txtOpt3);
            }
        });
        txtOpt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAnswer(txtOpt4);
            }
        });
    }

    private void initView() {
        txtAmt=findViewById(R.id.txtAmt);
        txtOpt1=findViewById(R.id.txtOption1);
        txtOpt2=findViewById(R.id.txtOption2);
        txtOpt3=findViewById(R.id.txtOption3);
        txtOpt4=findViewById(R.id.txtOption4);
        txtQues=findViewById(R.id.txtQuestion);
    }
    private void setData(String ques,String option){
        String options[]=option.split(",");
        txtOpt1.setText("A. "+options[0]);
        txtOpt2.setText("B. "+options[1]);
        txtOpt3.setText("C. "+options[2]);
        txtOpt4.setText("D. "+options[3]);
        txtQues.setText(ques);
    }
    private void validateAnswer(TextView textView){
        String text=textView.getText().toString().substring(textView.getText().toString().indexOf(".")+1).trim();
        if (text.equals(questions.get(currentQues).getAnswer().trim())){


            txtAmt.setText(questions.get(currentQues).getPrize());
            Toast.makeText(getApplicationContext(),"Your answer is absolutely correct",Toast.LENGTH_SHORT).show();
            if (currentQues>8){
                finish();
                startActivity(new Intent(getApplicationContext(),ResultActivity.class).putExtra("amount",txtAmt.getText().toString()).putExtra("won","yes"));
            }
            else {
                currentQues+=1;
                setData(questions.get(currentQues).getQuestion(),questions.get(currentQues).getOptions());

            }
            Log.d("CurrentQues", String.valueOf(currentQues));
        }
        else {
            Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),ResultActivity.class).putExtra("amount",txtAmt.getText().toString()));
            finish();

        }
    }
    private void loadData(){
        Cursor res=helper.get();
        Log.d("conutRes", String.valueOf(res.getCount()));
        while (res.moveToNext()){
            questions.add(new Quiz(res.getString(1),res.getString(2),res.getString(3),res.getString(4)));
        }
    }

    @Override
    public void onBackPressed() {
        showExitPopup();
    }

    private void showExitPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        String color = preferences.getString("bgcolor", "#000000");
        builder.setMessage((Html.fromHtml("<font color=" + color + ">Are you sure you want to exit quiz?</font>")));
        builder.setPositiveButton((Html.fromHtml("<font color=" + color + ">Yes</font>")), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton((Html.fromHtml("<font color=" + color + ">NO</font>")), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}