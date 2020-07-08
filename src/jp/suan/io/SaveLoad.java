package jp.suan.io;

import jp.suan.ChatLog;
import jp.suan.Main;
import jp.suan.Text;
import jp.suan.UserSelectWindow;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.URLDecoder;

public class SaveLoad implements WindowListener {
    private String filename;

    public SaveLoad() {
        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            filename = URLDecoder.decode(path, "UTF-8") + "Chatlog";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            filename = "./Chaglog";
        }
    }

    public void load() {
        try {
            File file = new File(this.filename);
            BufferedReader br = new BufferedReader(new FileReader(file));

            boolean Messagemode = false;
            ChatLog chat = null;
            boolean My = false;
            String tempstr = "";

            String str = br.readLine();
            while (str != null) {
                System.out.println(str);
                if (Messagemode) {
                    if (str.equals("F:")) {
                        Text newtext = new Text();
                        newtext.JArea = new JTextArea(tempstr);
                        newtext.JArea.setLayout(null);
                        newtext.JArea.setEditable(false);
                        newtext.JArea.setLineWrap(true);
                        newtext.JArea.setBackground(My ? Color.GREEN : Color.LIGHT_GRAY);
                        newtext.JArea.setBorder(new LineBorder(Color.BLACK));
                        newtext.Me = My;
                        chat.Messages.add(newtext);
                        chat.JPWindow_Chat.add(newtext.JArea);
                        Messagemode = false;
                    } else {
                        tempstr += "\n" + str;
                    }
                } else {
                    String subst = str.substring(0, 1);
                    String subst2 = str.substring(2);
                    if (subst.equals("E")) {
                        UserSelectWindow.singleton.Name.setText(subst2);
                    } else if (subst.equals("N")) {
                        if (chat != null) {
                            UserSelectWindow.singleton.UserList.add(chat);
                            UserSelectWindow.singleton.User.add(UserSelectWindow.singleton.UserList.get(UserSelectWindow.singleton.UserList.size() - 1).JPWindow_UserSelect);
                            chat.addedMessage();
                        }
                        tempstr = subst2;
                    } else if (subst.equals("A")) {
                        chat = new ChatLog(tempstr, subst2, UserSelectWindow.singleton.UserList.size());
                    } else if (subst.equals("M")) {
                        tempstr = subst2;
                        Messagemode = true;
                        My = true;
                    } else if (subst.equals("Y")) {
                        tempstr = subst2;
                        Messagemode = true;
                        My = false;
                    }
                }
                str = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            File file = new File(this.filename);
            if (!file.exists()) file.createNewFile();
            FileWriter filewriter = new FileWriter(file);

            filewriter.write("E:" + UserSelectWindow.singleton.Name.getText() + "\n");

            for (ChatLog c : UserSelectWindow.singleton.UserList) {
                filewriter.write("N:" + c.UserName + "\n");
                filewriter.write("A:" + c.Address + "\n");
                for (Text t : c.Messages) {
                    filewriter.write((t.Me ? "M:" : "Y:") + t.JArea.getText() + "\nF:\n");
                }
            }
            filewriter.write("N:");

            filewriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
