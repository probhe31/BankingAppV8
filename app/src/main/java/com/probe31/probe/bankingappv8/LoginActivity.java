package com.probe31.probe.bankingappv8;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.probe31.probe.bankingappv8.model.TokenResponse;
import com.probe31.probe.bankingappv8.viewmodel.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity {

    LoginActivityViewModel loginActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginActivityVM = ViewModelProviders.of(this).get(LoginActivityViewModel.class);

        /*loginActivityVM.getLoginStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLogin) {
                if(isLogin)
                {
                    goToMainMenu();
                }
            }
        });*/



    }

    public void sendLoginForm(View view) {

        EditText dniEdit = (EditText) findViewById(R.id.dni_txt);
        EditText passwordEdit = (EditText) findViewById(R.id.password_txt);

        String dni = dniEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        loginActivityVM.tryLogin(dni, password).observe(this, new Observer<TokenResponse>() {
            @Override
            public void onChanged(@Nullable TokenResponse tokenResponse) {
                Log.d("view", tokenResponse.toString());
                if(tokenResponse!=null)
                {
                    goToMainMenu();
                }
            }
        });


    }

    void goToMainMenu()
    {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
