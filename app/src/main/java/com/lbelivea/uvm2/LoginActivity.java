package com.lbelivea.uvm2;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static LoginActivity loginActivityInstance;
    private LoginViewModel loginViewModel;
    public static LoggedInUser user = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivityInstance = this;
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        // edit texts for the username and password
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);

        // button to press for login
        final Button loginButton = findViewById(R.id.login);

        // progress bar for when it is loading the main activity
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        //
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                setResult(Activity.RESULT_OK);
            }

        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            // need to override
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            // need to override
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        // when the login button is clicked
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set loading bar to visible
                loadingProgressBar.setVisibility(View.VISIBLE);

                // get the username and password from the edit text
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // create new loggedinuser with the username and password
                user = new LoggedInUser(username, password);

                // try to get the user from api
                try {
                    new ApiInteractions.GetUser().execute(username, password);
                } catch (Exception e) {
                    Log.d("IO", "authentication: error");
                }
            }
        });
    }

    public void attemptLogin(boolean loggedIn) {
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        // if we login
        if(loggedIn) {
            // go to the main activity
            Intent changeToMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(changeToMain);
        } else {
            // else tell user login failed
            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
            loadingProgressBar.setVisibility(View.INVISIBLE);
        }

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
