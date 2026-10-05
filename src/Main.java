package src;

import src.auth.AuthService;
import src.auth.FileAuthService;
import src.model.User;
import src.ui.LoginUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    /*
     * Swing is not thread safe. Oracle suggests using invokeLater because it wont block your main thread
     * while you are waiting for your GUI to build. Idk if Dr. Kumari prefers this or new LoginUIClean();
     */
    public static void main(String[] args) {

        setFont();

        AuthService authService = new FileAuthService("./data/UserAccounts.csv");
        SwingUtilities.invokeLater(() -> new LoginUI(authService));

//        SwingUtilities.invokeLater(LoginUI::new);

        User user = authService.authenticate("michael", "dontleavemehere");
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
