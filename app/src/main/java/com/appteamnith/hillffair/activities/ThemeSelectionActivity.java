package com.appteamnith.hillffair.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.SharedPref;

public class ThemeSelectionActivity extends AppCompatActivity implements View.OnClickListener {


    private int themeVal;
    ImageButton batman, superman, hulk, wonderwoman, flash, captain;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_selection);
        init();
        context=this;
    }

    void init() {
        batman = (ImageButton) findViewById(R.id.batman);
        superman = (ImageButton) findViewById(R.id.superman);
        hulk = (ImageButton) findViewById(R.id.hulk);
        wonderwoman = (ImageButton) findViewById(R.id.wonderwoman);
        flash = (ImageButton) findViewById(R.id.flash);
        captain = (ImageButton) findViewById(R.id.captain);
        batman.setOnClickListener(this);
        superman.setOnClickListener(this);
        hulk.setOnClickListener(this);
        wonderwoman.setOnClickListener(this);
        flash.setOnClickListener(this);
        captain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.batman:
                themeVal = R.style.batman;
                break;
            case R.id.flash:
                themeVal = R.style.flash;
                break;
            case R.id.hulk:
                themeVal = R.style.hulk;
                break;
            case R.id.wonderwoman:
                themeVal = R.style.wonderwoman;
                break;
            case R.id.superman:
                themeVal = R.style.superman;
                break;
            case R.id.captain:
                themeVal = R.style.captainamerica;
                break;

        }
        savetoSharedPref();
        startActivity(new Intent(ThemeSelectionActivity.this,LoginActivity.class));
        finish();
    }
    
    void savetoSharedPref(){
        SharedPref sharedPref = new SharedPref(context);
        sharedPref.setThemeId(themeVal);
    }

}
