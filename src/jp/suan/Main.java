package jp.suan;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        Window.singleton.makeWindow("CHAT APPLICATION");
        Thread.sleep(1000);
        UserSelectWindow.singleton.setWindow();
        ChatWindow.singleton.setWindow();
        Window.singleton.repaint();
    }
}
