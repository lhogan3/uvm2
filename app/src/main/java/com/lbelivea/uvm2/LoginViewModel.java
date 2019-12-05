package com.lbelivea.uvm2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;


public class LoginViewModel extends ViewModel {

    public static boolean realUser = false;

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

    public boolean login(String username, String password) {
        // can be launched in a separate asynchronous job
        //LoggedInUser result = loginRepository.login(username, password);
        try {
            // TODO: handle loggedInUser authentication
            new ApiInteractions.GetUser().execute(username, password);

<<<<<<< HEAD
        } catch (Exception e) {
            Log.d("IO", "authentication: error");
        }
        if (realUser) {
            //LoggedInUser data = new LoggedInUser(result.getNetId(), result.getPassword());
            //loginResult.setValue(new LoginResult(new LoggedInUserView(data.getPassword())));
            //realUser = false;
            return true;
=======
        if (result.isLoggedIn()) {
            LoggedInUser data = new LoggedInUser(result.getNetId(), result.getPassword());
            loginResult.setValue(new LoginResult());
>>>>>>> fadd054d5bbd4640a0459f49c026157f141b42ba
        } else {
            //loginResult.setValue(new LoginResult(R.string.login_failed));
            //realUser = false;
            return false;
        }
    }

//    public LoggedInUser login(String username, String password) {
//        // not handle authentication
//        //creating a logged in user not setting whether they are logged in or not. (isLoggedIn = null)
//        LoggedInUser result = new LoggedInUser(username, password);
//        authentication(result.getNetId(), result.getPassword());
//
//        if(user.isLoggedIn()){
//            result.setLoggedIn(true);
//        }
//        else{
//            result.setLoggedIn(false);
//        }
//        setLoggedInUser(result);
//
//        return result;
//    }

//    public LoggedInUser authentication(String username, String password) {
//
//        try {
//            // TODO: handle loggedInUser authentication
//            new ApiInteractions.GetUser().execute(username, password);
//            LoggedInUser user = new LoggedInUser(username, password);
//
//            setLoggedInUser(user);
//            return user;
//        } catch (Exception e) {
//            Log.d("IO", "authentication: error");
//            LoggedInUser badUser = new LoggedInUser(username, password);
//            badUser.setLoggedIn(false);
//            return badUser;
//        }
//    }





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
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 0;
    }
}
