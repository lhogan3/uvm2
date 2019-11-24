package com.lbelivea.uvm2;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String netId;
    private String password;
    private boolean loggedIn;

    public LoggedInUser(String netId, String password) {
        this.netId = netId;
        this.password = password;
        this.loggedIn = true;
    }

    public LoggedInUser(boolean bad) {
        this.netId = "";
        this.password = "";
        this.loggedIn = false;
    }

    public String getNetId() {
        return netId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
