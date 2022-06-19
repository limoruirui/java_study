package com.company.qqserver.server;

import com.company.qqcommon.Message;
import com.company.qqcommon.MessageType;
import com.company.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

//监听9999端口
public class QQServer {
    private ServerSocket ss = null;
    //创建一个集合 保存合法有效的用户
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static {
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("瑞瑞", new User("100", "123456"));
        validUsers.put("ruirui", new User("100", "123456"));
        validUsers.put("limoruirui", new User("100", "123456"));
    }
    //验证用户是否合法
    public boolean checkUser(String userId, String pwd) {
        User user = validUsers.get(userId);

        if(user == null) { //userId不在validUsers中
            return false;
        }
        if(!user.getPwd().equals(pwd)) {
            return false;
        }
        return true;
    }
    public  QQServer() {
        System.out.println("服务端再9999监听");
        try {
            ss = new ServerSocket(9999);
            while (true) {
                //一直不间断监听
                Socket socket = ss.accept();
                //得到socket关联对象输入的东西
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //得到socket关联对象的输入流 用于回复信息
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                User u = (User) ois.readObject();
                Message message = new Message();
                if(checkUser(u.getUserId(), u.getPwd())) {
                    //登录成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);
                    //登录成功就创建一个线程 用于和客户端保持通信
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUserId());
                    serverConnectClientThread.start();
                    //把线程加入集合中
                    ManageClientThreads.addClientThread(u.getUserId(), serverConnectClientThread);
                } else {
                    //登陆失败
                    System.out.println("用户 id=" + u.getUserId() + "pwd=" + u.getPwd() + "登录失败");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //如果退出循环了 说明不需要监听了 关闭
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
