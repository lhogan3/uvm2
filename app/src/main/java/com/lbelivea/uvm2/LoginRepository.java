package com.lbelivea.uvm2;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of authentication status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public LoggedInUser login(String username, String password) {
        // new loggedinuser
        LoggedInUser result = new LoggedInUser(username, password);

        // run authentication on the user
        dataSource.authentication(result.getNetId(), result.getPassword());

        // return the result user
        return result;
    }
}
