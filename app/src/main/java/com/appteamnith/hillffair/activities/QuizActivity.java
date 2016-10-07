package com.appteamnith.hillffair.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.appteamnith.hillffair.R;

public class QuizActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button enter_quiz,leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        enter_quiz=(Button)findViewById(R.id.enter_quiz);

        enter_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizActivity.this,QuizQuestionActivity.class));
            }
        });

    }
}
