package com.appteamnith.hillffair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.*;

import net.steamcrafted.loadtoast.LoadToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends AppCompatActivity {



    private LoadToast loadToast;
    String name;
    String rollno;
    String email;
    String phoneno;
    String password;
    View focusView = null;
    public static final String BASE_URL = "https://festnimbus.herokuapp.com/auth/local/";
    boolean isemail=true,ispassword=true,isphone=true,isnitian=false,isValidRollNo=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        loadToast = new LoadToast(this);
        final   EditText   Name = (EditText)findViewById(R.id.name);
       final EditText  Rollno = (EditText)findViewById(R.id.rollno);
     final EditText   Email = (EditText)findViewById(R.id.email);
      final EditText  Phoneno = (EditText)findViewById(R.id.phone);
    final EditText    Password = (EditText)findViewById(R.id.pwd);
        final EditText ConfirmPassword = (EditText)findViewById(R.id.repwd);
        isemail= Utils.checkData(Email.getText().toString());
        ispassword= Utils.checkData(Password.getText().toString());
        isphone= Utils.checkData(Phoneno.getText().toString());

        final TextInputLayout emailTextInputLayout= (TextInputLayout) findViewById(R.id.input_email);
        final TextInputLayout passwordTextInputLayout= (TextInputLayout) findViewById(R.id.input_pwd);
        final TextInputLayout confirmTextInputLayout= (TextInputLayout) findViewById(R.id.input_repwd);
        final TextInputLayout phonenoTextInputLayout= (TextInputLayout) findViewById(R.id.input_phone);
        final TextInputLayout rollnoTextInputLayout= (TextInputLayout) findViewById(R.id.input_roll);

        Button registrationButton = (Button)findViewById(R.id.signup_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rollno = Rollno.getText().toString();
                phoneno = Phoneno.getText().toString();


                email = Email.getText().toString();
                password = Password.getText().toString();
                loadToast.setText("Loading..");
                loadToast.show();


                registrationProcessWithRetrofit(email, password,rollno,phoneno,name);
            }
        });


        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (Utils.checkData(Email.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                    emailTextInputLayout.setErrorEnabled(false);
                    isemail = true;
                } else {
                  //  emailTextInputLayout.setError("PLEASE ENTER THE EMAIL");
                    isemail = false;

                }


            }

            @Override
            public void afterTextChanged(Editable s) {




                if (Utils.checkData(Email.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                    emailTextInputLayout.setErrorEnabled(false);
                    isemail = true;
                } else {

              //      emailTextInputLayout.setError("PLEASE ENTER THE EMAIL");

                    Email.setError("PLEASE ENTER THE EMAIL");
                    isemail = false;
                }
            }

            }
        );

    ConfirmPassword.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(Utils.checkData(ConfirmPassword.getText().toString())&&ConfirmPassword.getText().toString().equals(Password.getText().toString())){
                confirmTextInputLayout.setErrorEnabled(false);
                ispassword=true;
            }
            else {
            //    confirmTextInputLayout.setError("PASSWORD DOES NOT MATCH");
                ispassword=false;
                ConfirmPassword.setError("PASSWORD DOES NOT MATCH");
            }
        }
    });



        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Utils.checkData(Password.getText().toString())&& Password.getText().toString().length()>8){
                    passwordTextInputLayout.setErrorEnabled(false);
                }
                else {

                    passwordTextInputLayout.setErrorEnabled(true);
                //    passwordTextInputLayout.setError("PLEASE ENTER MORE THAN 8 CHARACTER");

                    Password.setError("PLEASE ENTER MORE THAN 8 CHARACTER");
                }
            }
        });


        Phoneno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Phoneno.getText().toString().length()==10){
                    phonenoTextInputLayout.setErrorEnabled(false);
                    isphone=true;}
                else {
                    //phonenoTextInputLayout.setError("NOT VALID PHONE NUMBER");
                    isphone=false;
                    Phoneno.setError("NOT VALID PHONE NUMBER");
                }
            }
        });



        Rollno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String input=Rollno.getText().toString();
                String ptr="((1(4|5|6)MI5((0[1-9])|([1-5][0-9])|60))|(1(4-6)M[1-5]((0[1-9])|([1-5][0-9])|60))|(116((0[1-9])|[1-5][0-9]|60))|(1(5|6)MI4((0[1-9])|([1-5][0-9])|60))|(1[2-6][1-7]((0[1-9])|([1-8][0-9])|90))|(IIITU1(5|6)(1|2)((0[1-9])|([1-2][0-9])|30)))";

                Pattern p=Pattern.compile(ptr);
                Matcher m=p.matcher(input.toUpperCase().trim());

                if(m.matches()){
                    rollnoTextInputLayout.setErrorEnabled(false);
                    isValidRollNo=true;
                }else{
                  //  rollnoTextInputLayout.setErrorEnabled(true);
                    isValidRollNo=false;
                  //  rollnoTextInputLayout.setError("Enter Valid RollNo");

                    Rollno.setError("Enter Valid RollNo");
                }

            }
        });





    }







    private APIINTERFACE getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIINTERFACE mInterfaceService = retrofit.create(APIINTERFACE.class);
        return mInterfaceService;
    }





    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private void registrationProcessWithRetrofit(final String email, String password,final String rollno,final String phoneno,final String name){
        APIINTERFACE mApiService = this.getInterfaceService();

        Call<Login> mService = mApiService.registration(email, password,rollno,name);
        mService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login mLoginObject = response.body();
                String returnedResponse = mLoginObject.isLogin;
                //showProgress(false);
                if(returnedResponse.trim().equals("1")){
                    // redirect to Main Activity page
                    Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    loadToast.success();
                    finish();
                    loginIntent.putExtra("EMAIL", email);
                    startActivity(loginIntent);
                }
                if(returnedResponse.trim().equals("0")){
                    // use the registration button to register
                   // failedLoginMessage.setText(getResources().getString(R.string.registration_failed));
                  //  Password.requestFocus();
                    Toast.makeText(SignUpActivity.this, "Already Used", Toast.LENGTH_SHORT).show();
                    loadToast.error();
                }
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                call.cancel();
                loadToast.error();
                Toast.makeText(SignUpActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }



}
