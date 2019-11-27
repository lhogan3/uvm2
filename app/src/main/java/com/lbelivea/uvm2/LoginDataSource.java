package com.lbelivea.uvm2;


import android.util.Log;

import java.io.IOException;
import com.lbelivea.uvm2.ApiInteractions.*;

/**
 * Class that handles authentication w/ authentication credentials and retrieves user information.
 */
public class LoginDataSource {

    public LoggedInUser authentication(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            new GetUser().execute(username, password);
            LoggedInUser user = new LoggedInUser(username, password);
            return user;
        } catch (Exception e) {
            Log.d("IO", "authentication: error");
            return new LoggedInUser(false);
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
