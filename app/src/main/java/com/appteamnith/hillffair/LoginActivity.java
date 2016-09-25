package com.appteamnith.hillffair;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.Toast;

import net.steamcrafted.loadtoast.LoadToast;


public class LoginActivity extends AppCompatActivity {

    ImageView logo;
    ScrollView layout;
    private Button login;
    private LoadToast loadToast;

    private final String TAG = "LoginActivity";

    public static final String BASE_URL = "http://nigerianstudentshop.com/";

    private EditText mEmailView;
    private EditText mPasswordView;
    View focusView = null;
    String email;
    String password;

    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        progress = new ProgressDialog(this);
             mEmailView = (EditText)findViewById(R.id.email);
        mPasswordView = (EditText)findViewById(R.id.password);
        login= (Button) findViewById(R.id.registar_Btn_login);

        logo = (ImageView) findViewById(R.id.logo);
        layout = (ScrollView) findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        final Animation bounceLogo = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.loginlogo_anim);
        final Animation layoutFade = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in);
        loadToast=new LoadToast(this);

        logo.startAnimation(bounceLogo);
        bounceLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.startAnimation(layoutFade);
                layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              loadToast.setText("LOADING");
              loadToast.show();
              attemptLogin();
          }
      });
        findViewById(R.id.signup_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

    }


    private void attemptLogin(){
        boolean mCancel = this.loginValidation();
        if (mCancel) {
            focusView.requestFocus();
        } else {
            loginProcessWithRetrofit(email, password);
        }

    }


    private boolean loginValidation() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        boolean cancel = false;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        return cancel;
    }



    private void loginProcessWithRetrofit(final String email, String password){

        APIINTERFACE mApiService = this.getInterfaceService();
        Toast.makeText(LoginActivity.this, "Please check your network jjjj", Toast.LENGTH_LONG).show();

       progress.setProgressStyle(android.R.attr.progressBarStyleSmall);
        Call<Login> mService = mApiService.authenticate(email, password);
        mService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {


                Login mLoginObject = response.body();


                String returnedResponse = response.toString();
           //     Toast.makeText(LoginActivity.this, "Returned " + returnedResponse, Toast.LENGTH_LONG).show();


                if(returnedResponse.trim().equals("1")){
                    // redirect to Main Activity page
                    Intent loginIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                    loginIntent.putExtra("EMAIL", email);
                    startActivity(loginIntent);
                    loadToast.success();
                    finish();
                    Toast.makeText(LoginActivity.this, "Succesfull ", Toast.LENGTH_LONG).show();
                }
                else{
                    // use the registration button to register


                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, "Invalid Useraname Pass word", Toast.LENGTH_LONG).show();
                    mPasswordView.requestFocus();
                    loadToast.error();
                }
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                call.cancel();
                Toast.makeText(LoginActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
                progress.dismiss();
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
        return password.length() > 8;
    }




}
