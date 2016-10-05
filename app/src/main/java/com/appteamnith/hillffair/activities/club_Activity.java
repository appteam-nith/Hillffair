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
import com.appteamnith.hillffair.models.club_model;
import com.appteamnith.hillffair.models.club_model2;
import com.appteamnith.hillffair.utilities.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sahil ramola on 5/10/16.
 */

public class club_Activity extends AppCompatActivity {
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
      Call<club_model2> getClubData= Utils.getRetrofitService().getClubInfo(club_name);
      getClubData.enqueue(new Callback<club_model2>() {
          @Override
          public void onResponse(Call<club_model2> call, Response<club_model2> response) {
              progressBar.setVisibility(View.INVISIBLE);
              frameLayout.setVisibility(View.VISIBLE);
              club_model2 data=response.body();
              if(data!=null&&response.isSuccess()){
                  if(data.isSuccess()){
                      club_model clubdata=data.getProfile();
                      clubName.setText(clubdata.getName());
                      description.setText(clubdata.getDescription());
                      Glide.with(club_Activity.this).load(clubdata.getPhoto()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.person_icon).into(grup_img);
                  }
                  else {
                      progressBar.setVisibility(View.INVISIBLE);
                      frameLayout.setVisibility(View.INVISIBLE);
                      Toast.makeText(club_Activity.this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                  }
              }
          }

          @Override
          public void onFailure(Call<club_model2> call, Throwable t) {
              progressBar.setVisibility(View.INVISIBLE);
              frameLayout.setVisibility(View.INVISIBLE);
              t.printStackTrace();
              Toast.makeText(club_Activity.this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();

          }
      });
  }
}
