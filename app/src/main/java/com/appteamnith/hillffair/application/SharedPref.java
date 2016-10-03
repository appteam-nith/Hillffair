package com.appteamnith.hillffair.application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Akatsuki on 10/1/2016.
 */
public class SharedPref {

    private Context context;
    private final String filename = "prefsFile";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

   // public static boolean launchStartUpShow;
    //public static int themeId;
    //public static boolean launchThemeSelection;

    public SharedPref(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public int getThemeId() {
        int id=prefs.getInt("themeid", 0);
      //  if(id==0)id= R.style.superman;
        return id;
    }

    public boolean launchThemeSelection() {
        return prefs.getBoolean("themeselected", false);
    }//if true launch ==> its first time

    public boolean launchStartUpShow(){
        return prefs.getBoolean("startupshow", false);
    }//if true ==>its first time


    public void setThemeId(int id) {
       // if(id==0)id=R.style.superman;
        editor.putInt("themeid", id);
        editor.apply();
    }

    public void setThemeSelection(boolean val) {
        editor.putBoolean("themeselected", val);
        editor.commit();
    }//set true if its first time for next time set it false

    public void setStartUpShow(boolean val){
        editor.putBoolean("startupshow", val);
        editor.commit();
    }//set true if its first time for next time set it false

}
