package com.appteamnith.hillffair.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.activities.UploadNewsFeedActivity;
import com.appteamnith.hillffair.adapters.CardAdapter;
import com.appteamnith.hillffair.modals.CardsData;
import com.appteamnith.hillffair.modals.newsfeed_model;
import com.appteamnith.hillffair.modals.newsfeed_model2;
import com.appteamnith.hillffair.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsfeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.newsfeed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new CardAdapter(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

  showData("1");
        // Button to upload the NewsFeed

        FloatingActionButton upload= (FloatingActionButton) findViewById(R.id.upload_btn);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsfeedActivity.this,UploadNewsFeedActivity.class));
            }
        });

    }



    /**
     * Adding few albums for testing
     */

    private void  showData(String from){

        Call<newsfeed_model> newsfeedResponse= Utils.getRetrofitService().getAllNews(from);
        newsfeedResponse.enqueue(new Callback<newsfeed_model>() {
            @Override
            public void onResponse(Call<newsfeed_model> call, Response<newsfeed_model> response) {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                if(response!=null){
                    adapter.refresh(response.body().getFeed());
                }
            }

            @Override
            public void onFailure(Call<newsfeed_model> call, Throwable t) {
                   t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}