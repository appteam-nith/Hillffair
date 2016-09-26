package com.appteamnith.hillffair;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity{

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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        //Start of Tab Layout in Profile Activity

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //End of Tab Layout in Profile Activity



        profile_name = (TextView) findViewById(R.id.profile_name);
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
