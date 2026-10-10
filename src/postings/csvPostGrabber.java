package src.postings;

import src.model.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

// Adding a CSV lib to handle long descriptions
// You might have to download the jar for it.
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class csvPostGrabber {
    // Initialization
    private final String filePath;
    public List<Post> posts = new ArrayList<>();

    // Makes parsing less annoying
    private static final List<String> postColumns = List.of(
            "UserID",
            "ProductName",
            "Photo",
            "Description",
            "Location",
            "Category",
            "Status",
            "DateOfListing",
            "ExpirationDate",
            "ValueStatus"
    );

    public csvPostGrabber(String filePath) {
        this.filePath = filePath;
    }

    // Load posts into an array to use later
    public List<Post> loadPosts() {
        // Initialization
        List<Post> posts = new ArrayList<>();

        // Look inside the posts file
        try (
                // Settings I found online to use this lib
                // Parses the filepath passed through
                Reader reader = new FileReader(filePath);
                CSVParser csvParser = CSVFormat.DEFAULT.builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .setTrim(true)
                        .get()
                        .parse(reader)
                )
        {
            // For all the lines inside the data file
            for(CSVRecord record: csvParser){
                // Parse the data and turn it into posts and add it to the array
                // Done more easily by skipping the hash map stuff
                posts.add(toPost(record));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    // Less annoying helper method
   private Post toPost(CSVRecord record){
       return Post.of(
               record.get("Status"),
               record.get("DateOfListing"),
               record.get("Description"),
               record.get("Category"),
               record.get("ProductName"),
               record.get("ExpirationDate"),
               record.get("UserID"),
               Integer.parseInt(record.get("ProductID")),
               record.get("Photo"),
               record.get("ValueStatus"),
               record.get("Location")
       );
   }


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
