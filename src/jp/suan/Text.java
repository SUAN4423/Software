package jp.suan;

import javax.swing.*;
import java.awt.*;

public class Text {
    public JTextArea JArea;
    public boolean Me;
    public JLabel Label;

    public int WindowSet(int LineHeight, int chatwindowsize, int height, FontMetrics fm){
        String[] str = this.JArea.getText().split("\r\n|\r|\n", -1);
        int Line = 0;
        for (int j = 0; j < str.length; j++) {
            int width = fm.stringWidth(str[j]);
            Line += width / (chatwindowsize - 60) + (width % (chatwindowsize - 60) == 0 ? 0 : 1);
            if (str[j].equals("")) Line++;
        }
        if (this.Me) {
            this.JArea.setBounds(60, LineHeight, chatwindowsize - 60, height * Line);
            LineHeight += height * Line;
        } else {
            this.JArea.setBounds(0, LineHeight, chatwindowsize - 60, height * Line);
            LineHeight += height * Line;
        }
        return LineHeight;
    }
}
