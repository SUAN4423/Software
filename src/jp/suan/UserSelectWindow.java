package jp.suan;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UserSelectWindow {
    public static UserSelectWindow singleton = new UserSelectWindow();

    public JPanel JP;

    public JPanel IPAddress;
    public JButton IPButton;
    public JTextField IPField;

    public JPanel User;

    public UserSelectWindow() {
        this.JP = new JPanel();
        this.JP.setBounds(0, 0, 300, 600);
        this.JP.setBackground(Color.RED);

        this.IPAddress = new JPanel();
        this.IPAddress.setBounds(0, 0, 300, 30);
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
        this.JP.add(this.IPField);
        this.JP.add(this.IPAddress);

        this.User = new JPanel();
        this.User.setBackground(Color.MAGENTA);
        this.User.setBounds(0, 30, 300, 570);
        this.JP.add(this.User);
    }

    public void setWindow() {
        Window.singleton.add(this.JP);
    }

    public void resetWindowSize() {
        this.JP.setSize(300, Window.singleton.getHeight() - Window.singleton.getHeightBorder());
        this.IPAddress.setBounds(0, 0, 300, 30);
        this.IPButton.setBounds(200, 0, 100, 30);
        this.IPField.setBounds(0, 0, 200, 30);
        this.User.setBounds(0, 30, 300, this.JP.getHeight() - 30);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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
