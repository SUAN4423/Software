package jp.suan.network;

import jp.suan.UserSelectWindow;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Receive extends Thread {

    public static ArrayList<Message> receiveMessage = new ArrayList<>();

    @Override
    public void run() {
        super.run();

        ServerSocket ssocket = null;
        Socket socket = null;

        while (true) {
            try {
                ssocket = new ServerSocket(8080);
                //ssocket.bind(new InetSocketAddress(8080));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (ssocket != null) {
                    socket = ssocket.accept();
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String Messages = br.readLine();
                    String receive = "<html>";
                    String name = "";
                    System.out.println("Message Received : " + Messages);
                    if (Messages.equals("[WhoAreYou]")) {
                        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                        pr.println(UserSelectWindow.singleton.Name.getText());
                        pr.close();
                    } else {
                        name += Messages;
                        while (!Messages.equals("[fin]")) {
                            receive += Messages;
                            Messages = br.readLine();
                            System.out.println("Message Received : " + Messages);
                            if(!Messages.equals("[fin]")) receive +=  "<br>";
                        }
                        receive += "</html>";
                        Message ms = new Message(receive, socket.getLocalAddress().toString(), socket.getRemoteSocketAddress().toString(), name);
                        receiveMessage.add(ms);
                    }
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ssocket != null) ssocket.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
