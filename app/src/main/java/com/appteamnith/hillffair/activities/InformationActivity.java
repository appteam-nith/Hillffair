package com.appteamnith.hillffair.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.application.SharedPref;

public class InformationActivity extends AppCompatActivity {

    private Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref = new SharedPref(this);
        setThem(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accept=(Button)findViewById(R.id.accept_and_enter_quiz);

        final SharedPref sp=new SharedPref(this);

        if(sp.getInstructionsReadStatus()){
           accept.setVisibility(View.GONE);
        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.setInstructionsReadStatus(true);
                finish();
            }
        });
    }
}
