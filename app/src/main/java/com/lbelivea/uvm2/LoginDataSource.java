package com.lbelivea.uvm2;


import java.io.IOException;
import com.lbelivea.uvm2.ApiInteractions.*;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public com.lbelivea.uvm2.Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            new AddUser().execute(username);
            LoggedInUser fakeUser =
                    new LoggedInUser(username, password);
            return new com.lbelivea.uvm2.Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new com.lbelivea.uvm2.Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
