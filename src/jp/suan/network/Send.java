package jp.suan.network;

import jp.suan.ChatWindow;
import jp.suan.UserSelectWindow;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Send extends Thread {

    private Message sendmessage;
    private boolean[] b;

    public Send(Message messages, boolean[] b) {
        this.sendmessage = messages;
        this.b = b;
    }

    @Override
    public void run() {
        super.run();
        if (this.sendmessage == null) {
            return;
        } else {
            try {
                Socket socket = new Socket();//(this.sendmessage.ToAddress, 8080);
                socket.connect(new InetSocketAddress(this.sendmessage.ToAddress, 8080), 2000);
                PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                pr.println(UserSelectWindow.singleton.Name.getText());
                if (b[3]) {
                    pr.println("[image]");
                    pr.close();
                    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    out.writeObject(this.sendmessage);
                    out.close();
                } else {
                    pr.println(this.sendmessage.Messages);
                    pr.println("[fin]");
                    pr.close();
                }
                socket.close();
                b[0] = true;
            } catch (IOException e) {
                e.printStackTrace();
                b[0] = false;
            }
        }
        b[1] = false;
        ChatWindow.singleton.sendfinish();
    }
}
