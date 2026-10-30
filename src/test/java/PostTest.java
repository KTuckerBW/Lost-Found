import com.lostandfound.db.Database;
import com.lostandfound.db.DatabaseInitializer;
import com.lostandfound.model.Post;
import com.lostandfound.repo.DbPostsRepository;
import com.lostandfound.ui.PostComponent;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PostTest {
    public static void main(String[] args) {
        // Try to open the database
        try {
            // Initialization
            Database db = Database.production();
            DatabaseInitializer.initialize(db);

            DbPostsRepository repo = new DbPostsRepository(db);

            // Test the findAll() then test the category
            List<Post> allPosts = repo.findAll();
            List<Post> searchPosts = repo.searchCategory("Technology");


            // Is there any data at all?
            if(allPosts.isEmpty()){
                System.out.println("NO DATA :(");
                return;
            }

            // Is category working?
            if(searchPosts.isEmpty()){
                System.out.println("Search no work");
            }

            // Create an invokeLater to test postsComp
            SwingUtilities.invokeLater(() -> {
                // Initialization
                JFrame testFrame = new JFrame("Post Test");
                JPanel cardHolder = new JPanel();
                JScrollPane scrollPane = new JScrollPane(cardHolder);

                // Config
                testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                testFrame.setContentPane(scrollPane);
                testFrame.setSize(500, 600);
                testFrame.setLocationRelativeTo(null);

                // Stack everything vertically just to see it
                cardHolder.setLayout(new BoxLayout(cardHolder, BoxLayout.Y_AXIS));
                cardHolder.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // put the components into place
                for (Post post : allPosts) {
                    // create comps and add them
                    cardHolder.add(new PostComponent(post));

                    // padding
                    cardHolder.add(Box.createRigidArea(new Dimension(0, 10)));

                }

                // Set visible
                testFrame.setVisible(true);
            });

        } catch (SQLException e) {
            System.err.println("Failure!");
            e.printStackTrace();
        }
    }
}
