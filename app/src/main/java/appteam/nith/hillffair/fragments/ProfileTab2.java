package appteam.nith.hillffair.fragments;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.gson.annotations.SerializedName;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.ProfileDataModel;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aditya on 9/13/2016.
 */
public class ProfileTab2 extends Fragment {

    private static final String USER_NAME ="name" ;
    private static final String USER_EMAIL = "email";
    private static final String USER_ROLLNO ="rollno" ;
    private static final String USER_PHONE ="phone" ;
    EditText et1,et2,et4,et5;
    private ProgressBar progress;
    SharedPref sharedPref;
    private LinearLayout scroll_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_tab2_fragment, container, false);
        sharedPref=new SharedPref(getContext());
        et1 = (EditText) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);
        et4 = (EditText) view.findViewById(R.id.et4);
        et5 = (EditText) view.findViewById(R.id.et5);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        scroll_layout = (LinearLayout) view.findViewById(R.id.scroll_layout);

        if(savedInstanceState==null){
            progress.setVisibility(view.VISIBLE);
            profileBasicInfo(sharedPref.getUserId());
        }
        else{
            scroll_layout.setVisibility(View.VISIBLE);
            if(savedInstanceState.getString(USER_NAME)!=null&&!savedInstanceState.getString(USER_NAME).isEmpty()){
                et1.setText(savedInstanceState.getString(USER_NAME));
            }
            else {
                et1.setVisibility(View.GONE);
            }
            if(savedInstanceState.getString(USER_EMAIL)!=null&&!savedInstanceState.getString(USER_EMAIL).isEmpty()){
                et4.setText(savedInstanceState.getString(USER_EMAIL));
            }
            else {
                et4.setVisibility(View.GONE);
            }
            if(savedInstanceState.getString(USER_ROLLNO)!=null&&!savedInstanceState.getString(USER_ROLLNO).isEmpty()){
                et2.setText(savedInstanceState.getString(USER_ROLLNO));
            }
            else {
                et2.setVisibility(View.GONE);
            }
            if(savedInstanceState.getString(USER_PHONE)!=null&&!savedInstanceState.getString(USER_PHONE).isEmpty()){
                et5.setText(savedInstanceState.getString(USER_PHONE));

            }
            else {
                et5.setVisibility(View.GONE);
            }
        }


        return view;

    }


    public class ProfileBasicDetailModel implements Parcelable{
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


        protected ProfileBasicDetailModel(Parcel in) {
            _id = in.readString();
            name = in.readString();
            email = in.readString();
            pwd = in.readString();
            nitian = in.readByte() != 0;
            photo = in.readString();
            rollno = in.readString();
            phone = in.readString();
            date = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_id);
            dest.writeString(name);
            dest.writeString(email);
            dest.writeString(pwd);
            dest.writeByte((byte) (nitian ? 1 : 0));
            dest.writeString(photo);
            dest.writeString(rollno);
            dest.writeString(phone);
            dest.writeString(date);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public  final Creator<ProfileBasicDetailModel> CREATOR = new Creator<ProfileBasicDetailModel>() {
            @Override
            public ProfileBasicDetailModel createFromParcel(Parcel in) {
                return new ProfileBasicDetailModel(in);
            }

            @Override
            public ProfileBasicDetailModel[] newArray(int size) {
                return new ProfileBasicDetailModel[size];
            }
        };

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
                if(response!=null&&response.isSuccess()){
                    if(response.body().isSuccess()){
                        ProfileBasicDetailModel model=response.body().getProfileInfo();
                        if(model!=null){
                            sharedPref.setUserName(model.getName());
                            progress.setVisibility(View.GONE);
                            scroll_layout.setVisibility(View.VISIBLE);
                            if(model.getRollno()==null)
                                et2.setVisibility(View.GONE);
                            et1.setText(model.getName());
                            et2.setText(model.getRollno());
                            et4.setText(model.getEmail());
                            et5.setText(model.getPhone());
                        }
                        else {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();

                        }
                    }
                }
                else {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();

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


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USER_NAME,et1.getText().toString());
        outState.putString(USER_EMAIL,et4.getText().toString());
        outState.putString(USER_ROLLNO,et2.getText().toString());
        outState.putString(USER_PHONE,et5.getText().toString());
    }
}



