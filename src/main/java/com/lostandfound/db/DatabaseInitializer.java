package com.lostandfound.db;

import java.sql.*;

public class DatabaseInitializer {
    public static void initialize(Database database) throws SQLException {
        try (Connection conn = database.getConnection(); Statement st = conn.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS students (
                    student_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE
                )
            """);
            st.execute("""
                CREATE TABLE IF NOT EXISTS courses (
                    course_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    course_code TEXT NOT NULL UNIQUE,
                    course_name TEXT NOT NULL
                )
            """);
            st.execute("""
                CREATE TABLE IF NOT EXISTS student_courses (
                    student_id INTEGER NOT NULL,
                    course_id INTEGER NOT NULL,
                    PRIMARY KEY(student_id, course_id),
                    FOREIGN KEY(student_id) REFERENCES students(student_id),
                    FOREIGN KEY(course_id) REFERENCES courses(course_id)
                )
            """);
            st.execute("""
                CREATE TABLE IF NOT EXISTS requests (
                    request_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    sender_id INTEGER NOT NULL,
                    receiver_id INTEGER NOT NULL,
                    course_id INTEGER NOT NULL,
                    message TEXT,
                    status TEXT NOT NULL DEFAULT 'pending'
                        CHECK(status IN ('pending','accepted','declined')),
                    FOREIGN KEY(sender_id) REFERENCES students(student_id),
                    FOREIGN KEY(receiver_id) REFERENCES students(student_id),
                    FOREIGN KEY(course_id) REFERENCES courses(course_id),
                    CHECK(sender_id <> receiver_id)
                )
            """);
            st.execute("""
                INSERT OR IGNORE INTO students(student_id, first_name, last_name, email) VALUES
                (1,'Ava','Patel','ava.patel@school.edu'),
                (2,'Noah','Kim','noah.kim@school.edu'),
                (3,'Maya','Rivera','maya.rivera@school.edu'),
                (4,'Liam','Chen','liam.chen@school.edu'),
                (5,'Sophia','Johnson','sophia.johnson@school.edu')
            """);
            st.execute("""
                INSERT OR IGNORE INTO courses(course_id, course_code, course_name) VALUES
                (1,'COMP390','Software Engineering'),
                (2,'COMP151','Computer Science I'),
                (3,'COMP152','Data Structures')
            """);
            st.execute("""
                INSERT OR IGNORE INTO student_courses(student_id, course_id) VALUES
                (1,1),(2,1),(3,1),(5,1),(1,3),(4,3),(4,2)
            """);
        }
    }
}

