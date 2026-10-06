package src.model;

import java.util.Map;

public class User {
    private final String role;
    private final String username;
    private final String password;
    private final String email;

    private User(
            String role,
            String username,
            String password,
            String email) {

        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private User(Map<String, String> row) {
        this.role = row.get("Role");
        this.username = row.get("Username");
        this.password = row.get("Password");
        this.email = row.get("Email");
    }

    // Static factory method
    public static User of(
            String role,
            String username,
            String password,
            String email) {

        return new User(role, username, password, email);

    }

    // Static factory method
    public static User fromRow(Map<String, String> row) {
        return new User(row);
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
