package appteam.nith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.BattleDayAdapter;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.BattleDayItem;
import appteam.nith.hillffair.models.BattleDayModel;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.RecyclerItemClickListener;
import appteam.nith.hillffair.utilities.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BattleDayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar bar;
    private ArrayList<BattleDayItem> list=new ArrayList<>();
    private BattleDayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(BattleDayActivity.this);
        recyclerView.setLayoutManager(manager);
        adapter=new BattleDayAdapter(BattleDayActivity.this);
        recyclerView.setAdapter(adapter);
        bar=(ProgressBar)findViewById(R.id.progress);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i=new Intent(BattleDayActivity.this,ClubActivity.class);
                i.putExtra("name",list.get(position).getName());
                i.putExtra("id",list.get(position).getId());
                i.putExtra("battleday",true);

                startActivity(i);
            }
        }));

        bar.setVisibility(View.VISIBLE);
        retrofit();

    }

    public void retrofit(){

        APIINTERFACE apiservice= Utils.getRetrofitService();
        Call<BattleDayModel> call=apiservice.getSpecialEvents();

        call.enqueue(new Callback<BattleDayModel>() {
            @Override
            public void onResponse(Call<BattleDayModel> call, Response<BattleDayModel> response) {
                bar.setVisibility(View.GONE);

                BattleDayModel model=response.body();
                int status=response.code();

                if(model!=null && response.isSuccess()){
                   recyclerView.setVisibility(View.VISIBLE);

                    list=model.getEvents();
                    adapter.refresh(list);

                }else{
                    Toast.makeText(BattleDayActivity.this,"Some error occurred!!",Toast.LENGTH_SHORT).show();
                }

              }

            @Override
            public void onFailure(Call<BattleDayModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Toast.makeText(BattleDayActivity.this,"Some error occurred!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
