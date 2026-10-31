package com.lostandfound.repo;
import com.lostandfound.db.Database;
import com.lostandfound.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DbPostsRepository implements PostRepository{
    private final Database db;
    public DbPostsRepository(Database db) {
        this.db = db;
    }

    // Searches for all posts
    public List<Post> findAll(){
        // Initialization
        String sql = "SELECT * FROM Listings";
        List<Post> posts = new ArrayList<>();

        // Connect to the db
        try (Connection conn = db.getConnection()) {
            // Compile sql
            PreparedStatement ps = conn.prepareStatement(sql);
            // Try to query
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    // Create new post
                    Post post = rowToPost(rs);

                    // Add posts to the list
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }

    // Searches the database for the requested category then returns a list of all the rows
    public List<Post> searchCategory(String category) {
        // Initialization
        String sql = "SELECT * FROM Listings WHERE category = ?";
        List<Post> posts = new ArrayList<>();

        // Try to connect to the db
        try (Connection conn = db.getConnection();
            // Compile sql query
            PreparedStatement ps = conn.prepareStatement(sql)) {
            // Insert the category the user selected to sort by
            ps.setString(1, category);
            // Try to actually query
            try(ResultSet rs = ps.executeQuery()) {
                // While there are still rows to search
                while (rs.next()) {
                    // Create a new post object with the helper method
                    Post post = rowToPost(rs);

                    // Add posts to the list
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }








    // Helper method for turning rows into a Post
    private Post rowToPost(ResultSet rs) throws SQLException{
        // Initialization
        Post post = new Post();

        // Assign attributes
        post.setStatus(rs.getString("status"));
        post.setDateOfListing(rs.getString("date_listed"));
        post.setDesc(rs.getString("description"));
        post.setCategory(rs.getString("category"));
        post.setName(rs.getString("product_name"));
        post.setExpirationDate(rs.getString("expiration_date"));
        post.setUserID(rs.getInt("user_id"));
        post.setProductID(rs.getInt("listing_id"));
        post.setPhoto(rs.getString("photo"));
        post.setValueStatus(rs.getString("value_status"));
        post.setLocation(rs.getString("location"));

        // return final object
        return post;
    }

}
