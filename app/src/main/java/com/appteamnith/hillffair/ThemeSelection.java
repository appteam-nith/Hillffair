package com.appteamnith.hillffair;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ThemeSelection extends AppCompatActivity implements View.OnClickListener{
    // TODO: 9/13/2016 replace the icons with the comic characters
    // TODO: 9/13/2016 connect activity to prev and next activities
    
    final static String fileTheme="comic_theme";//in this file theme of the app will be stored
    final static String fileThemeSel ="theme_sel";//this prefs is to check whether the act was opened earlier
    String themeVal="comic1";
    ImageButton comic1,comic2,comic3,comic4,comic5,comic6,comic7,comic8,comic9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_selection);
        if(isThemeAlreadySelected())openNextActivity();
        init();
    }

    void init(){
        comic1=(ImageButton)findViewById(R.id.comic1);
        comic2=(ImageButton)findViewById(R.id.comic2);
        comic3=(ImageButton)findViewById(R.id.comic3);
        comic4=(ImageButton)findViewById(R.id.comic4);
        comic5=(ImageButton)findViewById(R.id.comic5);
        comic6=(ImageButton)findViewById(R.id.comic6);
        comic7=(ImageButton)findViewById(R.id.comic7);
        comic8=(ImageButton)findViewById(R.id.comic8);
        comic9=(ImageButton)findViewById(R.id.comic9);
        comic1.setOnClickListener(this);
        comic2.setOnClickListener(this);
        comic3.setOnClickListener(this);
        comic4.setOnClickListener(this);
        comic5.setOnClickListener(this);
        comic6.setOnClickListener(this);
        comic7.setOnClickListener(this);
        comic8.setOnClickListener(this);
        comic9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){

        case R.id.comic1:themeVal="comic1";
            break;
        case R.id.comic2:themeVal="comic2";
            break;
        case R.id.comic3:themeVal="comic3";
            break;
        case R.id.comic4:themeVal="comic4";
            break;
        case R.id.comic5:themeVal="comic5";
            break;
        case R.id.comic6:themeVal="comic6";
            break;
        case R.id.comic7:themeVal="comic7";
            break;
        case R.id.comic8:themeVal="comic8";
            break;
        case R.id.comic9:themeVal="comic9";
            break;
    }
       applyTheme();
       dontOpenAgain();
        openNextActivity();
    }

    void openNextActivity(){
        finish();
       // Intent intent = new Intent(ThemeSelection.this,ACT_NEXT.class);
        //startActivity(intent);
    }

    boolean isThemeAlreadySelected(){
        String fileThemeSel ="theme_sel";
        SharedPreferences prefsFlag =getSharedPreferences(fileThemeSel,MODE_PRIVATE);
         return !prefsFlag.getBoolean("isFirstTime",true);//is first time ==>theme is yet to be selected
    }
    void applyTheme(){
        SharedPreferences prefsTheme = getSharedPreferences(fileTheme,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefsTheme.edit();
        editor.putString("Theme",themeVal);
        editor.commit();
          }
    void dontOpenAgain(){
        SharedPreferences prefsFlag=getSharedPreferences(fileThemeSel,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefsFlag.edit();
        editor.putBoolean("isFirstTime", false);//dont open it next time
        editor.commit();}
}
