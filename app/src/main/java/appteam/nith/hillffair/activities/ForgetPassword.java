package appteam.nith.hillffair.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.ForgotPassword;
import appteam.nith.hillffair.models.SendPassword;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.Utils;

import net.steamcrafted.loadtoast.LoadToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {
    Button confirmpwd;
    EditText pwd;
    EditText repwd;
    EditText code;
    EditText email;
    String email1;
    ForgotPassword fin;
    Button verifyemail;
    boolean ispassword=false;
    TextInputLayout pwdTextInput;
    TextInputLayout repwdTextInput;
    TextInputLayout emailTextInputLayout;
    boolean isemail=false;
    private LoadToast loadToast;
    boolean ispwd=false;
    boolean isrepwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref = new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        loadToast=new LoadToast(this);
        verifyemail = (Button)findViewById(R.id.foremail);
        confirmpwd = (Button)findViewById(R.id.confirmbutton);
        pwd = (EditText)findViewById(R.id.forgetpwd);
        repwd = (EditText) findViewById(R.id.confirmforgetpwd);
        pwdTextInput = (TextInputLayout) findViewById(R.id.pwdtextinput);
        repwdTextInput = (TextInputLayout) findViewById(R.id.repwdtextinput);
        email = (EditText)findViewById(R.id.forgotemail);
        emailTextInputLayout = (TextInputLayout)findViewById(R.id.emailtextinput);
        code=(EditText)findViewById(R.id.confirmcode);

        View view = ForgetPassword.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        verifyemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if(email.getText().toString().isEmpty() || !isemail)
                    {
                        Toast.makeText(ForgetPassword.this, "Enter the correct email", Toast.LENGTH_SHORT).show();
                    }
                else {
                        email1 = email.getText().toString();
                        loadToast.show();
                        sendemail(email1);
                    }
            //    verifyemail.setEnabled(false);


                View view = ForgetPassword.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

           }
        });

        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Utils.checkData(pwd.getText().toString()) && pwd.getText().toString().length() > 8) {

                    pwdTextInput.setErrorEnabled(false);
                } else {
                    pwdTextInput.setErrorEnabled(true);
                    pwdTextInput.setError("PLEASE ENTER MORE THAN 8 CHARACTER");
            }
        }

    });

    repwd.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (Utils.checkData(repwd.getText().toString()) && repwd.getText().toString().equals(pwd.getText().toString())) {
                repwdTextInput.setErrorEnabled(false);
                ispassword = true;
            } else {
                repwdTextInput.setError("PASSWORD DOES NOT MATCH");
                ispassword = false;

          }
        }
    });

    email.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
         }

         @Override
         public void afterTextChanged(Editable s) {


             if (Utils.checkData(email.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                 emailTextInputLayout.setErrorEnabled(false);
                 isemail = true;
             } else {

                 emailTextInputLayout.setError("PLEASE ENTER THE EMAIL");

                 isemail = false;
             }
         }

      }
    );


   confirmpwd.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           int vericode=0;
           String mycode = code.getText().toString();
           if(!mycode.isEmpty())
            vericode = Integer.parseInt(mycode);



           if(fin==null)return;

           if(pwd.getText().toString().isEmpty() || repwd.getText().toString().isEmpty() || mycode.isEmpty())
           {
               Toast.makeText(ForgetPassword.this, "Please Enter All The Data Correctly", Toast.LENGTH_SHORT).show();
           }
           else if( isPasswordValid(pwd.getText().toString()) && ispassword && repwd.getText().toString().equals(pwd.getText().toString()) ) {
               if(vericode==fin.user.getCode() ) {
                   send_id(fin.user.get_id1(), pwd.getText().toString());
                   loadToast.show();
               }
               else if(vericode!=fin.user.getCode()){
                   Toast.makeText(ForgetPassword.this, "Code entered is not correct", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(ForgetPassword.this, "Enter the password correctly", Toast.LENGTH_SHORT).show();
               }
           }


         }

       });

    }

    private void sendemail (String Email) {

        APIINTERFACE mApiService = Utils.getRetrofitService();
        Call<ForgotPassword> mService = mApiService.forgotPassword(Email);
        mService.enqueue(new Callback<ForgotPassword>() {
            @Override
            public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                verifyemail.setEnabled(true);

                ForgotPassword mLoginObject = response.body();
                int status_code = response.code();
               // boolean returnedResponse = response.body().isSuccess();

                if (mLoginObject != null && response.isSuccess()) {
                    boolean returnedResponse = mLoginObject.isSuccess();
                    if (returnedResponse) {
                        loadToast.success();
                        verifyemail.setVisibility(View.GONE);
                        emailTextInputLayout.setVisibility(View.GONE);
                        pwdTextInput.setVisibility(View.VISIBLE);
                        repwdTextInput.setVisibility(View.VISIBLE);
                        code.setVisibility(View.VISIBLE);
                        pwd.setVisibility(View.VISIBLE);
                        repwd.setVisibility(View.VISIBLE);
                        confirmpwd.setVisibility(View.VISIBLE);


                        Toast.makeText(ForgetPassword.this, "Enter the new Password and code", Toast.LENGTH_SHORT).show();
                        fin = mLoginObject;

                        Log.v("fin-check",fin+"");

                    }

                    else{
                        loadToast.error();
                        Toast.makeText(ForgetPassword.this, "Please check your details", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    loadToast.error();
                    Toast.makeText(ForgetPassword.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                  }

                }

            @Override
            public void onFailure(Call<ForgotPassword> call, Throwable t) {
                verifyemail.setEnabled(true);
                loadToast.error();
                Toast.makeText(ForgetPassword.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void send_id(String id,  String pwd){

        APIINTERFACE mApiService = Utils.getRetrofitService();

        Call<SendPassword> mService = mApiService.sendPassword(id,pwd);
        mService.enqueue(new Callback<SendPassword>() {
            @Override
            public void onResponse(Call<SendPassword> call, Response<SendPassword> response) {
                SendPassword obj = response.body();
                if (obj != null && response.isSuccess()) {
                    boolean returnedResponse = obj.isSuccess();

                    if (returnedResponse) {
                        Toast.makeText(ForgetPassword.this, "Password changed Successfully", Toast.LENGTH_LONG).show();
                      //  Toast.makeText(ForgetPassword.this,pwd , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        loadToast.success();
                        finish();
                    } else {
                        loadToast.error();
                        Toast.makeText(ForgetPassword.this, "Internal error", Toast.LENGTH_SHORT).show();
                    }
                }

                else
                {
                    loadToast.error();
                    Toast.makeText(ForgetPassword.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SendPassword> call, Throwable t) {
                loadToast.error();
                Toast.makeText(ForgetPassword.this, "Check your Internet Permission", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        if(password.length()<=8)
        {
            Toast.makeText(this, "Please Keep the length of the password more then 8", Toast.LENGTH_SHORT).show();
        }

        return password.length() > 8;

    }


}
