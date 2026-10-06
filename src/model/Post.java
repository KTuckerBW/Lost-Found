package src.model;
import java.util.Map;

public class Post {
    // Initialization
    private String status;
    private String dateOfListing;
    private String desc;
    private String category;
    private String name;
    private String expirationDate;
    private String userID;
    private int productID;
    private String photo;
    private String valueStatus;
    private String location;

    // Default constructor
    private Post(String status, String dateOfListing, String desc, String category, String name, String expirationDate, String userID, int productID, String photo,String valueStatus, String location){
        this.status = status;
        this.dateOfListing = dateOfListing;
        this.desc = desc;
        this.category = category;
        this.name = name;
        this.expirationDate = expirationDate;
        this.userID = userID;
        this.productID = productID;
        this.photo=photo;
        this.valueStatus = valueStatus;
        this.location = location;
    }

    // Constructor to handle the listed data
    private Post(Map<String, Object> row){
       // this.status = String)row.get("status"));
        this.status = (String) row.get("Status");
        this.dateOfListing = (String) row.get("DateOfListing");
        this.desc = (String) row.get("Description");
        this.category = (String) row.get("Category");
        this.name = (String) row.get("ProductName");
        this.expirationDate = (String) row.get("ExpirationDate");
        this.userID = (String) row.get("UserID");
        this.productID = (Integer) row.get("ProductID");
        this.photo= (String) row.get("Photo");
        this.valueStatus = (String) row.get("ValueStatus");
        this.location = (String) row.get("Location");
    }


}
