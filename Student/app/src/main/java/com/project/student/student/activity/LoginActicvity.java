package com.project.student.student.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.student.student.R;

/**
 * Created by parinda on 5/31/16.
 */
public class LoginActicvity extends Activity {

    EditText tvUsername;
    EditText tvPassword;
    TextInputLayout ilUsername;
    TextInputLayout ilPassword;
    TextView btnLogin;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUI();
        setListners();
    }

    private void setListners() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extractFieldsfromUI();
                loginProcess();
            }
        });
    }

    private void extractFieldsfromUI() {

        username = tvUsername.getText().toString();
        password = tvPassword.getText().toString();
    }

    private void loginProcess() {

        if (!validateUser()) {
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    progressDialog.dismiss();
                    sendLoginRequest();
                }
            }
        };
        timerThread.start();
    }

    private void sendLoginRequest() {

        Intent intent = new Intent(LoginActicvity.this, MainActivity.class);
        startActivity(intent);
    }

    public boolean validateUser() {

        boolean valid = true;

        if (username == null || username.equals("")) {
            ilUsername.setError(getString(R.string.error_username_empty));
            valid = false;
        } else {
            ilUsername.setError(null);
        }

        if (password == null || password.equals("")) {
            ilPassword.setError(getString(R.string.error_password_empty));
            valid = false;
        } else {
            ilPassword.setError(null);
        }

        return valid;

    }

    private void setUI() {

        ilUsername = (TextInputLayout) findViewById(R.id.inputLayoutUsername);
        ilPassword = (TextInputLayout) findViewById(R.id.inputLayoutPassword);
        tvUsername = (EditText) findViewById(R.id.etUsername);
        tvPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        ilUsername.setHintEnabled(false);
        ilPassword.setHintEnabled(false);
    }
}
