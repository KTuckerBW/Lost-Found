package com.lostandfound.ui;// Nina Aubourg
// Comp 390
// Browse listings page where users can browse their lost and found items

import com.lostandfound.model.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BrowseListings extends JPanel {
    private final ControllerFrame controller;
    private final JPanel searchPanel;
    private final Object[] data = null;
    // DefaultListModel<String> listModel = new DefaultListModel<>();
    private JPanel listModel;
    private Font myFont = new Font("Tahoma", Font.PLAIN, 20);
    private JPanel listingsPanel;
    private JScrollPane scrollPane;

    private JButton logout;
    private JButton electronicsButton;
    private JButton accessoriesButton;
    private JButton clothingsButton;
    private JButton bagsButton;
    private JButton allListingsButton;

    // private JList categories;



    // Turning BrowseListings into a panel to work with controller Frame
    public BrowseListings(ControllerFrame controller, List<Post> posts){
        // Initialization
        this.controller = controller;
        listingsPanel = new JPanel(new GridLayout(0,3, 10, 10)); // allows dynamic sizing with auto sorting, 3 across and infinite down
        listModel = new JPanel(new GridLayout(5,1,10,10));
        scrollPane = new JScrollPane(listingsPanel);

        logout = new JButton("Log Out");
        accessoriesButton = new JButton("Accessories");
        electronicsButton = new JButton("Electronics");
        clothingsButton = new JButton("Clothing");
        bagsButton = new JButton("Bags");
        allListingsButton = new JButton("All Listings");

        accessoriesButton.addActionListener(event -> {
            List<Post> filteredPosts = posts.stream()
                    .filter(post -> post.getCategory() != null
                            && post.getCategory().equalsIgnoreCase("Accessories"))
                    .toList();

            setPosts(filteredPosts);
        });

        electronicsButton.addActionListener(event -> {
            List<Post> filteredPosts = posts.stream()
                    .filter(post -> post.getCategory() != null
                            && post.getCategory().equalsIgnoreCase("Electronics"))
                    .toList();

            setPosts(filteredPosts);
        });

        clothingsButton.addActionListener(event -> {
            List<Post> filteredPosts = posts.stream()
                    .filter(post -> post.getCategory() != null
                            && post.getCategory().equalsIgnoreCase("Clothing"))
                    .toList();

            setPosts(filteredPosts);
        });

        bagsButton.addActionListener(event -> {
            List<Post> filteredPosts = posts.stream()
                    .filter(post -> post.getCategory() != null
                            && post.getCategory().equalsIgnoreCase("Bags"))
                    .toList();

            setPosts(filteredPosts);
        });

        allListingsButton.addActionListener(event ->setPosts(posts));



        searchPanel = new JPanel(new GridLayout(5,1,10,10));




        // listModel.addElement("Accessories");
        // listModel.addElement("Electronics");
        // listModel.addElement("Clothing");
        // listModel.addElement("Bags");

        listModel.add(accessoriesButton, constraints(0,0,1,0, GridBagConstraints.NONE, GridBagConstraints.LINE_START));
        listModel.add(electronicsButton, constraints(0,1,1,0, GridBagConstraints.NONE, GridBagConstraints.LINE_START));
        listModel.add(clothingsButton, constraints(0,2,1,0, GridBagConstraints.NONE, GridBagConstraints.LINE_START));
        listModel.add(bagsButton, constraints(0,3,1,0, GridBagConstraints.NONE, GridBagConstraints.LINE_START));
        listModel.add(allListingsButton, constraints(0, 4,1,0, GridBagConstraints.NONE, GridBagConstraints.LINE_START));
        // categories = new JList(listModel);

        // Action Listener to switch panels
        logout.addActionListener(event -> {
            controller.showLogin();
        });

        // action Listener for Accessories


        JLabel title = new JLabel("Campus Lost & Found");
        JPanel footerPanel = new JPanel(new GridBagLayout());

        // Further set up of main panel
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Further set up of parts
        listingsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10, 10));

        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Fix the cooked scrolling
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(25);

        // Fonts
        title.setFont(myFont.deriveFont(Font.BOLD, 24));
        logout.setFont(myFont);
        // categories.setFont(myFont);





        // add logout to the footer panel
        // Footer panel forces logout to be at the far right even if the cell its in is weirdly sized
        footerPanel.add(logout, constraints(0,0,1,1,GridBagConstraints.NONE, GridBagConstraints.LINE_END));
//        searchPanel.add(categories);
        searchPanel.add(listModel);

        // Add everything to the main panel
        add(title, BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.WEST);

        // also kinda an adding stuff
        setPosts(posts);
    }

    public void setPosts(List<Post> posts){
        // Initialization
        JLabel emptyLabel = new JLabel("No listings are available");
        emptyLabel.setFont(myFont);

        // Delete everything if there is anything, will be useful later with add/deleting
        listingsPanel.removeAll();

        // If there are no posts, provide feedback. Will also be useful later
        if(posts.isEmpty()){
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            listingsPanel.add(emptyLabel, constraints(0,0,1,1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START));
        }else{
            for(Post post: posts){
                // add all the new components. Constraints set on listing earlier forces organization
                listingsPanel.add(new PostComponent(post));
            }
        }

        // update page
        listingsPanel.revalidate();
        listingsPanel.repaint();
    }

    // Helper method stolen from postComponents
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
}
