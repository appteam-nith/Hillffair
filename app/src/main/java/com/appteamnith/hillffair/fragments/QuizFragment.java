package com.appteamnith.hillffair.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.activities.QuizScoreActivity;
import com.appteamnith.hillffair.models.SingleQuestionModel;

/**
 * Created by sukhbir on 8/10/16.
 */

public class QuizFragment extends Fragment {

    private int qnumber=1;
    private boolean show_finish=false;
    private Button finish;
    private TextView next_text,question_number_text;

    private TextView question_text;
    private CheckBox checkBoxA,checkBoxB,checkBoxC,checkBoxD;
    private RadioButton radioA,radioB,radioC,radioD;
    private LinearLayout radio_questions,checkbox_questions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.single_question, container, false);
        qnumber=getArguments().getInt("num",1);
        show_finish=getArguments().getBoolean("finish");

        SingleQuestionModel ques= (SingleQuestionModel) getArguments().getSerializable("ques");

        question_text=(TextView)view.findViewById(R.id.question_text);
        checkBoxA=(CheckBox)view.findViewById(R.id.checkA);
        checkBoxB=(CheckBox)view.findViewById(R.id.checkB);
        checkBoxC=(CheckBox)view.findViewById(R.id.checkC);
        checkBoxD=(CheckBox)view.findViewById(R.id.checkD);

        radioA=(RadioButton)view.findViewById(R.id.radioA);
        radioB=(RadioButton)view.findViewById(R.id.radioB);
        radioC=(RadioButton)view.findViewById(R.id.radioC);
        radioD=(RadioButton)view.findViewById(R.id.radioD);

        radio_questions=(LinearLayout)view.findViewById(R.id.radio_questions);
        checkbox_questions=(LinearLayout) view.findViewById(R.id.checkbox_questions);

        finish=(Button) view.findViewById(R.id.finish_button);
        next_text=(TextView) view.findViewById(R.id.next_instruction);
        question_number_text=(TextView) view.findViewById(R.id.question_number_text);

        question_number_text.setText("Question "+qnumber+".");

        if(ques!=null){
            question_text.setText(ques.getQuestion());

            if(ques.isSingleChoice()){
                radio_questions.setVisibility(View.VISIBLE);
                checkbox_questions.setVisibility(View.GONE);

                radioA.setText(ques.getOptionsA());
                radioB.setText(ques.getOptionsB());
                radioC.setText(ques.getOptionsC());
                radioD.setText(ques.getOptionsD());

            }else{
                radio_questions.setVisibility(View.GONE);
                checkbox_questions.setVisibility(View.VISIBLE);

                checkBoxA.setText(ques.getOptionsA());
                checkBoxB.setText(ques.getOptionsB());
                checkBoxC.setText(ques.getOptionsC());
                checkBoxD.setText(ques.getOptionsD());
            }

            if(show_finish){
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

        }

        return view;

    }

}
