package com.appteamnith.hillffair;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private int PICK_IMAGE_REQUEST = 1;
    ImageView open_gallery;
    ImageButton profile_pic;
    ImageButton edit_btn;
    TextView profile_name;
    EditText et1,et2,et3,et4,et5;
    int i=1;
    int i1 = android.R.drawable.ic_menu_edit;
    int i2 = android.R.drawable.ic_menu_save;


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onTabSelected(TabLayout.Tab tab){
        viewPager.setCurrentItem(tab.getPosition());
    }
    public void onTabUnselected(TabLayout.Tab tab){}
    public void onTabReselected(TabLayout.Tab tab){}




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        //Start of Tab Layout in Profile Activity

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Posts"));
        tabLayout.addTab(tabLayout.newTab().setText("Basic Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Scorecard"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener(this);

        //End of Tab Layout in Profile Activity



        profile_name = (TextView) findViewById(R.id.profile_name);
        open_gallery = (ImageView) findViewById(R.id.open_gallery);
        profile_pic = (ImageButton) findViewById(R.id.profile_pic);




    }


    public void openGallery(View v){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                ImageView profile_pic = (ImageView) findViewById(R.id.profile_pic);
                profile_pic.setScaleType(ImageView.ScaleType.CENTER_CROP);
                profile_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
