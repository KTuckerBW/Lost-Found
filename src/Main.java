package src;

import src.auth.CsvUserAuthenticator;
import src.auth.UserAuthenticator;

import src.postings.csvPostGrabber;
import src.model.Post;

import src.ui.controllerFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Main {
    /*
     * Swing is not thread safe. Oracle suggests using invokeLater because it wont block your main thread
     * while you are waiting for your GUI to build. Idk if Dr. Kumari prefers this or new LoginUIClean();
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setFont();

            // Initialize
            UserAuthenticator userAuthenticator = new CsvUserAuthenticator("./data/UserAccounts.csv");
            csvPostGrabber postGrabber = new csvPostGrabber("./data/LstAndFndListing.csv");

            List<Post> posts = postGrabber.loadPosts();
            try {
                new controllerFrame(userAuthenticator, posts);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    // Set font to something more readable
    public static void setFont(){
        Font myFont = new Font("Tahoma", Font.PLAIN, 20);
        UIDefaults defaultUI = UIManager.getDefaults();
        defaultUI.put("Button.font", myFont);
        defaultUI.put("Label.font", myFont);
        defaultUI.put("ComboBox.font", myFont);
        defaultUI.put("TextArea.font", myFont);
    }
}
