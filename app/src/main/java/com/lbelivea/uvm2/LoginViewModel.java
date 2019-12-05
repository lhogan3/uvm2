package com.lbelivea.uvm2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;



public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        LoggedInUser result = loginRepository.login(username, password);

        if (result.isLoggedIn()) {
            LoggedInUser data = new LoggedInUser(result.getNetId(), result.getPassword());
            loginResult.setValue(new LoginResult());
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        // if no username
        if (username == null) {
            // not valid
            return false;
        }

        // not null so it could be a netId, checks with banner
        return true;
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        // if no password
        if (password == null) {
            // not valid
            return false;
        }

        // not null could be a password, checks with banner
        return true;
    }
}
