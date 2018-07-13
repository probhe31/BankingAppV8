package com.probe31.probe.bankingappv8;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.probe31.probe.bankingappv8.model.TokenResponse;
import com.probe31.probe.bankingappv8.viewmodel.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity {

    LoginActivityViewModel loginActivityVM;
    RelativeLayout login_progress;
    LinearLayout login_form;


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_progress = findViewById(R.id.loading_progress);
        login_form = findViewById(R.id.login_form);
        loginActivityVM = ViewModelProviders.of(this).get(LoginActivityViewModel.class);

        this.context = this;

        loginActivityVM.checkNetwork().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer idError) {

                if(idError==401){
                    Toast.makeText(context, "Authorization problem", Toast.LENGTH_SHORT).show();
                }

            }
        });

        setTitle(R.string.bank_name);


    }

    public void sendLoginForm(View view) {

        EditText dniEdit = (EditText) findViewById(R.id.dni_txt);
        EditText passwordEdit = (EditText) findViewById(R.id.password_txt);

        dniEdit.setError(null);
        passwordEdit.setError(null);

        boolean cancel = false;
        View focusView = null;

        String dni = dniEdit.getText().toString();
        String password = passwordEdit.getText().toString();


        if(!loginActivityVM.validateDNI(dni))
        {
            dniEdit.setError(getText(R.string.dni_error));
            focusView = dniEdit;
            cancel = true;
        }

        if(!loginActivityVM.validatePassword(password))
        {
            passwordEdit.setError(getText(R.string.password_error));
            focusView = passwordEdit;
            cancel = true;
        }

        if (cancel) {
            if(focusView!=null)
                focusView.requestFocus();
        }else
        {
            loginActivityVM.tryLogin(dni, password).observe(this, new Observer<TokenResponse>() {
                @Override
                public void onChanged(@Nullable TokenResponse tokenResponse) {
                if(tokenResponse!=null)
                {
                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                    preferences.edit().putString("token", tokenResponse.getAccess_token()).commit();
                    preferences.edit().putInt("id_customer", tokenResponse.getCustomer_id()).commit();
                    goToMainMenu();
                }else
                {
                    showProgress(false);
                }
                }
            });

            hideKeyboard(this);
            showProgress(true);
        }




    }


    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    void showProgress(boolean show)
    {
        if(show)
        {
            login_form.setVisibility(View.GONE);
            login_progress.setVisibility(View.VISIBLE);

        }else
        {
            login_form.setVisibility(View.VISIBLE);
            login_progress.setVisibility(View.GONE);

        }
    }

    void goToMainMenu()
    {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
}
