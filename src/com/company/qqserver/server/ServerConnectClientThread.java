package com.company.qqserver.server;

import com.company.qqcommon.Message;
import com.company.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

//该类的一个对象用于和某个客户端保持通信
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        while (true) {
            //
            System.out.println("服务端和客户端" + userId + "保持通信 读取数据");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //后面再使用message 根据message类型 做相应处理
                if (message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    System.out.println(message.getSender() + "在拉取在线用户list");
                    StringBuilder onlineUser = ManageClientThreads.getOnlineUser();
                    //返回message 返回给客户端
                    Message message1 = new Message();
                    message1.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUser.toString());
                    message1.setGetter(message.getSender());

                    //返回给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    ManageSendContent.sendManageSendContent(message.getGetter(), message);
//                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(message.getGetter()).getSocket().getOutputStream());
//                    oos.writeObject(message);
                } else if(message.getMesType().equals(MessageType.MESSAGE_TOALL_MES)) {
                    //先遍历所有线程的集合 然后依次发送
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        String onlineUserId = iterator.next();
                        System.out.println(onlineUserId);
                        if(!onlineUserId.equals(message.getSender())) {//排除掉发送者自己
                            ManageSendContent.sendManageSendContent(message.getGetter(), message);
//                            ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(onlineUserId).getSocket().getOutputStream());
////                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
//                            oos.writeObject(message);
                        }
                    }
                }
                else if (message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    System.out.println(message.getSender() + "请求退出");
                    ManageClientThreads.removeServerConnectClientThread(message.getSender());
                    Thread.sleep(500);
                    socket.close();
                    //退出这个线程
                    break;
                } else {
                    System.out.println("暂时不做处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
