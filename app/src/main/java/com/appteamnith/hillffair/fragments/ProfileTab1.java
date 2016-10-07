package com.appteamnith.hillffair.fragments;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.models.UserScoreResponse;
import com.appteamnith.hillffair.utilities.Utils;
import com.google.gson.annotations.SerializedName;

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
    private TextView score,questionSolved,noOfQuestionSolved,noScore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.profile_tab1_fragment,container,false);
        data_layout= (LinearLayout) v.findViewById(R.id.data_layout);
        progressBar= (ProgressBar) v.findViewById(R.id.progress);
        score= (TextView) v.findViewById(R.id.view_score);
        //questionSolved= (TextView) v.findViewById(R.id.view_number_question_solved);
        noOfQuestionSolved= (TextView) v.findViewById(R.id.view_number_question_solved);
        noScore= (TextView) v.findViewById(R.id.no_data_textview);
        if(savedInstanceState==null){
            progressBar.setVisibility(View.VISIBLE);
            getData(new SharedPref(getActivity()).getUserId());
        }
        else {
            if((savedInstanceState.getString(USER_SCORE)!=null&&!savedInstanceState.getString(USER_SCORE).isEmpty())||(savedInstanceState.getString(USER_NO_QUESTION)!=null&&!savedInstanceState.getString(USER_NO_QUESTION).isEmpty())){
                data_layout.setVisibility(View.VISIBLE);
                score.setText(savedInstanceState.getString(USER_SCORE));
                noOfQuestionSolved.setText(savedInstanceState.getString(USER_NO_QUESTION));
            }
            else {
                noScore.setVisibility(View.VISIBLE);
                noScore.setText("No Data Available");
            }
        }


        return v;
    }


    public  class QuizResponse implements Parcelable{
        @SerializedName("sets")
        private int qSolved;

        @SerializedName("points")
        private int points;

        public QuizResponse(int qSolved, int points) {
            this.qSolved = qSolved;
            this.points = points;
        }

        protected QuizResponse(Parcel in) {
            qSolved = in.readInt();
            points = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(qSolved);
            dest.writeInt(points);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public  final Creator<QuizResponse> CREATOR = new Creator<QuizResponse>() {
            @Override
            public QuizResponse createFromParcel(Parcel in) {
                return new QuizResponse(in);
            }

            @Override
            public QuizResponse[] newArray(int size) {
                return new QuizResponse[size];
            }
        };

        public int getqSolved() {
            return qSolved;
        }

        public void setqSolved(int qSolved) {
            this.qSolved = qSolved;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }

    private void getData(String id){
        Call<UserScoreResponse> getUserScoreResponseCall= Utils.getRetrofitService().getUserScore(id);
        getUserScoreResponseCall.enqueue(new Callback<UserScoreResponse>() {
            @Override
            public void onResponse(Call<UserScoreResponse> call, Response<UserScoreResponse> response) {
                UserScoreResponse data=response.body();
                if(data!=null&&response.isSuccess()){
                    QuizResponse score=data.getQuizResponse();
                    if(score!=null){
                        int s=score.getPoints();
                        int q=score.getqSolved();
                        if(s!=0&&q!=0){
                            ProfileTab1.this.data_layout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            ProfileTab1.this.score.setText("Score of Quiz is "+s);
                            ProfileTab1.this.noOfQuestionSolved.setText("Number of Question Solved "+q);
                        }
                        else {
                            noScore.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            ProfileTab1.this.noScore.setText("No Data Avialable");
                        }
                    }
                    else {
                        noScore.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        ProfileTab1.this.noScore.setText("No Data Avialable");
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
        outState.putString(USER_NO_QUESTION,noOfQuestionSolved.getText().toString());
    }
}
