package jp.suan;

import jp.suan.io.SaveLoad;
import jp.suan.network.Receive;

import java.awt.event.WindowEvent;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        Window.singleton.makeWindow("CHAT APPLICATION");
        Window.singleton.setDropTarget(new DropTargets());
        UserSelectWindow.singleton.setWindow();
        ChatWindow.singleton.setWindow();
        SaveLoad sl = new SaveLoad();
        Window.singleton.addWindowListener(sl);
        sl.load();
        Window.singleton.repaint();
        Receive re = new Receive();
        re.start();
    }
}
