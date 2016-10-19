package com.appteamnith.hillffair.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.appteamnith.hillffair.adapters.contributorsAdapter;
import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.models.contributorsItem;
import com.appteamnith.hillffair.utilities.RecyclerItemClickListener;

import java.util.ArrayList;

public class contributors extends AppCompatActivity {

    RecyclerView rvContributors;
    contributorsAdapter ContributorAdapter;
    Toolbar tbContributers;
    ArrayList<contributorsItem> contributorsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref = new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);
        String BASE_URL = "https://github.com/";
        rvContributors = (RecyclerView) findViewById(R.id.contributors_view);

        contributorsItems = new ArrayList<>();
        contributorsItems.add(new contributorsItem("narendra", BASE_URL + "narendra36.png", BASE_URL + "narendra36"));
        contributorsItems.add(new contributorsItem("sukhbir", BASE_URL + "sukhbir-singh.png",BASE_URL + "sukhbir-singh"));
        contributorsItems.add(new contributorsItem("ramola", BASE_URL + "RamolaWeb.png",BASE_URL + "RamolaWeb"));
        contributorsItems.add(new contributorsItem("jalaz", BASE_URL + "jaykay12.png",BASE_URL + "jaykay12"));
        contributorsItems.add(new contributorsItem("Ratan ", BASE_URL + "sukhbir-singh.png", BASE_URL + "sukhbir-singh"));
        contributorsItems.add(new contributorsItem("Chankya ", BASE_URL + "sukhbir-singh.png", BASE_URL + "sukhbir-singh"));


        ContributorAdapter = new contributorsAdapter(contributorsItems, contributors.this);
        rvContributors.setAdapter(ContributorAdapter);

        tbContributers = (Toolbar) findViewById(R.id.contributors_toolbar);
        tbContributers.setTitle("Contributors");
        setSupportActionBar(tbContributers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager lvmanager = new LinearLayoutManager(this);
        lvmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContributors.setLayoutManager(lvmanager);

    }
}
