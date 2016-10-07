package com.appteamnith.hillffair.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.activities.QuizScoreActivity;

/**
 * Created by sukhbir on 8/10/16.
 */

public class QuizFragment extends Fragment {

    private int qnumber=1;
    private Button finish;
    private TextView next_text,question_number_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.single_question, container, false);
        qnumber=getArguments().getInt("num",1);

        finish=(Button) view.findViewById(R.id.finish_button);
        next_text=(TextView) view.findViewById(R.id.next_instruction);
        question_number_text=(TextView) view.findViewById(R.id.question_number_text);

        question_number_text.setText("Question "+qnumber+".");

        if(qnumber==10){
            next_text.setVisibility(View.GONE);
            finish.setVisibility(View.VISIBLE);

            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                    startActivity(new Intent(getActivity(),QuizScoreActivity.class));
                }
            });

        }else{
            next_text.setVisibility(View.VISIBLE);
            finish.setVisibility(View.GONE);
        }

        return view;

    }

}
