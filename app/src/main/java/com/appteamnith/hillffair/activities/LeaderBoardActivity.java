package com.appteamnith.hillffair.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.adapters.LeaderBoardAdapter;
import com.appteamnith.hillffair.models.LeaderBoardModel;
import com.appteamnith.hillffair.utilities.APIINTERFACE;
import com.appteamnith.hillffair.utilities.Utils;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;;
    ArrayList<LeaderBoardUserModel>users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        recyclerView =(RecyclerView)findViewById(R.id.leader_recycler);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getLeaderBoard("1");




    }

    public void getLeaderBoard(String from){
        APIINTERFACE mAPI = Utils.getRetrofitService();
        Call<LeaderBoardModel> mService= mAPI.getLeaderBoard(from);
        mService.enqueue(new Callback<LeaderBoardModel>() {
            @Override
            public void onResponse(Call<LeaderBoardModel> call, Response<LeaderBoardModel> response) {
               users=response.body().getUsers();
                adapter = new LeaderBoardAdapter(users,getApplicationContext());
                recyclerView.setAdapter(adapter);
                Log.e("Inside onresponse ()","-->"+users.get(0).getName());
            }

            @Override
            public void onFailure(Call<LeaderBoardModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();

            }
        });


    }




    public class LeaderBoardUserModel{

        @SerializedName("name")
        private String name;
        @SerializedName("score")
        private int score;
        @SerializedName("rollno")
        private String rollNo;
        @SerializedName("sets")
        private int sets;

        public LeaderBoardUserModel(String name, int score, String rollNo, int sets) {
            this.name = name;
            this.score = score;
            this.rollNo = rollNo;
            this.sets = sets;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getRollNo() {
            return rollNo;
        }

        public void setRollNo(String rollNo) {
            this.rollNo = rollNo;
        }

        public int getSets() {
            return sets;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }
    }


}
