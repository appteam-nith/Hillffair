package com.appteamnith.hillffair.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.fragments.QuizFragment;
import com.appteamnith.hillffair.models.QuizQuestionsModel;
import com.appteamnith.hillffair.models.SingleQuestionModel;
import com.appteamnith.hillffair.utilities.APIINTERFACE;
import com.appteamnith.hillffair.utilities.ScoreCalculator;
import com.appteamnith.hillffair.utilities.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizQuestionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager pager;
    private ProgressBar progressBar;
    private Button back2home;
    private LinearLayout staytuned_message;
    private TextView message;

    private SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager=(ViewPager)findViewById(R.id.question_pager);
        back2home=(Button)findViewById(R.id.home_link);
        staytuned_message=(LinearLayout)findViewById(R.id.stay_tuned_message);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        message=(TextView)findViewById(R.id.message);

        back2home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pref=new SharedPref(this);
        String userId=pref.getUserId();

        loadQuizwithRetrofit(userId);

    }

    public void loadQuizwithRetrofit(String userId){

        APIINTERFACE apiservice= Utils.getRetrofitService();
        Call<QuizQuestionsModel> call=apiservice.getQuiz(userId);

        call.enqueue(new Callback<QuizQuestionsModel>() {
            @Override
            public void onResponse(Call<QuizQuestionsModel> call, Response<QuizQuestionsModel> response) {
                progressBar.setVisibility(View.GONE);

                QuizQuestionsModel model=response.body();
                int status=response.code();

                if(model!=null && response.isSuccess()){
                    if(model.isSuccess()){
                        // set viewpager

                        final List<SingleQuestionModel> questions=model.getQuestions();

                        pager.setVisibility(View.VISIBLE);

                        if(questions!=null){
                            //initialize scoreCalculator
                            ScoreCalculator sc=ScoreCalculator.getInstance();

                            String answers[]=new String[questions.size()];
                            String selectedChoices[]=new String[questions.size()];
                            int question_type[]=new int[questions.size()];

                            for(int i1=0;i1<questions.size();i1++){
                                SingleQuestionModel ques=questions.get(i1);

                                answers[i1]=ques.getAnswer();
                                selectedChoices[i1]="";

                                if(ques.isSingleChoice()){
                                    question_type[i1]=1;
                                }else{
                                    question_type[i1]=2;
                                }

                            }

                            sc.setAnswers(answers);
                            sc.setSelectedChoices(selectedChoices);
                            sc.setQuestion_type(question_type);

                            FragmentManager fragmentManager=getSupportFragmentManager();
                            pager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

                                @Override
                                public Fragment getItem(int position) {
                                    Bundle b=new Bundle();
                                    b.putInt("num",position+1);
                                    b.putSerializable("ques",questions.get(position));

                                    if(position==questions.size()-1){
                                        b.putBoolean("finish",true);
                                    }else{
                                        b.putBoolean("finish",false);
                                    }

                                    QuizFragment fragment=new QuizFragment();
                                    fragment.setArguments(b);

                                    return fragment;
                                }

                                @Override
                                public int getCount() {
                                    return questions.size();
                                }
                            });

                        }else{
                            staytuned_message.setVisibility(View.VISIBLE);
                        }

                    }else{
                        staytuned_message.setVisibility(View.VISIBLE);
                        message.setText("Some error occurred !! \nPlease try again later..");

                        if (status == 503) {
                            Toast.makeText(QuizQuestionActivity.this, "Server Down", Toast.LENGTH_SHORT).show();
                        }

                    }

                    Toast.makeText(QuizQuestionActivity.this, ""+model.getMsg(), Toast.LENGTH_SHORT).show();

                }else{
                    staytuned_message.setVisibility(View.VISIBLE);
                    message.setText("Some error occurred !! \nPlease try again later..");

                    if (status == 503) {
                        Toast.makeText(QuizQuestionActivity.this, "Server Down", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<QuizQuestionsModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                staytuned_message.setVisibility(View.VISIBLE);
                message.setText("Some error occurred !! \nPlease try again later..");

            }
        });

    }

}
