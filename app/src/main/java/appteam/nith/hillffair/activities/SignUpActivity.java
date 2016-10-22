package appteam.nith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.Register;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.Utils;

import net.steamcrafted.loadtoast.LoadToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {


    private static final String TAG ="signup" ;
    private LoadToast loadToast;
    String name;
    String rollno;
    String email;
    String phoneno;
    String password;
    View focusView = null;
    boolean isemail = false, ispassword = false, isphone = false, isnitian = false, isValidRollNo = false, isName = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        loadToast = new LoadToast(this);
        final EditText Name = (EditText) findViewById(R.id.name);
        final EditText Rollno = (EditText) findViewById(R.id.rollno);
        final EditText Email = (EditText) findViewById(R.id.email);
        final EditText Phoneno = (EditText) findViewById(R.id.phone);
        final EditText Password = (EditText) findViewById(R.id.pwd);
        final EditText ConfirmPassword = (EditText) findViewById(R.id.repwd);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.choice_register);
        isemail = Utils.checkData(Email.getText().toString());
        ispassword = Utils.checkData(Password.getText().toString());
        isphone = Utils.checkData(Phoneno.getText().toString());

        final TextInputLayout nameTextInputLayout = (TextInputLayout) findViewById(R.id.textinputlayoutname);
        final TextInputLayout emailTextInputLayout = (TextInputLayout) findViewById(R.id.input_email);
        final TextInputLayout passwordTextInputLayout = (TextInputLayout) findViewById(R.id.input_pwd);
        final TextInputLayout confirmTextInputLayout = (TextInputLayout) findViewById(R.id.input_repwd);
        final TextInputLayout phonenoTextInputLayout = (TextInputLayout) findViewById(R.id.input_phone);
        final TextInputLayout rollnoTextInputLayout = (TextInputLayout) findViewById(R.id.input_roll);

        Button registrationButton = (Button) findViewById(R.id.signup_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isName && isemail && ispassword && isphone && isPasswordValid(Password.getText().toString()) && ConfirmPassword.getText().toString().equals(Password.getText().toString()) ) {
                    rollno = Rollno.getText().toString();
                    phoneno = Phoneno.getText().toString();
                    name = Name.getText().toString();
                    email = Email.getText().toString();
                    password = Password.getText().toString();
                    loadToast.setText("Loading..");
                    loadToast.show();

                    if (isnitian && isValidRollNo) {
                        registrationProcessWithRetrofit(email, password, rollno, phoneno, name, true);
                    } else {
                        registrationProcessWithRetrofit(email, password, "", phoneno, name, false);
                    }

                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Please Enter The correct data", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Email.addTextChangedListener(new TextWatcher() {
                                         @Override
                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                         }

                                         @Override
                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                         }

                                         @Override
                                         public void afterTextChanged(Editable s) {


                                             if (Utils.checkData(Email.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                                                 emailTextInputLayout.setErrorEnabled(false);
                                                 isemail = true;
                                             } else {

                                                      emailTextInputLayout.setError("PLEASE ENTER THE EMAIL");

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
                if (Utils.checkData(ConfirmPassword.getText().toString()) && ConfirmPassword.getText().toString().equals(Password.getText().toString())) {
                    confirmTextInputLayout.setErrorEnabled(false);
                    ispassword = true;
                } else {
                    confirmTextInputLayout.setError("PASSWORD DOES NOT MATCH");
                    ispassword = false;

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
                if (Utils.checkData(Password.getText().toString()) && Password.getText().toString().length() > 8) {

                    passwordTextInputLayout.setErrorEnabled(false);

                } else {
                    passwordTextInputLayout.setErrorEnabled(true);
                    passwordTextInputLayout.setError("PLEASE ENTER MORE THAN 8 CHARACTER");

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
                if (Phoneno.getText().toString().length() == 10) {
                    phonenoTextInputLayout.setErrorEnabled(false);
                    isphone = true;
                } else {
                    phonenoTextInputLayout.setError("NOT VALID PHONE NUMBER");
                    isphone = false;
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

                String input = Rollno.getText().toString();
                String ptr = "((1(4|5|6)MI5((0[1-9])|([1-5][0-9])|60))|(1(4-6)M[1-5]((0[1-9])|([1-5][0-9])|60))|(116((0[1-9])|[1-5][0-9]|60))|(1(5|6)MI4((0[1-9])|([1-5][0-9])|60))|(1[2-6][1-7]((0[1-9])|([1-8][0-9])|90))|(IIITU1(4|5|6)(1|2)((0[1-9])|([1-2][0-9])|30)))";

                Pattern p = Pattern.compile(ptr);
                Matcher m = p.matcher(input.toUpperCase().trim());

                if (m.matches()) {
                    rollnoTextInputLayout.setErrorEnabled(false);
                    isValidRollNo = true;
                } else {
                    rollnoTextInputLayout.setErrorEnabled(true);
                    isValidRollNo = false;
                    rollnoTextInputLayout.setError("Enter Valid RollNo");

                }
                Log.v("rollisvalid",""+isValidRollNo);

            }
        });

        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    isName = true;
                    nameTextInputLayout.setErrorEnabled(false);
                } else {
                    isName = false;
                    nameTextInputLayout.setError("Please Enter The Name");
                    nameTextInputLayout.setErrorEnabled(true);
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    rollnoTextInputLayout.setVisibility(View.VISIBLE);
                    Log.v("isnitiancheckbox",""+isnitian);
                    isnitian = true;
                } else {
                    rollnoTextInputLayout.setVisibility(View.GONE);
                    isnitian = false;
                }
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

    private void registrationProcessWithRetrofit(final String email, String password, final String rollno, final String phoneno, final String name, boolean isnitian) {
        APIINTERFACE mApiService = Utils.getRetrofitService();
        Call<Register> registerCall = mApiService.register(name, email, password, isnitian, rollno, phoneno);

        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                Register register = response.body();
                int status_code=response.code();
               if(register!=null&&response.isSuccess()){
                   if (register.isSuccess()){
                       Toast.makeText(SignUpActivity.this,"SuccessFully Register Please verify your Email.",Toast.LENGTH_LONG).show();
                       loadToast.success();
                       startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                       finish();
                   }
                   else {
                       loadToast.error();
                       if(register.getMsg()!=null&&!register.getMsg().isEmpty()){
                           Toast.makeText(SignUpActivity.this,register.getMsg(),Toast.LENGTH_SHORT).show();

                       }
                   }
               }
                else  {
                   loadToast.error();
                  if(status_code==503){
                      Toast.makeText(SignUpActivity.this,"Server Down",Toast.LENGTH_SHORT).show();
                  }
                   else
                  {
                      Toast.makeText(SignUpActivity.this, "Check your internet Connection", Toast.LENGTH_SHORT).show();
                  }

               }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                loadToast.error();
                t.printStackTrace();
                loadToast.error();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
