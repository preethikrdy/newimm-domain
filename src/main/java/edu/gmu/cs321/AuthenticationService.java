package edu.gmu.cs321;

public class AuthenticationService {
    private boolean authenticated;

    public AuthenticationService() {
        this.authenticated = false;
    }

    // Authenticate method expects a username and password
    public boolean authenticate(String username, String password) {
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            this.authenticated = true;
            return true;
        }
        this.authenticated = false;
        return false;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}