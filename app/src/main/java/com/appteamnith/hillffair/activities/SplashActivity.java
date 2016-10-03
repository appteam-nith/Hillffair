package com.appteamnith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.adapters.CardAdapter;
import com.appteamnith.hillffair.modals.newsfeed_model2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static final long TIME_SPLASH =5000 ;
    private ImageView image_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        image_splash = (ImageView) findViewById(R.id.image_spalsh);
        Target<GlideDrawable> into = Glide.with(SplashActivity.this).load(R.raw.splash).into(image_splash);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
                  finish();
            }
        },TIME_SPLASH);
    }

}

