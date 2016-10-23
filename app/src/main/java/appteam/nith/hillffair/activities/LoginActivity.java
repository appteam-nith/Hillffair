package appteam.nith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.Login;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.Utils;

import net.steamcrafted.loadtoast.LoadToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    ImageView logo;
    LinearLayout layout;
    private Button login;
    private LoadToast loadToast;
    private EditText mEmailView;
    private EditText mPasswordView;
    View focusView = null;
    String email;
    TextView forgot;
    String password;
    SharedPref pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.registar_Btn_login);
        forgot = (TextView)findViewById(R.id.forgot);
        logo = (ImageView) findViewById(R.id.logo);
        layout = (LinearLayout) findViewById(R.id.login_login);
        layout.setVisibility(View.INVISIBLE);
        final Animation bounceLogo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loginlogo_anim);
        final Animation layoutFade = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        loadToast = new LoadToast(this);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgetPassword.class));
            }
        });
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
            // startActivity(new Intent(LoginActivity.this,HomeActivity.class));
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
            loadToast.setText("LOADING");
            loadToast.show();
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
        if (TextUtils.isEmpty(email)||!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
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
                int status_code = response.code();

                if (mLoginObject != null && response.isSuccess()) {
                    boolean returnedResponse = mLoginObject.success;

                    if (returnedResponse) {
                        pref.setUserId(mLoginObject.getId());
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }else {
                        String error = mLoginObject.getMsg();
                        if (error != null && !error.isEmpty()) {
                            Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                            Log.v("msg",error);
                        }
                        loadToast.error();
                    }
                } else {
                    loadToast.error();
                    if (status_code == 503) {
                        Toast.makeText(LoginActivity.this, "Server Down", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
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
