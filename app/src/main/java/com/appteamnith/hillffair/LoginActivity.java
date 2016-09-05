package com.appteamnith.hillffair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class LoginActivity extends AppCompatActivity {

    ImageView logo;
    ScrollView layout;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login= (Button) findViewById(R.id.registar_Btn_login);
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

      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
          }
      });

    }
}
