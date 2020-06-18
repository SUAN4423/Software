package jp.suan.network;

import jp.suan.ChatWindow;
import jp.suan.UserSelectWindow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
                Socket socket = new Socket(this.sendmessage.ToAddress, 8080);
                PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                pr.println(UserSelectWindow.singleton.Name.getText());
                pr.println(this.sendmessage.Messages);
                pr.println("[fin]");
                pr.close();
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
