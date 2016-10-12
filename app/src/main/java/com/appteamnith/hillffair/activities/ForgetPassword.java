package com.appteamnith.hillffair.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.application.SharedPref;
import com.appteamnith.hillffair.utilities.Utils;

public class ForgetPassword extends AppCompatActivity {
    Button confirmpwd;
    EditText pwd;
    EditText repwd;
    EditText code;
    EditText email;
    boolean ispassword=false;
    TextInputLayout pwdTextInput;
    TextInputLayout repwdTextInput;
    TextInputLayout emailTextInputLayout;
   boolean isemail=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref = new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        confirmpwd = (Button)findViewById(R.id.confirmbutton);
        pwd = (EditText)findViewById(R.id.forgetpwd);
        repwd = (EditText) findViewById(R.id.confirmforgetpwd);
        pwdTextInput = (TextInputLayout) findViewById(R.id.pwdtextinput);
        repwdTextInput = (TextInputLayout) findViewById(R.id.repwdtextinput);
        email = (EditText)findViewById(R.id.forgotemail);
        emailTextInputLayout = (TextInputLayout)findViewById(R.id.emailtextinput);
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


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }


}
