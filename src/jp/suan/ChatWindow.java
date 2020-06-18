package jp.suan;

import jp.suan.layout.MyLayout;
import jp.suan.network.Message;
import jp.suan.network.Send;
import sun.font.FontDesignMetrics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow {
    public static ChatWindow singleton = new ChatWindow();

    public JPanel JP;

    public JPanel Name;
    public JLabel NameDisplay;

    public JPanel Display;
    public JScrollPane DisplayScroll;
    public int DisplayWidth = 0;

    public int getDisplayWidth(int JPWidth) {
        this.DisplayWidth = JPWidth - DisplayScroll.getVerticalScrollBar().getWidth() - DisplayScroll.getInsets().left - DisplayScroll.getInsets().right - DisplayScroll.getVerticalScrollBar().getInsets().left - DisplayScroll.getVerticalScrollBar().getInsets().right;
        return DisplayWidth;
    }

    public JPanel Chat;
    public JTextArea ChatArea;
    public JScrollPane ChatScroll;
    public JButton ChatSend;

    public boolean messagesending[] = {false, false, false};

    public ChatWindow() {
        JP = new JPanel();
        JP.setLayout(null);
        JP.setBounds(300, 0, 500, 600);
        JP.setBackground(Color.BLUE);

        Chat = new JPanel();
        Chat.setLayout(null);
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
        Display.setLayout(new MyLayout(Display));
        Display.setBounds(0, 50, 500, 480);
        Display.setBackground(Color.ORANGE);
        DisplayScroll = new JScrollPane(Display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DisplayScroll.setBounds(0, 50, 500, 480);
        //JP.add(Display);
        JP.add(DisplayScroll);

        Name = new JPanel();
        Name.setLayout(null);
        Name.setBounds(0, 0, 500, 50);
        Name.setBackground(new Color(0xC0FFEE));
        NameDisplay = new JLabel("null");
        Font font = null;
        for (int i = 1; ; i++) {
            font = new Font(Font.DIALOG, Font.PLAIN, i);
            FontMetrics fontMetrics = FontDesignMetrics.getMetrics(font);
            int height = fontMetrics.getHeight();
            if (height > 50) {
                font = new Font(Font.DIALOG, Font.PLAIN, i - 1);
                break;
            }
        }
        NameDisplay.setFont(font);
        NameDisplay.setBounds(0, 0, 500, 50);
        NameDisplay.setHorizontalAlignment(JLabel.CENTER);
        NameDisplay.setVerticalAlignment(JLabel.CENTER);
        Name.add(NameDisplay);
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
        if (Window.singleton.nowSelected == null)
            Display.setBounds(0, 50, getDisplayWidth(JP.getWidth()), JP.getHeight() - 150);
        else {
            Display.setBounds(0, 50, getDisplayWidth(JP.getWidth()), Math.max(JP.getHeight() - 150, Window.singleton.nowSelected.JPWindow_Chat.getHeight()));
            Window.singleton.nowSelected.selectedResize();
        }
        DisplayScroll.setBounds(0, 50, JP.getWidth(), JP.getHeight() - 150);
        Name.setBounds(0, 0, JP.getWidth(), 50);
        NameDisplay.setBounds(0, 0, JP.getWidth(), 50);
    }

    public void reset() {
        NameDisplay.setText(Window.singleton.nowSelected.UserName);
        Display.removeAll();
        Display.add(Window.singleton.nowSelected.JPWindow_Chat);
        Display.repaint();
    }

    public void sendfinish() {
        if (!messagesending[0]) {
            JOptionPane.showMessageDialog(Window.singleton, "メッセージの送信に失敗しました。", "エラー", JOptionPane.ERROR_MESSAGE);
        } else {
            JLabel jl = new JLabel(ChatArea.getText());
            jl.setHorizontalAlignment(JLabel.RIGHT);
            Window.singleton.nowSelected.Messages.add(jl);
            Window.singleton.nowSelected.JPWindow_Chat.add(jl);
            Window.singleton.nowSelected.addedMessage();
            ChatArea.setText("");
        }
        messagesending[2] = false;
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Window.singleton.nowSelected == null) {
                JOptionPane.showMessageDialog(Window.singleton, "送信先を選択してください。", "エラー", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!messagesending[2]) {
                    Message sendmessage = new Message(ChatArea.getText(), Window.singleton.nowSelected.Address);
                    messagesending[1] = true;
                    messagesending[2] = true;
                    Send send = new Send(sendmessage, messagesending);
                    send.run();
                }
            }
        }
    }
}
