package com.task_egyadv.Activites;

import android.app.ProgressDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.task_egyadv.Model.Login_Response;
import com.task_egyadv.Model.UserLogin;
import com.task_egyadv.R;
import com.task_egyadv.View_Model.Login_ViewModel;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {
    @BindView(R.id.login_email)
    EditText login_email;
    @BindView(R.id.login_password)
    EditText login_password;
    @BindView(R.id.Btn_Sigin)
    Button Btn_Sigin;
    Login_ViewModel login;
    ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_login);
        login = ViewModelProviders.of(this).get(Login_ViewModel.class);
        progressdialog = new ProgressDialog(this);
        ButterKnife.bind(this);
        Login();

    }
    public void Login(){
        Btn_Sigin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FUtilsValidation.isEmpty(login_email, getString(R.string.insertemail));
            FUtilsValidation.isEmpty(login_password,getString(R.string.insertpass));
            if (!login_email.getText().toString().equals("")
                    && !login_password.getText().toString().equals("")) {
                UserLogin user = new UserLogin();
                user.setEmail(login_email.getText().toString());
                progressdialog.setMessage("Please Wait While Loading ....");
                progressdialog.show();
                user.setPassword(login_password.getText().toString());
                login.getLogin(user,Login.this).observe(Login.this, new Observer<Login_Response>() {
                    @Override
                    public void onChanged(@Nullable Login_Response loginData) {
                        progressdialog.dismiss();
                        if(loginData!=null){
                            if(loginData.getMessage().equals("تم تسجيل الدخول")){
                                Toast.makeText(Login.this, getResources().getString(R.string.loginsucces), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                            }else  if(loginData.getMessage().equals("خطأ فى تسجيل الدخول")){

                                Toast.makeText(Login.this, getResources().getString(R.string.invalidemail), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Login.this, "Error !! There is some thing wrong", Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }}
    });
}
}
