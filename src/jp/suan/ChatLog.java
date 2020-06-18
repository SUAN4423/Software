package jp.suan;

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
    public ArrayList<JLabel> Messages = new ArrayList<>();
    public Font chatFont;

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

    public void selectedResize(){
        JPWindow_Chat.setBounds(0, 0, ChatWindow.singleton.getDisplayWidth(ChatWindow.singleton.JP.getWidth()), ChatWindow.singleton.Display.getHeight());
        for (int i = 0; i < Messages.size(); i++) {
            Messages.get(i).setBounds(0, i*60, JPWindow_UserSelect.getWidth(), 60);
        }
    }

    public void IPReflesh() {
        IPAddress.setText("Address : " + this.Address);
    }

    public void addedMessage() {
        for (int i = 0; i < Messages.size(); i++) {
            Messages.get(i).setFont(chatFont);
            Messages.get(i).setBounds(0, i*60, JPWindow_UserSelect.getWidth(), 60);
        }
        JPWindow_Chat.repaint();
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
