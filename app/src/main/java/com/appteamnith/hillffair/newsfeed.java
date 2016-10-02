package com.appteamnith.hillffair;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class newsfeed extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<cards_data> card_data;

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
                startActivity(new Intent(newsfeed.this,UploadNewsFeed.class));
            }
        });

    }



    /**
     * Adding few albums for testing
     */
    private void prepareCards() {


        cards_data a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
        a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd",null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);
         a = new cards_data("abcd", null,"fdsfsdfasdfSDffsdfsdfsdfsdfsadfsadfsdfsdfsadfsadfsdfsadfsagdafgdfagdfagdafgadfg",true, 100,"29/09/2016");
        card_data.add(a);



        adapter.notifyDataSetChanged();
    }
}