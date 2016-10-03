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

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.adapters.CardAdapter;
import com.appteamnith.hillffair.modals.CardsData;

import java.util.ArrayList;
import java.util.List;

public class NewsfeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardsData> card_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        card_data = new ArrayList<>();
        adapter = new CardAdapter(this, card_data);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        prepareCards();

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
    private void prepareCards() {


        CardsData a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
        a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new CardsData("abcd",null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new CardsData("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);



        adapter.notifyDataSetChanged();
    }
}