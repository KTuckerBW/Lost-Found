package src.ui;

import  src.auth.UserAuthenticator;
import src.model.Post;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class controllerFrame extends JFrame{
        public final String login = "LOGIN";
        public final String browsing = "BROWSING";
        private CardLayout cardLayout;
        private JPanel mainPanel;

        public controllerFrame(UserAuthenticator userAuthenticator, List<Post> posts) throws IOException{
            // Initialization
            cardLayout = new CardLayout();
            mainPanel = new JPanel((cardLayout));

            setTitle("Lst&Fnd");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setMinimumSize(new Dimension(500,500));


            // Passing this instance of the controller frame to the login class so they may talk to each other
            // This initializes an instance of the login screen
            LoginUI loginPanel = new LoginUI(this, userAuthenticator);
            BrowseListings browsingPanel = new BrowseListings(this, posts);

            // Add our cards to the controller
            mainPanel.add(loginPanel, login);
            mainPanel.add(browsingPanel, browsing);


        }
}
