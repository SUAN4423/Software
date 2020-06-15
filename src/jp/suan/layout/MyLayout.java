package jp.suan.layout;

import javax.swing.*;
import java.awt.*;

public class MyLayout implements LayoutManager {
    public JComponent JC;

    public MyLayout(JComponent JC){
        this.JC = JC;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        //System.out.println("add " + name + " " + comp);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        //.out.println("remove " + comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        //System.out.println("preferred " + parent);
        return JC.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        //System.out.println("minimum " + parent);
        return JC.getSize();
    }

    @Override
    public void layoutContainer(Container parent) {
        //System.out.println("layout " + parent.getComponents());
    }
}
