package com.appteamnith.hillffair.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.adapters.CardAdapter;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.models.NewsfeedModel;

import com.appteamnith.hillffair.utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsfeedActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref = new SharedPref(this);
        setThem(pref.getThemeId());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.newsfeed);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
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
        Call<NewsfeedModel> newsfeedResponse= Utils.getRetrofitService().getAllNews(from);
        newsfeedResponse.enqueue(new Callback<NewsfeedModel>() {
            @Override
            public void onResponse(Call<NewsfeedModel> call, Response<NewsfeedModel> response) {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                if(response!=null){
                    adapter.refresh(response.body().getFeed());
                }
            }

            @Override
            public void onFailure(Call<NewsfeedModel> call, Throwable t) {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                   t.printStackTrace();

                progressBar.setVisibility(View.GONE);
                Toast.makeText(NewsfeedActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        showData("1");
    }
}
