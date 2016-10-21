package appteam.nith.hillffair.application;
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

    public SharedPref(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setInstructionsReadStatus(boolean b){
        editor.putBoolean("quizinstruct",b);
        editor.commit();
    }

    public boolean getInstructionsReadStatus(){
        return prefs.getBoolean("quizinstruct",false);
    }

    public int getThemeId() {
        int id=prefs.getInt("themeid", 0);
        return id;
    }

    public boolean launchThemeSelection() {
        return prefs.getBoolean("themeselected", false);
    }//if true launch ==> its first time

    public boolean launchStartUpShow(){
        return prefs.getBoolean("startupshow", true);
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

    public String getUserId() {
        return prefs.getString("userid",null);
    }

    public void setUserId(String userId) {
        editor.putString("userid", userId);
        editor.commit();
    }

    public String getUserName(){
        return prefs.getString("name",null);
    }

    public void setUserName(String userName){
        editor.putString("name", userName);
        editor.commit();
    }

    public String getRollNo(){
        return prefs.getString("rollno",null);
    }

    public void setRollNo(String RollNO){
        editor.putString("rollno", RollNO);
        editor.commit();
    }

}
