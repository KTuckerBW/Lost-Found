package src.postings;
import src.model.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class csvPostGrabber {
    private final String filePath;

    public csvPostGrabber(String filePath){
        this.filePath = filePath;
    }


    // Modified helper method from Bradley's parseCsv
    private List<Map<String, Object>> parseCsv() {
        List<Map<String, Object>> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) return rows; // Handle empty file gracefully

            String[] header = line.split(",");

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                // Modified the map to accept strings and ints
                Map<String, Object> row = new HashMap<>();

                // Check if it's the only int we are saving and turn it into an int value
                for (int i = 0; i < header.length; i++) {
                    if ("ProductID".equals(header[i])) {
                        row.put(header[i], Integer.valueOf(fields[i]));
                    } else {
                        row.put(header[i], fields[i]);
                    }
                }

                // Add the populated row to list
                rows.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    // Test method to grab data
    public static void main(String[] args) {
        String filePath = "./data/LstAndFndListing.csv";

        csvPostGrabber grabber = new csvPostGrabber(filePath);

        // Updated variable type to match the method's new return type
        List<Map<String, Object>> rows = grabber.parseCsv();

        System.out.println("Rows parsed: " + rows.size());

        for (int i = 0; i < rows.size(); i++) {
            System.out.println("Row " + (i + 1) + ": " + rows.get(i));
        }
    }
}
