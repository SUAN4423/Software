package jp.suan;

import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
    }

    public void resize() {
        JPWindow_UserSelect.setBounds(0, Index * 60, UserSelectWindow.singleton.getUserWidth(), 60);
        Name.setBounds(0, 0, JPWindow_UserSelect.getWidth(), 30);
        IPAddress.setBounds(0, 30, JPWindow_UserSelect.getWidth(), 30);
    }
}
