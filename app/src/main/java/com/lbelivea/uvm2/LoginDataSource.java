package com.lbelivea.uvm2;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public com.lbelivea.uvm2.Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            String urlString = "http://73.219.102.187:6969";

            String get = "/getUser?netId=lmpotasi";

            String addClasses = "/addClasses?netId=lmpotasi&classes=RN1,RN2,RN3";

            String urlStringGet = urlString + addClasses;

            URL url = new URL(urlStringGet);

            Scanner sc = new Scanner(url.openStream());


            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new com.lbelivea.uvm2.Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new com.lbelivea.uvm2.Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
