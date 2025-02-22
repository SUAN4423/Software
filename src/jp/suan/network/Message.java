package jp.suan.network;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Message {
    public String Messages;
    public String FromAddress;
    public String ToAddress;
    public String Name;
    public ImageIcon Images;

    public Message(String Messages, String ToAddress) {
        try {
            this.Messages = Messages;
            this.ToAddress = ToAddress;
            this.FromAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Message(ImageIcon image, String ToAddress) {
        try {
            this.Images = image;
            this.ToAddress = ToAddress;
            this.FromAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Message(String Messages, String ToAddress, String FromAddress, String name) {
        this.Messages = Messages;
        this.ToAddress = ToAddress;
        this.FromAddress = FromAddress;
        this.Name = name;
    }

    public Message(ImageIcon image, String ToAddress, String FromAddress, String name) {
        this.Images = image;
        this.ToAddress = ToAddress;
        this.FromAddress = FromAddress;
        this.Name = name;
    }
}
