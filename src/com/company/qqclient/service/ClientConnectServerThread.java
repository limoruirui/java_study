package com.company.qqclient.service;

import com.company.qqcommon.Message;
import com.company.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread{
    private Socket socket;
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //因为线程需要在后台和服务器通讯
        while (true) {
            System.out.println("客户端线程 等待服务端发来数据");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果没有收到数据 就会堵塞
                Message message = (Message)ois.readObject();
                //判断message类型 做响应的业务
                if(message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    //规定 在线用户都是 userId 内容
                    String[] s = message.getContent().split(" ");
                    System.out.println("========当前在线用户列表========");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("用户 " + s[i]);
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_TOALL_MES)) {
                    //
                    System.out.println("\n" + message.getSender() + "对大家说" + message.getContent());
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    System.out.println("\n" + message.getSendTime());
                    System.out.println("\n" + message.getSender()
                    + " 对 " + message.getGetter() + "说: " + message.getContent());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
