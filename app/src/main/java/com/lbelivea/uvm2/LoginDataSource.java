package com.lbelivea.uvm2;


import android.util.Log;

import java.io.IOException;
import com.lbelivea.uvm2.ApiInteractions.*;

/**
 * Class that handles authentication w/ authentication credentials and retrieves user information.
 */
public class LoginDataSource {

    public LoggedInUser authentication(String username, String password) {
        // try to login
        try {
            // get the user from api
            new GetUser().execute(username, password);

            // new loggedinuser
            LoggedInUser user = new LoggedInUser(username, password);

            // return the user
            return user;

        } catch (Exception e) {
            Log.d("IO", "authentication: error");

            // return a bad loggedinuser
            return new LoggedInUser(false);
        }
    }

    public void logout() {

    }
}
