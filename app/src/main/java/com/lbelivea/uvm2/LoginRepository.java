package com.lbelivea.uvm2;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Log;
import java.io.IOException;
import com.lbelivea.uvm2.ApiInteractions.*;
/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of authentication status and user credentials information.
 */
public class LoginRepository{

    private static volatile LoginRepository instance;
    //public String APIResponse = "";

    public static boolean realUser = false;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    public static LoggedInUser user = null;

    // private constructor : singleton access
    public LoginRepository() {
    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public LoggedInUser login(String username, String password) {
        // not handle authentication
        //creating a logged in user not setting whether they are logged in or not. (isLoggedIn = null)
        LoggedInUser result = new LoggedInUser(username, password);
        authentication(result.getNetId(), result.getPassword());

        if(user.isLoggedIn()){
            result.setLoggedIn(true);
        }
        else{
            result.setLoggedIn(false);
        }
        setLoggedInUser(result);

        return result;
    }

        public LoggedInUser authentication(String username, String password) {

            try {
                // TODO: handle loggedInUser authentication
                new GetUser().execute(username, password);
                LoggedInUser user = new LoggedInUser(username, password);

                setLoggedInUser(user);
                return user;
            } catch (Exception e) {
                Log.d("IO", "authentication: error");
                LoggedInUser badUser = new LoggedInUser(username, password);
                badUser.setLoggedIn(false);
                return badUser;
            }
        }

        //getting the GetUser API response to see if it matches records. The going to change the
        //way that the LoggedInUser is going to be set.
//        @Override
//        public void processFinish(String response){
//            APIResponse = response;
//        }

}
