package com.appteamnith.hillffair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import net.steamcrafted.loadtoast.LoadToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    ImageView logo;
    ScrollView layout;
    private Button login;
    private LoadToast loadToast;
    private EditText mEmailView;
    private EditText mPasswordView;
    View focusView = null;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.registar_Btn_login);

        logo = (ImageView) findViewById(R.id.logo);
        layout = (ScrollView) findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        final Animation bounceLogo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loginlogo_anim);
        final Animation layoutFade = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        loadToast = new LoadToast(this);

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
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }


    private void attemptLogin() {
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


    private void loginProcessWithRetrofit(final String email, String password) {

        APIINTERFACE mApiService = Utils.getRetrofitService();
        Call<Login> mService = mApiService.login(email, password);
        mService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                loadToast.success();
                Login mLoginObject = response.body();
                boolean returnedResponse = mLoginObject.success;
                if(returnedResponse){
                    startActivity(new Intent(LoginActivity.this,homeActivity.class));
                }
                else {
                    String error = mLoginObject.getError();
                    if (error != null && !error.isEmpty()) {
                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                    loadToast.error();
                }




            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                t.printStackTrace();
                loadToast.error();
                Toast.makeText(LoginActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
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
