package com.appteamnith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.models.ClubModel;
import com.appteamnith.hillffair.models.ClubModel2;
import com.appteamnith.hillffair.utilities.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sahil ramola on 5/10/16.
 */

public class ClubActivity extends AppCompatActivity {
    private String club_name;
    private CollapsingToolbarLayout ctl;
    private Toolbar toolbar;
    private ImageView grup_img;
    private TextView clubName;
    private TextView description;
    private FrameLayout frameLayout;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPref pref = new SharedPref(this);
        setThem(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_activity);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        frameLayout= (FrameLayout) findViewById(R.id.layout_data);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar_Detail);
        grup_img= (ImageView) findViewById(R.id.image_View);
        clubName= (TextView) findViewById(R.id.grup_name);
        description= (TextView) findViewById(R.id.desc_club);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if (i != null) {
            if (i.hasExtra(EventActivity.CLUB_NAME))
                club_name = i.getStringExtra(EventActivity.CLUB_NAME);
            ctl.setTitle(club_name);
            showData(club_name);
        }


    }


  private void showData(final String club_name){
      Call<ClubModel2> getClubData= Utils.getRetrofitService().getClubInfo(club_name);
      getClubData.enqueue(new Callback<ClubModel2>() {
          @Override
          public void onResponse(Call<ClubModel2> call, Response<ClubModel2> response) {
              progressBar.setVisibility(View.INVISIBLE);
              frameLayout.setVisibility(View.VISIBLE);
              ClubModel2 data=response.body();
              if(data!=null&&response.isSuccess()){
                  if(data.isSuccess()){
                      ClubModel clubdata=data.getProfile();
                      clubName.setText(clubdata.getName());
                      description.setText(clubdata.getDescription());
                      Glide.with(ClubActivity.this).load(clubdata.getPhoto()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.person_icon).into(grup_img);
                  }
                  else {
                      progressBar.setVisibility(View.INVISIBLE);
                      frameLayout.setVisibility(View.INVISIBLE);
                      Toast.makeText(ClubActivity.this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                  }
              }
          }

          @Override
          public void onFailure(Call<ClubModel2> call, Throwable t) {
              progressBar.setVisibility(View.INVISIBLE);
              frameLayout.setVisibility(View.INVISIBLE);
              t.printStackTrace();
              Toast.makeText(ClubActivity.this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();

          }
      });
  }
}
