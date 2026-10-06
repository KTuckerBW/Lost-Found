package src.auth;

import src.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Authenticates users against a local CSV file.
 *
 * <p>The CSV must have a header row with at least
 * columns {@code Username} and {@code Password}.
 */
public class CsvUserAuthenticator implements UserAuthenticator {

    private final String filePath;

    public CsvUserAuthenticator(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public User authenticate(String username, String password) {
        List<Map<String, String>> rows = parseCsv();

        for (Map<String, String> row : rows) {
            if (username.equals(row.get("Username")) && password.equals(row.get("Password"))) {
                return User.fromRow(row);
            }
        }
        return null;
    }

    /**
     * Reads and parses the CSV file into a list of row maps.
     *
     * <p>The first line is the header; each subsequent line is
     * split on {@code ","} and zipped with the header.
     */
    private List<Map<String, String>> parseCsv() {
        List<Map<String, String>> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] header = reader.readLine().split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < header.length; i++) {
                    row.put(header[i], fields[i]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
}   