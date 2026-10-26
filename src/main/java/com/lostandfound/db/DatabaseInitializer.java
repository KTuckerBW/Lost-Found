package com.lostandfound.db;

import com.password4j.Password;

import java.sql.*;

public class DatabaseInitializer {
    public static void initialize(Database database) throws SQLException {
        try (Connection conn = database.getConnection();) {

            createUserTable(conn);
            createListingsTable(conn);
            insertRowsWithHashedPassword(conn);
        }

    }

    private static void createUserTable(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS Users (
            user_id INTEGER PRIMARY KEY AUTOINCREMENT,
            role TEXT NOT NULL,
            username TEXT NOT NULL UNIQUE,
            password TEXT NOT NULL,
            email TEXT NOT NULL
            )
            """;
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void createListingsTable(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS Listings (
                listing_id INTEGER PRIMARY KEY AUTOINCREMENT,
                product_name TEXT NOT NULL,
                photo TEXT,
                description TEXT,
                location TEXT,
                category TEXT,
                status TEXT,
                date_listed TEXT,
                expiration_date TEXT,
                value_status TEXT,
                user_id INTEGER NOT NULL,
                FOREIGN KEY(user_id) REFERENCES Users(user_id)
                )
            """;
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void insertRowsWithHashedPassword(Connection conn) {

        String sql = "INSERT OR IGNORE INTO Users (role, username, password, email) VALUES(?, ?, ?, ?)";

        String[][] userRecords = {
            {"Admin", "admin", "password1234", "admin@campus.edu"},
            {"User", "michael", "dontleavemehere", "michael@campus.edu"},
            {"Admin", "DMFAdmin", "DanaMohlerFaria1234", "dmf_admin@campus.edu"},
            {"Admin", "Alex", "BlueSky2026", "alex_admin@campus.edu"},
            {"User", "Jordan", "coffeeandcode", "jordan@campus.edu"},
            {"User", "Taylor", "SunnyDay123", "taylor@campus.edu"},
            {"Admin", "Sam", "CampusAdmin1", "sam_admin@campus.edu"},
            {"User", "Casey", "letmeinplease", "casey@campus.edu"}
        };


        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            for (String[] record : userRecords) {
                String role = record[0];
                String username = record[1];
                String plainTextPassword = record[2];
                String email = record[3];

                String hashedPassword = Password.hash(plainTextPassword).withBcrypt().getResult();

                pstm.setString(1, role);
                pstm.setString(2, username);
                pstm.setString(3, hashedPassword);
                pstm.setString(4, email);

                pstm.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
