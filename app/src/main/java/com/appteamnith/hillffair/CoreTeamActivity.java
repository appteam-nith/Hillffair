package com.appteamnith.hillffair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class CoreTeamActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    CoreTeamAdapter core_team_adapter;
    ArrayList<CoreTeamItem> array_list;
    Toolbar core_team_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coreteam);

        recycler_view=(RecyclerView)findViewById(R.id.core_team_list);
        array_list=new ArrayList<>();
        array_list.add(new CoreTeamItem("Rishabh Bhandari","Clubs Secretary","url"));
        array_list.add(new CoreTeamItem("Deepak Kumar Jain","Hillffair Secretary","url"));
        array_list.add(new CoreTeamItem("Sourabh Thakur","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Mukul Chandel","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Avantika Sharma","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Bhanu Pratap Singh","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Dhananjay Kumar Pal","Convener","url"));
        array_list.add(new CoreTeamItem("Medha Agrawal","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Aprajit Pandit","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Aruni Juyal","Convener","url"));
        array_list.add(new CoreTeamItem("Nishant Rana","Convener","url"));
        array_list.add(new CoreTeamItem("Aanchal Negi","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Aditya Verma","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Shikhar Shrivastava","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Abhilasha","Convener","url"));
        array_list.add(new CoreTeamItem("Naveen Banyal","Jt. Secretary","url"));
        array_list.add(new CoreTeamItem("Rishabh Jain","Convener","url"));
        array_list.add(new CoreTeamItem("Varun B.","Convener","url"));
        array_list.add(new CoreTeamItem("Nishant Dogra","Convener","url"));
        array_list.add(new CoreTeamItem("Amit Gupta","Convener","url"));
        array_list.add(new CoreTeamItem("Tanya Rani","Convener","url"));
        array_list.add(new CoreTeamItem("Mudit Banyal","Convener","url"));
        array_list.add(new CoreTeamItem("Kunal Sharma","Convener","url"));
        array_list.add(new CoreTeamItem("Ashima Anand","Convener","url"));
        array_list.add(new CoreTeamItem("Praveen Kumar","Convener","url"));
        array_list.add(new CoreTeamItem("Shubham Dhiman","Convener","url"));
        array_list.add(new CoreTeamItem("Puneet Kumar","Secretary(Sports)","url"));
        array_list.add(new CoreTeamItem("Priya Vashishth","Chief Editor(Srijan)","url"));
        core_team_adapter=new CoreTeamAdapter(array_list,CoreTeamActivity.this);
        recycler_view.setAdapter(core_team_adapter);
        LinearLayoutManager liner_layout_manager=new LinearLayoutManager(this);
        liner_layout_manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(liner_layout_manager);

        core_team_toolbar=(Toolbar)findViewById(R.id.core_team_toolbar);
        core_team_toolbar.setTitle("Core Team");
        setSupportActionBar(core_team_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}