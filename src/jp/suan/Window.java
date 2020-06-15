package jp.suan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame {
    public static Window singleton = new Window();

    public Insets border;

    public void makeWindow() {
        this.makeWindow("No title");
    }

    public void makeWindow(String windowname) {
        singleton.setTitle(windowname);
        singleton.setSize(800, 600);
        singleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        singleton.setLayout(null);
        singleton.setVisible(true);
        this.border = singleton.getInsets();
        singleton.setMinimumSize(this.includeBorder(800, 600));
        singleton.setSize(this.includeBorder(800, 600));
        singleton.addComponentListener(new WindowListener());

        //System.out.println(this.border.left + " " + this.border.right + " " + this.border.top + " " + this.border.bottom);
    }

    public Dimension includeBorder(int width, int height) {
        return new Dimension(width + this.border.left + this.border.right, height + this.border.top + this.border.bottom);
    }

    public int getWidthBorder() {
        return this.border.right + this.border.left;
    }

    public int getHeightBorder() {
        return this.border.top + this.border.bottom;
    }

    public class WindowListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent e) {
            try {
                UserSelectWindow.singleton.resetWindowSize();
                ChatWindow.singleton.resetWindowSize();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {
            try {
                UserSelectWindow.singleton.resetWindowSize();
                ChatWindow.singleton.resetWindowSize();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    }
}
