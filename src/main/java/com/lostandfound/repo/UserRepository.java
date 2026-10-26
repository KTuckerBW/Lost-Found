package com.lostandfound.repo;

import com.lostandfound.model.User;

/**
 * This class exists to allow CRUD operations on User
 */
public class UserRepository {

    public static boolean exists(String username) {

        String sql = """
            SELECT * FROM Users
            WHERE Username = ?
            """;

        try {
           // TODO: implement logic
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public User findByUsername(String username) {
        String sql = """
            """;
            // TODO: return user record
        return User;
    }
}
