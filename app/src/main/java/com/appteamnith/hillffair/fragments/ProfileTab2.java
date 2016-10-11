package com.appteamnith.hillffair.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.models.ProfileDataModel;
import com.appteamnith.hillffair.utilities.APIINTERFACE;
import com.appteamnith.hillffair.utilities.Utils;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aditya on 9/13/2016.
 */
public class ProfileTab2 extends Fragment {

    EditText et1,et2,et4,et5;
    private ProgressBar progress;
    SharedPref sharedPref;
    private ScrollView scroll_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_tab2_fragment, container, false);
        sharedPref=new SharedPref(getContext());
        et1 = (EditText) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);
        et4 = (EditText) view.findViewById(R.id.et4);
        et5 = (EditText) view.findViewById(R.id.et5);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        scroll_layout = (ScrollView) view.findViewById(R.id.scroll_layout);

        progress.setVisibility(view.VISIBLE);

        profileBasicInfo(sharedPref.getUserId());
        Log.d("data",""+sharedPref.getUserId());

        return view;

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

    }

    private void profileBasicInfo(String id){

        APIINTERFACE mAPI = Utils.getRetrofitService();
        Call<ProfileDataModel> mService = mAPI.profileBasicInfo(id);
        mService.enqueue(new Callback<ProfileDataModel>() {
            @Override
            public void onResponse(Call<ProfileDataModel> call, Response<ProfileDataModel> response) {
                ProfileBasicDetailModel model = response.body().getProfileInfo();

                sharedPref.setUserName(model.getName());
                int status_code = response.code();
                boolean returnedResponse = response.body().isSuccess();
                progress.setVisibility(View.GONE);
                scroll_layout.setVisibility(View.VISIBLE);
                if(returnedResponse){
                    Log.d("as","come");
                    Log.d("abc",""+model.getName());
                    Log.d("abc",""+model.getRollno());
                    Log.d("abc",""+model.getEmail());
                    Log.d("abc",""+model.getPhone());
                    if(model.getRollno().isEmpty()){
                        et2.setVisibility(View.GONE);
                    }
                    et1.setText(model.getName());
                    et2.setText(model.getRollno());
                    et4.setText(model.getEmail());
                    et5.setText(model.getPhone());

                }
                else {
                    if(status_code==503){
                        if(getActivity()!=null)
                        Toast.makeText(getActivity(), "Server Down", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ProfileDataModel> call, Throwable t) {
                progress.setVisibility(View.GONE);
                t.printStackTrace();
                if(getActivity()!=null)
                Toast.makeText(getActivity(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });


    }

}



