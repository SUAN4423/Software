package jp.suan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow {
    public static ChatWindow singleton = new ChatWindow();

    public JPanel JP;

    public JPanel Name;

    public JPanel Display;

    public JPanel Chat;
    public JTextArea ChatArea;
    public JScrollPane ChatScroll;
    public JButton ChatSend;

    public ChatWindow() {
        JP = new JPanel();
        JP.setBounds(300, 0, 500, 600);
        JP.setBackground(Color.BLUE);

        Chat = new JPanel();
        Chat.setBounds(0, 500, 500, 100);
        Chat.setBackground(Color.CYAN);
        ChatArea = new JTextArea();
        ChatArea.setLineWrap(true);
        ChatArea.setMargin(new Insets(10, 10, 10, 10));
        ChatArea.setBounds(0, 0, 500, 70);
        ChatArea.setBackground(new Color(0xE0E0E0));
        ChatScroll = new JScrollPane(ChatArea);
        ChatScroll.setBounds(0, 0, 500, 70);
        Chat.add(ChatScroll);
        ChatSend = new JButton();
        ChatSend.setMargin(new Insets(0, 0, 0, 0));
        ChatSend.setText("送信");
        ChatSend.setBounds(400, 70, 100, 30);
        ChatSend.addActionListener(new ButtonListener());
        Chat.add(ChatSend);
        JP.add(Chat);

        Display = new JPanel();
        Display.setBounds(0, 50, 500, 480);
        Display.setBackground(Color.ORANGE);
        JP.add(Display);

        Name = new JPanel();
        Name.setBounds(0, 0, 500, 50);
        Name.setBackground(new Color(0xC0FFEE));
        JP.add(Name);
    }

    public void setWindow() {
        Window.singleton.add(this.JP);
    }

    public void resetWindowSize() {
        JP.setSize(Window.singleton.getWidth() - 300 - Window.singleton.getWidthBorder(), Window.singleton.getHeight() - Window.singleton.getHeightBorder());
        Chat.setBounds(0, JP.getHeight() - 100, JP.getWidth(), 100);
        ChatArea.setBounds(0, 0, Chat.getWidth(), Chat.getHeight() - 30);
        ChatScroll.setBounds(0, 0, Chat.getWidth(), Chat.getHeight() - 30);
        ChatSend.setBounds(Chat.getWidth() - 100, Chat.getHeight() - 30, 100, 30);
        Display.setBounds(0, 50, JP.getWidth(), JP.getHeight() - 150);
        Name.setBounds(0, 0, JP.getWidth(), 50);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
