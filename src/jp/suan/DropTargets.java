package jp.suan;

import jp.suan.network.Message;
import jp.suan.network.Send;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DropTargets extends DropTarget {
    @Override
    public synchronized void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
        Transferable trans = dtde.getTransferable();
        List<File> fileNameList = null;
        if (trans.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            try {
                fileNameList = (List<File>)
                        trans.getTransferData(DataFlavor.javaFileListFlavor);
            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (fileNameList == null) return;

        String path = "";

        if (Window.singleton.nowSelected == null) {
            JOptionPane.showMessageDialog(Window.singleton, "送信先を選択してください。", "エラー", JOptionPane.ERROR_MESSAGE);
        } else {
            for (File f : fileNameList) {
                path = f.getAbsolutePath();
                if (!ChatWindow.singleton.messagesending[2]) {
                    if (path.contains(".png")) {
                        System.out.println("Address : " + Window.singleton.nowSelected.Address);
                        Message sendmessage = new Message(new ImageIcon(path), Window.singleton.nowSelected.Address);
                        ChatWindow.singleton.messagesending[1] = true;
                        ChatWindow.singleton.messagesending[2] = true;
                        ChatWindow.singleton.messagesending[3] = true;
                        Send send = new Send(sendmessage, ChatWindow.singleton.messagesending);
                        send.run();
                    } else if (path.contains(".jpg") || path.contains(".jpeg")) {
                        System.out.println("Address : " + Window.singleton.nowSelected.Address);
                        Message sendmessage = new Message(new ImageIcon(path), Window.singleton.nowSelected.Address);
                        ChatWindow.singleton.messagesending[1] = true;
                        ChatWindow.singleton.messagesending[2] = true;
                        ChatWindow.singleton.messagesending[3] = true;
                        Send send = new Send(sendmessage, ChatWindow.singleton.messagesending);
                        send.run();
                    } else {
                        JOptionPane.showMessageDialog(Window.singleton, "そのファイル形式には対応していません。", "エラー", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}
