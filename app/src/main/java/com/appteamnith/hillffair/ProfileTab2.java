package com.appteamnith.hillffair;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Aditya on 9/13/2016.
 */
public class ProfileTab2 extends Fragment implements View.OnClickListener {

    EditText et1,et2,et3,et4,et5;
    ImageButton edit_btn;
    int i=1;
    int i1 = android.R.drawable.ic_menu_edit;
    int i2 = android.R.drawable.ic_menu_save;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_tab2_fragment, container, false);

        edit_btn = (ImageButton) view.findViewById(R.id.edit_btn);
        et1 = (EditText) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);
        et3 = (EditText) view.findViewById(R.id.et3);
        et4 = (EditText) view.findViewById(R.id.et4);
        et5 = (EditText) view.findViewById(R.id.et5);


    edit_btn.setOnClickListener(this);


    return view;


    }



    @Override
    public void onClick(View v) {

        if (i % 2 == 0) {
            edit_btn.setImageDrawable(getResources().getDrawable(i2));

            et1.setCursorVisible(true);
            et1.setFocusable(true);
            et1.setFocusableInTouchMode(true);

            et2.setCursorVisible(true);
            et2.setFocusable(true);
            et2.setFocusableInTouchMode(true);

            et3.setCursorVisible(true);
            et3.setFocusable(true);
            et3.setFocusableInTouchMode(true);

            et4.setCursorVisible(true);
            et4.setFocusable(true);
            et4.setFocusableInTouchMode(true);

            et5.setCursorVisible(true);
            et5.setFocusable(true);
            et5.setFocusableInTouchMode(true);
        }
        else {

            edit_btn.setImageDrawable(getResources().getDrawable(i1));

            et1.setCursorVisible(false);
            et1.setFocusable(false);
            et1.setFocusableInTouchMode(false);

            et2.setCursorVisible(false);
            et2.setFocusable(false);
            et2.setFocusableInTouchMode(false);

            et3.setCursorVisible(false);
            et3.setFocusable(false);
            et3.setFocusableInTouchMode(false);

            et4.setCursorVisible(false);
            et4.setFocusable(false);
            et4.setFocusableInTouchMode(false);

            et5.setCursorVisible(false);
            et5.setFocusable(false);
            et5.setFocusableInTouchMode(false);

        }
        i++;
    }
}



