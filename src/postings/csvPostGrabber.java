package src.postings;

import src.model.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class csvPostGrabber {
    // initialization
    private final String filePath;
    public List<Post> posts = new ArrayList<>();

    public csvPostGrabber(String filePath) {
        this.filePath = filePath;
    }

    // Load posts into an array to use later
    public List<Post> loadPosts() {
        List<Post> posts = new ArrayList<>();

        // Look inside the posts file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Initialization
            String line;
            String headerLine = reader.readLine();

            // Array of headers gathered from the first line in the posts data
            String[] header = headerLine.split(",");

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
                // turn the row data into a post data type
                Post post = Post.fromRow(row);
                // Add the post to the list of posts
                posts.add(post);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }


    // Modified helper method from Bradley's parseCsv
   /* private List<Map<String, Object>> parseCsv() {
        List<Map<String, Object>> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) return rows;

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
*/
    // Test method to grab data
    public static void main(String[] args) {
        String filePath = "./data/LstAndFndListing.csv";

        csvPostGrabber grabber = new csvPostGrabber(filePath);

        List<Post> posts = grabber.loadPosts();

        System.out.println("Rows parsed: " + posts.size());

        for (int i = 0; i < posts.size(); i++) {
            System.out.println("Post " + (i + 1) + ": " + posts.get(i));
        }
    }
}
