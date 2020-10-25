package com.dc.msu.wwbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView txtResult,txtExit,txtHome;
    String amount,won;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        amount=getIntent().getStringExtra("amount");
        won=getIntent().getStringExtra("won");
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
        // on losing - MSG
        if (won==null){
            txtHome.setText("Restart");
            txtResult.setText("Oops!! You lost the game. You have won "+amount);
        }
        // on winning  - MSG
        else {
            txtHome.setText("Home");
            txtResult.setText("Congratulations!! You have won the game with "+amount+" prize money");
        }
        txtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (won!=null){
                    finish();
                }
                else {
                    finish();
                    startActivity(new Intent(getApplicationContext(),QuizActivity.class));

                }
            }
        });
    }

    private void initView() {
        txtResult=findViewById(R.id.txtResult);
        txtExit=findViewById(R.id.txtExit);
        txtHome=findViewById(R.id.txtHome);
    }
}