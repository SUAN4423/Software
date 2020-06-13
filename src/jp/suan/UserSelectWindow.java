package jp.suan;

import javax.swing.*;
import java.awt.*;

public class UserSelectWindow {
    public static UserSelectWindow singleton = new UserSelectWindow();

    public JPanel JP;

    public UserSelectWindow() {
        JP = new JPanel();
        JP.setBounds(Window.singleton.border.left, Window.singleton.border.top, 300, 600);
        JP.setBackground(Color.RED);
    }

    public void setWindow() {
        Window.singleton.add(this.JP);
    }

    public void resetWindowSize() {
        JP.setSize(300, Window.singleton.getHeight() - Window.singleton.getHeightBorder());
    }
}
