package jp.suan;

import javax.swing.*;
import java.awt.*;

public class ChatWindow {
    public static ChatWindow singleton = new ChatWindow();

    public JPanel JP;

    public ChatWindow() {
        JP = new JPanel();
        JP.setBounds(Window.singleton.border.left + 300, Window.singleton.border.top, 500, 600);
        JP.setBackground(Color.BLUE);
    }

    public void setWindow() {
        Window.singleton.add(this.JP);
    }

    public void resetWindowSize() {
        JP.setSize(Window.singleton.getWidth() - 300 - Window.singleton.getWidthBorder(), Window.singleton.getHeight() - Window.singleton.getHeightBorder());
    }
}
