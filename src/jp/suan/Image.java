package jp.suan;

import java.awt.*;

public class Image extends Text {

    @Override
    public int WindowSet(int LineHeight, int chatwindowsize, int height, FontMetrics fm) {
        return LineHeight;
    }
}
