package jp.suan;

import jp.suan.network.Message;
import sun.font.FontDesignMetrics;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class ChatLog {
    public String UserName;
    public String Address;

    public JPanel JPWindow_UserSelect;
    public long latestUpdate = System.currentTimeMillis();
    public JLabel Name;
    public JLabel IPAddress;

    public JPanel JPWindow_Chat;
    public ArrayList<Text> Messages = new ArrayList<>();
    public Font chatFont;
    public int LineHeight;

    protected ChatLog MY = this;

    public int Index;

    public ChatLog(String UName, String Domain, int Index) {
        this.UserName = UName;
        this.Address = Domain;
        this.Index = Index;
        JPWindow_UserSelect = new JPanel();
        JPWindow_UserSelect.setLayout(null);
        JPWindow_UserSelect.setBounds(0, Index * 60, UserSelectWindow.singleton.getUserWidth(), 60);
        JPWindow_UserSelect.setBackground(new Color(new Random().nextInt(0x1000000)));
        JPWindow_UserSelect.setBorder(new LineBorder(Color.GRAY, 1));
        Name = new JLabel("Name : " + this.UserName);
        Name.setBounds(0, 0, JPWindow_UserSelect.getWidth(), 30);
        IPAddress = new JLabel("Address : " + this.Address);
        IPAddress.setBounds(0, 30, JPWindow_UserSelect.getWidth(), 30);
        JPWindow_UserSelect.add(Name);
        JPWindow_UserSelect.add(IPAddress);
        Name.addMouseListener(new SelectListener());
        IPAddress.addMouseListener(new SelectListener());
        JPWindow_UserSelect.addMouseListener(new SelectListener());

        JPWindow_Chat = new JPanel();
        JPWindow_Chat.setLayout(null);
        JPWindow_Chat.setBounds(0, 0, 500, 480);
        JPWindow_Chat.setBackground(Color.WHITE);
        chatFont = new Font(Font.DIALOG, Font.PLAIN, 20);
    }

    public void resize() {
        JPWindow_UserSelect.setBounds(0, Index * 60, UserSelectWindow.singleton.getUserWidth(), 60);
        Name.setBounds(0, 0, JPWindow_UserSelect.getWidth(), 30);
        IPAddress.setBounds(0, 30, JPWindow_UserSelect.getWidth(), 30);
    }

    public void selectedResize() {
        this.MessagesSize();
    }

    public void IPReflesh() {
        IPAddress.setText("Address : " + this.Address);
    }

    public void addedMessage() {
        this.MessagesSize();
        JPWindow_Chat.repaint();
    }

    public void MessagesSize() {
        FontMetrics fm = null;
        int height = 0;
        if (Messages.size() > 0) {
            fm = FontDesignMetrics.getMetrics(Messages.get(0).JArea.getFont());
            height = fm.getHeight();
            LineHeight = 0;
        }
        for (int i = 0; i < Messages.size(); i++) {
            String[] str = Messages.get(i).JArea.getText().split("\r\n|\r|\n", -1);
            int Line = 0;
            for (int j = 0; j < str.length; j++) {
                int width = fm.stringWidth(str[j]);
                Line += width / (ChatWindow.singleton.getDisplayWidth(ChatWindow.singleton.JP.getWidth()) - 60) + (width % (ChatWindow.singleton.getDisplayWidth(ChatWindow.singleton.JP.getWidth()) - 60) == 0 ? 0 : 1);
            }
            if (Messages.get(i).Me) {
                Messages.get(i).JArea.setBounds(60, LineHeight, ChatWindow.singleton.getDisplayWidth(ChatWindow.singleton.JP.getWidth()) - 60, height * Line);
                LineHeight += height * Line;
            } else {
                Messages.get(i).JArea.setBounds(0, LineHeight, ChatWindow.singleton.getDisplayWidth(ChatWindow.singleton.JP.getWidth()) - 60, height * Line);
                LineHeight += height * Line;
            }
        }
        JPWindow_Chat.setBounds(0, 0, ChatWindow.singleton.getDisplayWidth(ChatWindow.singleton.JP.getWidth()), LineHeight);
    }

    public class SelectListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Window.singleton.nowSelected = MY;
            ChatWindow.singleton.reset();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}
