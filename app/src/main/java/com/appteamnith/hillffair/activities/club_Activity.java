package com.appteamnith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.modals.club_model;
import com.appteamnith.hillffair.modals.club_model2;
import com.appteamnith.hillffair.modals.newsfeed_model;
import com.appteamnith.hillffair.modals.newsfeed_model2;
import com.appteamnith.hillffair.utilities.Utils;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by parvesh_dhull on 5/10/16.
 */

public class club_Activity extends AppCompatActivity {
    private String club_name;
    private CollapsingToolbarLayout ctl;
    private Toolbar toolbar;
    private ImageView grup_img;
    private TextView clubName;
    private TextView description;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_activity);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.app_bar_layout);

        setSupportActionBar(toolbar);

        Intent i =getIntent();
        if(i!=null){
            if(i.hasExtra(EventActivity.CLUB_NAME))
            club_name=i.getStringExtra(EventActivity.CLUB_NAME);
            ctl.setTitle(club_name);
        }


    }
  private  void showClub(String club_name)

  {
      Call<club_model2> clubDetailResponce=Utils.getRetrofitService().getClubInfo(club_name);
      clubDetailResponce.enqueue(new Callback<club_model2>() {
          @Override
          public void onResponse(Call<club_model2> call, Response<club_model2> response) {
              club_model data = response.body().getProfile();

              if(data.getName()!=null&&!data.getName().isEmpty())
                  holder.clubName.setText(data.getName());
              if(data.getDescription()!=null&&!data.getDescription().isEmpty())
                  holder.description.setText(data.getDescription());


              if(data.getPhoto()!=null&&!data.getPhoto().isEmpty())
                  Glide.with(mContext).load(data.getPhoto()).into(holder.grup_img);
              else
                  holder.grupimg.setImageResource(R.drawable.photo1);


          }

          @Override
          public void onFailure(Call<club_model2> call, Throwable t) {

          }
      });

      public MyViewHolder(View view) {
      super(view);
      grup_img = (ImageView) findViewById(R.id.image_View);
      clubName = (TextView) findViewById(R.id.grup_name);
      description = (TextView) findViewById(R.id.desc_club);


  }
  }
}
