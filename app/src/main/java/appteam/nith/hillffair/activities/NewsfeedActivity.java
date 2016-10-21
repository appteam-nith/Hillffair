package appteam.nith.hillffair.activities;


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

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.CardAdapter;

import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.NewsfeedModel;
import appteam.nith.hillffair.models.NewsfeedModel2;
import appteam.nith.hillffair.utilities.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsfeedActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean loading = true;
    private int  pastVisiblesItems, visibleItemCount, totalItemCount, previousTotal = 0, visibleThreshold = 0,feedNo=0;
    private ArrayList<NewsfeedModel2> list=new ArrayList<>();
    private SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref= new SharedPref(this);
        setTheme(pref.getThemeId());
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

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount)
                            <= (pastVisiblesItems + visibleThreshold)) {

                        list.add(null);
                        adapter.notifyItemInserted(list.size() + 1);
                        feedNo+=11;
                        showData(feedNo);
                        loading = true;
                    }
                }
            }
        });

        showData(1);
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

    private void  showData(int from){
        if(from>1){
            adapter.removeItem(null);
        }
        else if(from==1) {
            list.clear();
            adapter.refresh(list);
            recyclerView.setVisibility(View.GONE);
        }

        Call<NewsfeedModel> newsfeedResponse= Utils.getRetrofitService().getAllNews(""+from,pref.getUserId());
        newsfeedResponse.enqueue(new Callback<NewsfeedModel>() {
            @Override
            public void onResponse(Call<NewsfeedModel> call, Response<NewsfeedModel> response) {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }


                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                if(response!=null&&response.isSuccess()){
                    if(response.body().getFeed()!=null){
                        if(response.body().getFeed().size()>0){
                            list.addAll(response.body().getFeed());

                            adapter.refresh(list);}
                        else {
                            adapter.removeItem(null);
                        }
                    }
                    else {
                        adapter.removeItem(null);
                    }

                }
                else {
                    adapter.removeItem(null);
                    Toast.makeText(NewsfeedActivity.this,"Unable to fetch Data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsfeedModel> call, Throwable t) {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                adapter.removeItem(null);
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(NewsfeedActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRefresh()
    {
        showData(1);
    }
}
