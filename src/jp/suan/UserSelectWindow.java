package jp.suan;

import jp.suan.layout.MyLayout;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.xml.ws.soap.Addressing;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSelectWindow {
    public static UserSelectWindow singleton = new UserSelectWindow();

    public JPanel JP;

    public JPanel YourName;
    public JTextField Name;

    public JPanel IPAddress;
    public JButton IPButton;
    public JTextField IPField;

    public JPanel User;
    public JScrollPane UserScroll;
    public ArrayList<ChatLog> UserList = new ArrayList<>();
    public int UserWidth = 0;

    public int getUserWidth() {
        if (this.UserScroll.getHorizontalScrollBar().isShowing()) {
            this.UserWidth = 300 - UserScroll.getVerticalScrollBar().getWidth() - UserScroll.getInsets().left - UserScroll.getInsets().right - UserScroll.getVerticalScrollBar().getInsets().left - UserScroll.getVerticalScrollBar().getInsets().right;
            System.out.println(this.UserWidth + " SCROLL");
        } else if (UserWidth == 0) {
            this.UserWidth = 300 - UserScroll.getVerticalScrollBar().getWidth() - UserScroll.getInsets().left - UserScroll.getInsets().right - UserScroll.getVerticalScrollBar().getInsets().left - UserScroll.getVerticalScrollBar().getInsets().right;
            System.out.println(this.UserWidth + " 0");
        }
        return UserWidth;
    }

    public UserSelectWindow() {
        this.JP = new JPanel();
        JP.setLayout(null);
        this.JP.setBounds(0, 0, 300, 600);
        this.JP.setBackground(Color.RED);

        this.YourName = new JPanel();
        this.YourName.setLayout(null);
        this.YourName.setBounds(0, 0, 300, 30);
        this.YourName.setBackground(new Color(0x95FAE3));
        this.Name = new JTextField("ユーザーネームを入力");
        this.Name.setBounds(0, 0, 300, 30);
        this.Name.setHorizontalAlignment(JTextField.CENTER);
        this.Name.addFocusListener(new GrayText(this.Name));
        this.YourName.add(this.Name);
        this.JP.add(this.YourName);

        this.IPAddress = new JPanel();
        this.IPAddress.setLayout(null);
        this.IPAddress.setBounds(0, 30, 300, 30);
        this.IPAddress.setBackground(Color.YELLOW);
        this.IPButton = new JButton();
        this.IPButton.setMargin(new Insets(0, 0, 0, 0));
        this.IPButton.setText("会話開始");
        this.IPButton.addActionListener(new ButtonListener());
        this.IPButton.setBounds(200, 0, 100, 30);
        this.IPAddress.add(this.IPButton);
        this.IPField = new JTextField("アドレスを入力");
        this.IPField.setBounds(0, 0, 200, 30);
        this.IPField.setHorizontalAlignment(JTextField.CENTER);
        this.IPField.addFocusListener(new GrayText(this.IPField));
        this.IPField.addActionListener(new EnterListener());
        this.IPAddress.add(this.IPField);
        this.JP.add(this.IPAddress);

        this.User = new JPanel();
        this.User.setLayout(new MyLayout(this.User));
        this.User.setBackground(Color.MAGENTA);
        this.User.setBounds(0, 0, 300, 540);
        this.UserScroll = new JScrollPane(this.User, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.UserScroll.setBounds(0, 60, 300, 540);
        this.UserScroll.getVerticalScrollBar().setUnitIncrement(30);
        this.JP.add(this.UserScroll);
    }

    public void setWindow() {
        Window.singleton.add(this.JP);
    }

    public void resetWindowSize() {
        this.JP.setSize(300, Window.singleton.getHeight() - Window.singleton.getHeightBorder());
        this.YourName.setBounds(0, 0, 300, 30);
        this.Name.setBounds(0, 0, 300, 30);
        this.IPAddress.setBounds(0, 30, 300, 30);
        this.IPButton.setBounds(200, 0, 100, 30);
        this.IPField.setBounds(0, 0, 200, 30);
        this.User.setBounds(0, 0, this.getUserWidth(), Math.max(this.JP.getHeight() - 60, UserList.size() * 60));
        this.UserScroll.setBounds(0, 60, 300, this.JP.getHeight() - 60);
        for (int i = 0; i < UserList.size(); i++) {
            UserList.get(i).resize();
        }
    }

    public void AddAddress() {
        Pattern p = Pattern.compile("^[0-9a-zA-Z.]*$");
        Matcher m = p.matcher(IPField.getText());
        if(!m.find()){
            JOptionPane.showMessageDialog(Window.singleton, "アドレスを入力してください。", "エラー", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ChatLog add = new ChatLog("TEST", IPField.getText(), UserList.size());
        UserList.add(add);
        User.add(UserList.get(UserList.size() - 1).JPWindow_UserSelect);
        User.setBounds(0, 0, getUserWidth(), Math.max(JP.getHeight() - 60, UserList.size() * 60));
        User.repaint();
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddAddress();
        }
    }

    public class EnterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            AddAddress();
        }
    }

    public class GrayText implements FocusListener {
        private final Color INACTIVE
                = UIManager.getColor("TextField.inactiveForeground");
        private String hintMessage;

        public GrayText(JTextComponent tf) {
            hintMessage = tf.getText();
            tf.setForeground(INACTIVE);
        }

        @Override
        public void focusGained(FocusEvent e) {
            JTextComponent tf = (JTextComponent) e.getComponent();
            if (hintMessage.equals(tf.getText())
                    && INACTIVE.equals(tf.getForeground())) {
                tf.setForeground(UIManager.getColor("TextField.foreground"));
                tf.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextComponent tf = (JTextComponent) e.getComponent();
            if ("".equals(tf.getText().trim())) {
                tf.setForeground(INACTIVE);
                tf.setText(hintMessage);
            }
        }
    }
}
