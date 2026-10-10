package src.ui;

import src.model.Post;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostComponent extends JPanel {
    // Main components
    private final int previewLength = 30;
    private  Font myFont = new Font("Tahoma", Font.PLAIN, 20);
    private Post post;
    private final JTextArea descriptionArea = new JTextArea();
    private final JButton more = new JButton("More...");
    private String description;
    private boolean expanded = false;

    public PostComponent(Post post){
        this.post  = post;
        this.description = post.getDesc();
        buildComponent();
    }

    private void buildComponent(){
        // Initialization
        JLabel nameLabel = new JLabel(post.getName());
        JLabel locationLabel = new JLabel(post.getLocation());
        JLabel categoryLabel = new JLabel(post.getCategory());

        // Since the class itself expands JPanel, we can call JPanel methods directly
        // to build the main panel holding all the different parts of a post card
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        // A compound border allows us to make an outline around the post card and add padding
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        // The description area var allows us to add the more button functionality
        // But since it isnt really meant to be used as a label you have to disable
        // all the user interaction functionality
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true); // It wont wrap lines in the middle of words
        descriptionArea.setOpaque(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder());
        descriptionArea.setColumns(16); // Forces a minimum width but not a minimum length to look prettier
        descriptionArea.setText(shorten(description, previewLength)); // preview desc


        // assign fonts, name is meant to be more noticeable
        nameLabel.setFont(myFont.deriveFont(Font.BOLD, 16f));
        locationLabel.setFont(myFont);
        descriptionArea.setFont(myFont);
        more.setFont(myFont);

        // assign a listener to the more button but code the functionality later
        more.addActionListener(event -> toggleDesc());

        // If Nina carries over the dynamic sizing thing from the other GUI then we can delete this
        // In the meantime, preserve space for the button component on cards without a more button
        if(description.length() <= previewLength){
            more.setText(" ");
            more.setEnabled(false);
            more.setBorderPainted(false);
            more.setContentAreaFilled(false);
        }

        // add Name on top
        add(nameLabel,
                constraints(0,0,1,0,GridBagConstraints.NONE,GridBagConstraints.LINE_START));
        // add category next to name
        add(categoryLabel,
                constraints(1,0,1,0,GridBagConstraints.NONE,GridBagConstraints.LINE_START));
        // add description below name
        add(descriptionArea,
                constraints(0,1,2,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER));
        add(locationLabel,
                constraints(0,2,1,0, GridBagConstraints.NONE, GridBagConstraints.LINE_START));
        add(more,
                constraints(0,3,1,0,GridBagConstraints.NONE, GridBagConstraints.LINE_START));
    }

    // Helper method stolen from another one of my projects
    private String shorten(String text, int maxLength){
        // default case
        if(text.length()<=maxLength){
            return text;
        }
        return text.substring(0,maxLength) + "...";
    }

    // Helper method stolen from this project somewhere else. Swapped where weightx
    private GridBagConstraints constraints(
            int column,
            int row,
            int width,
            float weightx,
            int fill,
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

    private void toggleDesc(){
        setExpanded(!expanded);

    }
    public void setExpanded(Boolean expanded){
        // Initialization
        this.expanded = expanded;


        if(expanded){
            // Expand the desc by setting the visible description to be equal to the full desc
            descriptionArea.setText(description);
            // rewrite the more button
            more.setText("Less...");
        }else{
            // UnExpand the desc by setting the area to the shortened version
            descriptionArea.setText(shorten(description,previewLength));
            // rewrite the more button
            more.setText("More...");
        }

        // regenerate the component
        revalidate();
        repaint();
    }
    // Test display
    public static void test(){
        // Singular test post (can be added to all our data later)
        Post testPost = Post.of(
                "Unclaimed",
                "2026-09-28",
                "Black Hydroflask with a silver lid and a small dent near the bottom.",
                "Accessories",
                "Water Bottle",
                "2026-10-28",
                "DMFAdmin",
                101,
                "hydroflask.jpg",
                "Standard",
                "DMF"
        );
        // singular frame just to display this component
        JFrame frame = new JFrame("Post Component Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel made just to display this component and its parts
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(new PostComponent(testPost), BorderLayout.CENTER);

        // Hard coded size, location is centering, and visibility is only required here
        // because there is no "main panel" which holds all the components. In the GUI the
        // main panel would be set to visible
        frame.setContentPane(container);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Test main
    public static void main(String[] args) {
        // Invoke later from Oracle
       // SwingUtilities.invokeLater(PostComponent::test);
     }
}
