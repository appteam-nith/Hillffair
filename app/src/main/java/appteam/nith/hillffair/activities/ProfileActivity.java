package appteam.nith.hillffair.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.PagerAdapter;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.fragments.ProfileTab1;
import appteam.nith.hillffair.fragments.ProfileTab2;
import appteam.nith.hillffair.fragments.ProfileTab3;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Aditya on 10/3/2016.
 */


public class ProfileActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    private SharedPref sharedPref ;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPref=new SharedPref(this);

        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        imageView= (ImageView) findViewById(R.id.image_View);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(sharedPref.getUserName());
        toolbar.setTitle(sharedPref.getUserName());


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



    }


    public void openGallery(){

        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Glide.with(this).load(uri).into(imageView);
        }
    }


}
