package appteam.nith.hillffair.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.UserScoreResponse;
import appteam.nith.hillffair.utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sahil on 9/13/2016.
 */
public class ProfileTab1 extends Fragment {

    private static final String USER_SCORE ="score" ;
    private static final String USER_NO_QUESTION ="noOfQuestion" ;
    private LinearLayout data_layout;
    private ProgressBar progressBar;
    private TextView score,noScore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.profile_tab1_fragment,container,false);
        data_layout= (LinearLayout) v.findViewById(R.id.data_layout);
        progressBar= (ProgressBar) v.findViewById(R.id.progress);
        score= (TextView) v.findViewById(R.id.view_score);
        noScore= (TextView) v.findViewById(R.id.no_data_textview);
        if(savedInstanceState==null){
            progressBar.setVisibility(View.VISIBLE);
            getData(new SharedPref(getActivity()).getUserId());
        }
        else {
            if((savedInstanceState.getString(USER_SCORE)!=null&&!savedInstanceState.getString(USER_SCORE).isEmpty())||(savedInstanceState.getString(USER_NO_QUESTION)!=null&&!savedInstanceState.getString(USER_NO_QUESTION).isEmpty())){
                data_layout.setVisibility(View.VISIBLE);
                score.setText(savedInstanceState.getString(USER_SCORE));
            }
            else {
                noScore.setVisibility(View.VISIBLE);
                noScore.setText("No Data Available");
            }
        }


        return v;
    }

    private void getData(String id){
        Call<UserScoreResponse> getUserScoreResponseCall= Utils.getRetrofitService().getUserScore(id);
        getUserScoreResponseCall.enqueue(new Callback<UserScoreResponse>() {
            @Override
            public void onResponse(Call<UserScoreResponse> call, Response<UserScoreResponse> response) {
                UserScoreResponse data=response.body();
                if(data!=null&&response.isSuccess()){
                    int score=data.getScore();
                        if(score>=0){
                            ProfileTab1.this.data_layout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            ProfileTab1.this.score.setText("Score of Quiz is "+score);
                        }
                        else {
                            noScore.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            ProfileTab1.this.noScore.setText("No Data Available");
                        }

                }
                else {
                    noScore.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    ProfileTab1.this.noScore.setText("Error While Getting Data");
                }
            }

            @Override
            public void onFailure(Call<UserScoreResponse> call, Throwable t) {
                noScore.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                ProfileTab1.this.noScore.setText("Error While Getting Data");
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USER_SCORE,score.getText().toString());
    }
}
