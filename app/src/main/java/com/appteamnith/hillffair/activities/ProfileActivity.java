package com.appteamnith.hillffair.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.SharedPref;
import com.appteamnith.hillffair.adapters.PagerAdapter;
import com.appteamnith.hillffair.fragments.ProfileTab1;
import com.appteamnith.hillffair.fragments.ProfileTab2;
import com.appteamnith.hillffair.fragments.ProfileTab3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.bitmap;

public class ProfileActivity extends AppCompatActivity{

    private int PICK_IMAGE_REQUEST = 1;
    Uri uri;
    private static final int CAMERA_REQUEST_CODE=1;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


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

        //End of Tab Layout in Profile Activity



        profile_name = (TextView) findViewById(R.id.profile_name);
        open_gallery = (ImageView) findViewById(R.id.open_gallery);
        profile_pic = (ImageButton) findViewById(R.id.profile_pic);




    }


    public void openGallery(View v){
        new AlertDialog.Builder(this)
                .setTitle("Complete using...")
                .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "profile_pic.jpg");
                        uri = Uri.fromFile(imageFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, CAMERA_REQUEST_CODE);
                    }
                })
                .setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"), PICK_IMAGE_REQUEST);    }
                })
                .show();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK && data != null && data.getData() != null) {
if(requestCode==PICK_IMAGE_REQUEST){
            Uri uri2 = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri2);
                ImageView profile_pic = (ImageView) findViewById(R.id.profile_pic);
                RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
                roundedBitmapDrawable.setCornerRadius(2.0f);
                roundedBitmapDrawable.setCircular(true);
                profile_pic.setImageDrawable(roundedBitmapDrawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            else if(requestCode==CAMERA_REQUEST_CODE){

    try {
      Bitmap  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
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

    }


