package com.appteamnith.hillffair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    ScrollView layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        logo = (ImageView) findViewById(R.id.logo);
        layout = (ScrollView) findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        final Animation bounceLogo = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.loginlogo_anim);
        final Animation layoutFade = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in);


        logo.startAnimation(bounceLogo);
        bounceLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.startAnimation(layoutFade);
                layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
