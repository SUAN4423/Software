package jp.suan.network;

import jp.suan.*;
import jp.suan.Window;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive extends Thread {

    @Override
    public void run() {
        super.run();

        ServerSocket ssocket = null;

        while (true) {
            try {
                ssocket = new ServerSocket(8080);
                //ssocket.bind(new InetSocketAddress(8080));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (ssocket != null) {
                    System.out.println("待機中");
                    Socket socket = ssocket.accept();
                    System.out.println("Connected : " + socket);
                    SocketThread st = new SocketThread(socket);
                    st.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ssocket != null) ssocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SocketThread extends Thread {
        Socket socket;

        public SocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                String Messages = br.readLine();
                String receive = "";
                String name = "";
                System.out.println("Message Received : " + Messages);
                if (Messages.equals("[WhoAreYou]")) {
                    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                    pr.println(UserSelectWindow.singleton.Name.getText());
                    pr.close();
                } else {
                    name += Messages;
                    Messages = br.readLine();
                    System.out.println("Message Received : " + Messages);
                    while (!Messages.equals("[fin]")) {
                        receive += Messages;
                        Messages = br.readLine();
                        System.out.println("Message Received : " + Messages);
                        if (!Messages.equals("[fin]")) receive += "\n";
                    }
                    Message ms = new Message(receive, socket.getLocalAddress().toString(), socket.getInetAddress().toString().substring(1), name);
                    boolean b = false;
                    for (int i = 0; i < UserSelectWindow.singleton.UserList.size(); i++) {
                        if (UserSelectWindow.singleton.UserList.get(i).UserName.equals(ms.Name)) {
                            UserSelectWindow.singleton.UserList.get(i).Address = ms.FromAddress;
                            UserSelectWindow.singleton.UserList.get(i).IPAddress.setText("Address : " + ms.FromAddress);
                            Text n = new Text();
                            n.JArea = new JTextArea(ms.Messages);
                            n.JArea.setLayout(null);
                            n.Me = false;
                            n.JArea.setEditable(false);
                            n.JArea.setLineWrap(true);
                            n.JArea.setBackground(Color.LIGHT_GRAY);
                            n.JArea.setBorder(new LineBorder(Color.BLACK));
                            UserSelectWindow.singleton.UserList.get(i).Messages.add(n);
                            UserSelectWindow.singleton.UserList.get(i).JPWindow_Chat.add(n.JArea);
                            UserSelectWindow.singleton.UserList.get(i).addedMessage();
                            if (UserSelectWindow.singleton.UserList.get(i)==Window.singleton.nowSelected){
                                ChatWindow.singleton.Display.setBounds(0, 50, ChatWindow.singleton.getDisplayWidth(ChatWindow.singleton.JP.getWidth()), Math.max(ChatWindow.singleton.JP.getHeight() - 150, Window.singleton.nowSelected.JPWindow_Chat.getHeight()));
                            }
                            b = true;
                            break;
                        }
                    }
                    if (!b) {
                        ChatLog c = new ChatLog(ms.Name, ms.FromAddress, UserSelectWindow.singleton.UserList.size());
                        Text n = new Text();
                        n.JArea = new JTextArea(ms.Messages);
                        n.JArea.setLayout(null);
                        n.Me = false;
                        n.JArea.setEditable(false);
                        n.JArea.setLineWrap(true);
                        n.JArea.setBackground(Color.LIGHT_GRAY);
                        n.JArea.setBorder(new LineBorder(Color.BLACK));
                        c.Messages.add(n);
                        c.JPWindow_Chat.add(n.JArea);
                        UserSelectWindow.singleton.UserList.add(c);
                        UserSelectWindow.singleton.User.add(UserSelectWindow.singleton.UserList.get(UserSelectWindow.singleton.UserList.size() - 1).JPWindow_UserSelect);
                        UserSelectWindow.singleton.User.setBounds(0, 0, UserSelectWindow.singleton.getUserWidth(), Math.max(UserSelectWindow.singleton.JP.getHeight() - 60, UserSelectWindow.singleton.UserList.size() * 60));
                        UserSelectWindow.singleton.User.repaint();
                        c.addedMessage();
                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Finish");
        }
    }
}
