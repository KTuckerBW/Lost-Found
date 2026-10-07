package src.ui; /**
 * Kaitlyn Tucker
 * Comp390
 * Login screen without database functionality. Enter button doesn't work.
 */
import src.ui.BrowseListings;
import src.auth.UserAuthenticator;
import src.model.User;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginUI extends JFrame implements ActionListener {
    private JButton login;
    private JPanel panel; // the panel will hold all the components
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel feedbackLabel;
    private JLabel titleLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserAuthenticator userAuthenticator;

    public LoginUI(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
        setTitle("Lst&Fnd");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();
        add(panel);
        pack(); // pack allows dynamic resizing of components depending on the size of the window

        setLocationRelativeTo(null); // will center the window
        setVisible(true);
    }

    /*
     * Creates a new constraints object for each component so layout settings do not carry
     * over from one component to the next. Much easier to add them to panel
     */
    private GridBagConstraints constraints(
            int column,
            int row,
            int width,
            int fill,
            double weightx,
            int anchor
    ) {
        GridBagConstraints gbc = new GridBagConstraints();
        // Add 5 empty pixels as padding around every component
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = column;
        gbc.gridy = row;
        gbc.gridwidth = width;
        gbc.fill = fill; // determines if and where a component can expand into extra space
        gbc.weightx = weightx; // determines how components share extra space if multiple components can expand into the same space
        gbc.anchor = anchor; // determines component alignment within its own cell

        return gbc;
    }

    private void buildPanel() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel = new JLabel("Login to Lst&Fnd!");

        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(25); // spans 15 columns wide

        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(25);

        login = new JButton("Log In");
        login.addActionListener(this);

        // Start with a blank label that is visible but the bg is not opaque so the user can't see it
        // It still reserves space so that the window wont resize when the banner appears and disappears
        feedbackLabel = new JLabel(" ", SwingConstants.CENTER);
        feedbackLabel.setForeground(Color.WHITE);
        feedbackLabel.setOpaque(false);

        // Title, login button, and feedback label span both columns.
        panel.add(titleLabel, constraints(0, 0, 2, GridBagConstraints.NONE, 0, GridBagConstraints.CENTER));
        panel.add(login, constraints(0, 3, 2, GridBagConstraints.NONE, 0, GridBagConstraints.CENTER));
        panel.add(feedbackLabel, constraints(0, 4, 2, GridBagConstraints.HORIZONTAL, 1, GridBagConstraints.CENTER));

        // The text fields consumes any extra horizontal space in their row
        panel.add(usernameField, constraints(1, 1, 1, GridBagConstraints.HORIZONTAL, 1, GridBagConstraints.LINE_START));
        panel.add(passwordField, constraints(1, 2, 1, GridBagConstraints.HORIZONTAL, 1, GridBagConstraints.LINE_START));

        // Rest of the components
        panel.add(usernameLabel, constraints(0, 1, 1, GridBagConstraints.NONE, 0, GridBagConstraints.LINE_END));
        panel.add(passwordLabel, constraints(0, 2, 1, GridBagConstraints.NONE, 0, GridBagConstraints.LINE_END));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();

        // grab the password. But since it's swings password field it returns as an array of chars
        // we automatically convert it to a string to test
        String typedPassword = new String(passwordField.getPassword());

        // Authenticate users regardless of the data origin: file or database
        User user = this.userAuthenticator.authenticate(username, typedPassword);
        if (user != null) {
            // Open a new window and close this one (WIP FEATURE)

            // But for now just display a success window
            feedbackLabel.setText("Login successful! :)");
            // this is where my BrowseListings page windows should open up
            BrowseListings.main(new String[]{}); // JUST CHANGED: (NINA) Redirects the user to the Browse Listings page after successful authentication
            feedbackLabel.setBackground(Color.GREEN);

            usernameField.setText("");

        } else {
            // Alert the user of their mistake
            feedbackLabel.setText("Username or password is incorrect!");
            feedbackLabel.setBackground(Color.RED);
        }

        // overwrite sensitive data and expose the banner
        typedPassword = "";
        feedbackLabel.setOpaque(true);
        feedbackTimer();

        // Keep the username after a failed attempt, clear the password
        passwordField.setText("");
    }

    /*
     * Helper method to hide the feedback banner after three seconds
     */
    private void feedbackTimer() {
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent timerEvent) {
                feedbackLabel.setText(" ");
                feedbackLabel.setOpaque(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

//    /*
//     * Handles login attempts and updates the feedback banner.
//     */
//    private class LoginButtonListener {
//        private AuthService authService;
//
//        public void actionPerformed(ActionEvent e) {
//
//        }


//    }

}