package com.appteamnith.hillffair.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.*;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.fragments.QuizFragment;

public class QuizQuestionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager=(ViewPager)findViewById(R.id.question_pager);

        FragmentManager fragmentManager=getSupportFragmentManager();
        pager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Bundle b=new Bundle();
                b.putInt("num",position+1);

                QuizFragment fragment=new QuizFragment();
                fragment.setArguments(b);

                return fragment;
            }

            @Override
            public int getCount() {
                return 10;
            }
        });

    }
}
