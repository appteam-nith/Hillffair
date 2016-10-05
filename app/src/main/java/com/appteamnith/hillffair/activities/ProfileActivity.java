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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.adapters.PagerAdapter;
import com.appteamnith.hillffair.fragments.ProfileTab1;
import com.appteamnith.hillffair.fragments.ProfileTab2;
import com.appteamnith.hillffair.fragments.ProfileTab3;
import com.appteamnith.hillffair.utilities.APIINTERFACE;
import com.appteamnith.hillffair.utilities.Utils;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aditya on 10/3/2016.
 */


public class ProfileActivity extends AppCompatActivity{

    private int PICK_IMAGE_REQUEST = 1;
    ImageView open_gallery;
    ImageButton profile_pic;
    ImageButton edit_btn;
    TextView profile_name;
    EditText et1,et2,et3,et4,et5;
    ProgressBar progress;
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
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);

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



    public class ProfileBasicDetailModel{

        @SerializedName("_id")
        private String _id;

        @SerializedName("name")
        private String name;

        @SerializedName("email")
        private String email;

        @SerializedName("pwd")
        private String pwd;

        @SerializedName("nitian")
        private boolean nitian;

        @SerializedName("photo")
        private String photo = null;

        @SerializedName("rollno")
        private String rollno;

        @SerializedName("phone")
        private String phone;

        @SerializedName("date")
        private String date;



        public ProfileBasicDetailModel(String _id, String name, String email, String pwd, boolean nitian, String photo, String rollno, String phone, String date) {
            this._id = _id;
            this.name = name;
            this.email = email;
            this.pwd = pwd;
            this.nitian = nitian;
            this.photo = photo;
            this.rollno = rollno;
            this.phone = phone;
            this.date = date;
        }


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public boolean isNitian() {
            return nitian;
        }

        public void setNitian(boolean nitian) {
            this.nitian = nitian;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRollno() {
            return rollno;
        }

        public void setRollno(String rollno) {
            this.rollno = rollno;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }


        @SerializedName("success")
        public boolean success;

        @SerializedName("error")
        public  String error;

        public ProfileBasicDetailModel(boolean success, String error) {
            this.success = success;
            this.error = error;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }


    }

    private void profileBasicInfo(String id){

        APIINTERFACE mAPI = Utils.getRetrofitService();
        Call<ProfileBasicDetailModel> mService = mAPI.profileBasicInfo(id);
        mService.enqueue(new Callback<ProfileBasicDetailModel>() {
            @Override
            public void onResponse(Call<ProfileBasicDetailModel> call, Response<ProfileBasicDetailModel> response) {
                ProfileBasicDetailModel model = response.body();
                int status_code = response.code();
                boolean returnedResponse = model.success;
                progress.setVisibility(View.GONE);
                if(returnedResponse){
                    et1.setText(model.getName());
                    et2.setText(model.getRollno());
                    et4.setText(model.getEmail());
                    et5.setText(model.getPhone());
                    profile_name.setText(model.getName());
                }
                else {
                    if(status_code==503){
                        Toast.makeText(ProfileActivity.this, "Server Down", Toast.LENGTH_SHORT).show();
                    }
                    String error = model.getError();
                    if (error != null && !error.isEmpty()) {
                        Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ProfileBasicDetailModel> call, Throwable t) {
                progress.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(ProfileActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });


    }

}
