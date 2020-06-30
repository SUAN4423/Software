package jp.suan;

import jp.suan.network.Receive;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        Window.singleton.makeWindow("CHAT APPLICATION");
        UserSelectWindow.singleton.setWindow();
        ChatWindow.singleton.setWindow();
        Window.singleton.repaint();
        Receive re = new Receive();
        re.start();
    }
}
