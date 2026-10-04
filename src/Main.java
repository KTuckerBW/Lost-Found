package src;

import src.ui.LoginUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    /*
     * Swing is not thread safe. Oracle suggests using invokeLater because it wont block your main thread
     * while you are waiting for your GUI to build. Idk if Dr. Kumari prefers this or new LoginUIClean();
     */
    public static void main(String[] args) {
        Font myFont = new Font("Tahoma", Font.PLAIN, 20);
        UIDefaults defaultUI = UIManager.getDefaults();
        defaultUI.put("Button.font", myFont);
        defaultUI.put("Label.font", myFont);
        defaultUI.put("ComboBox.font", myFont);
        defaultUI.put("TextArea.font", myFont);

        SwingUtilities.invokeLater(LoginUI::new);
    }
}
