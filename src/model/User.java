package src.model;

import java.util.Map;

public class User {
    private final String role;
    private final String username;
    private final String password;
    private final String email;

    public User(String role, String username, String password, String email) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(Map<String, String> data){
        this.role = data.get("Role");
        this.username = data.get("Username");
        this.password = data.get("Password");
        this.email = data.get("Email");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
