package com.appteamnith.hillffair.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.adapters.PagerAdapter;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.fragments.ProfileTab1;
import com.appteamnith.hillffair.fragments.ProfileTab2;
import com.appteamnith.hillffair.fragments.ProfileTab3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aditya on 10/3/2016.
 */


public class ProfileActivity extends AppCompatActivity{

    private int PICK_IMAGE_REQUEST = 1;
    ImageView open_gallery;
    ImageButton profile_pic;
    TextView profile_name;

    SharedPref sharedPref ;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPref=new SharedPref(this);

        // List of All The Fragment for the Profile Tabs and Their Title

        ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
        fragmentArrayList.add(new ProfileTab1());
        fragmentArrayList.add(new ProfileTab2());
        fragmentArrayList.add(new ProfileTab3());


        ArrayList<String> titleArrayList=new ArrayList<>();
        titleArrayList.add("ScoreBoard");
        titleArrayList.add("Basic info");
        titleArrayList.add("News Feed");

        //Start of Tab Layout in Profile Activity

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),fragmentArrayList,titleArrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).select();
        viewPager.setOffscreenPageLimit(3);

        //End of Tab Layout in Profile Activity

        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_name.setText(sharedPref.getUserName());
        open_gallery = (ImageView) findViewById(R.id.open_gallery);
        profile_pic = (ImageButton) findViewById(R.id.profile_pic);

    }


    public void openGallery(View v){

        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView profile_pic = (ImageView) findViewById(R.id.profile_pic);
                RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
                roundedBitmapDrawable.setCornerRadius(2.0f);
                roundedBitmapDrawable.setCircular(true);
                profile_pic.setImageDrawable(roundedBitmapDrawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
