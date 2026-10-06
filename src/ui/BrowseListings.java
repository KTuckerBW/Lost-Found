package src.ui;

import javax.swing.*;
import java.awt.*;

public class BrowseListings {

    public static void main(String[] args) {

        JFrame frame = new JFrame("     Campus Lost & Found");

        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Campus Lost & Found");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        // Item 1
        JLabel item1 = new JLabel(
                "<html><b>Blue Water Bottle</b><br>" +
                        "Location: Science Building - 2nd Floor<br>" +
                        "Description: Blue bottle with a university sticker.</html>"
        );

        JButton button1 = new JButton("View Details");

        panel.add(item1);
        panel.add(button1);
        panel.add(Box.createVerticalStrut(20));

        // Item 2
        JLabel item2 = new JLabel(
                "<html><b>Black Backpack</b><br>" +
                        "Location: Library - 1st Floor<br>" +
                        "Description: Black backpack found near the study area.</html>"
        );

        JButton button2 = new JButton("View Details");

        panel.add(item2);
        panel.add(button2);
        panel.add(Box.createVerticalStrut(20));

        // Item 3
        JLabel item3 = new JLabel(
                "<html><b>Set of Keys</b><br>" +
                        "Location: Student Center - 3rd Floor<br>" +
                        "Description: Small set of keys with a red keychain.</html>"
        );

        JButton button3 = new JButton("View Details");

        panel.add(item3);
        panel.add(button3);

        JScrollPane scrollPane = new JScrollPane(panel);

        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
