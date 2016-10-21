package appteam.nith.hillffair.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class StartupShow {
    SharedPreferences pref;
    SharedPreferences.Editor edits;
    Context c;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "hillffair-welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    public StartupShow(Context con){
        this.c=con;
        pref=c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        edits=pref.edit();
}
    public void setFirstTimeLaunch(boolean firsts){
        edits.putBoolean(IS_FIRST_TIME_LAUNCH,firsts);
        edits.commit();
    }

    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
}