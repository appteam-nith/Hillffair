package appteam.nith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.ClubEventAdapter;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.ClubEvent;
import appteam.nith.hillffair.utilities.RecyclerItemClickListener;
import appteam.nith.hillffair.utilities.Utils;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {
    public static final String CLUB_NAME ="CLUB_NAME" ;
    private RecyclerView recyclerView;
    private ClubEventAdapter adapter;
    private ProgressBar progressBar;
    private ArrayList<ClubEvent> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        adapter=new ClubEventAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i=new Intent(EventActivity.this,ClubActivity.class);
                i.putExtra(CLUB_NAME,list.get(position).getName());
                startActivity(i);
            }
        }));
        showData();
    }


    public  class ClubResponse{
        @SerializedName("clubs")
        private ArrayList<ClubEvent> list;

        @SerializedName("success")
        private boolean success;

        @SerializedName("error")
        private String error;

        public ClubResponse(ArrayList<ClubEvent> list, boolean success, String error) {
            this.list = list;
            this.success = success;
            this.error = error;
        }

        public ArrayList<ClubEvent> getList() {
            return list;
        }

        public void setList(ArrayList<ClubEvent> list) {
            this.list = list;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    private  void showData(){
        Call<ClubResponse> getClubResponseCall= Utils.getRetrofitService().getAllClub();
        getClubResponseCall.enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Call<ClubResponse> call, Response<ClubResponse> response) {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                ClubResponse clubResponse=response.body();
                if(clubResponse!=null&&response.isSuccess()){
                    if(clubResponse.isSuccess()){
                        list=clubResponse.getList();
                        adapter.refresh(list);
                    }
                }
                else {
                    Toast.makeText(EventActivity.this,"Error While Fetching Data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClubResponse> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
               t.printStackTrace();
                Toast.makeText(EventActivity.this,"Error While Fetching Data",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
