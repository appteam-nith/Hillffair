package com.appteamnith.hillffair;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Aditya on 9/13/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public PagerAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                ProfileTab1 tab1 = new ProfileTab1();
                return  tab1;
            case 1:
                ProfileTab2 tab2 = new ProfileTab2();
                return tab2;
            case 2:
                ProfileTab3 tab3 = new ProfileTab3();
                return tab3;
            default:
                return null;
        }

    }

    @Override
    public int getCount(){
        return tabCount;
    }

}
