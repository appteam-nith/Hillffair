package com.appteamnith.hillffair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ClubEvent> list = new ArrayList<ClubEvent>();
    int image_id[] =
            {R.drawable.deidara_logo, R.drawable.hidan_logo, R.drawable.itachi_logo, R.drawable.kakuzu_logo, R.drawable.kisame_logo,
                    R.drawable.konan_logo, R.drawable.pain_logo, R.drawable.sasori_logo, R.drawable.tobi_logo, R.drawable.zetsu_logo};
    String[] name = { "Diedara", "Hidan", "Itachi", "Kakuzu", "Kisame",  "Konan",  "Pain",   "Sasori","Tobi", "Zetsu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        int count = 0;
        for (String str : name) {
            ClubEvent clubEvent = new ClubEvent(image_id[count], name[count]);
            count++;
            list.add(clubEvent);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ClubEventAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}
