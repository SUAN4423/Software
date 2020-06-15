package jp.suan;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        Window.singleton.makeWindow("CHAT APPLICATION");
        UserSelectWindow.singleton.setWindow();
        ChatWindow.singleton.setWindow();
        Window.singleton.repaint();
    }
}
