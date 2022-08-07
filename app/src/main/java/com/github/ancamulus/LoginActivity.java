package com.github.ancamulus;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.ancamulus.viewmodel.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private LoginActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        usernameEditText = findViewById(R.id.activity_login_et_username);
        passwordEditText = findViewById(R.id.activity_login_et_password);
        loginButton = findViewById(R.id.activity_login_btn_login);

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.setUsername(editable.toString());
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.setPassword(editable.toString());
            }
        });

        LiveData<Boolean> loginButtonEnabled = viewModel.isLoginButtonEnabled();
        loginButtonEnabled.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean b) {
                loginButton.setEnabled(b);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
            .setTitle("Exit Ancamulus?")
            .setMessage("Are you sure you want to exit?")
            .setNegativeButton(android.R.string.no, null)
            .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                finishAffinity();
                System.exit(0);
            }).create().show();
    }
}