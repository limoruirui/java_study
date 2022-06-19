package com.company.qqserver.server;

import java.util.HashMap;
import java.util.Iterator;

//用于管理和客户端通讯的线程
public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    public static void addClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userId, serverConnectClientThread);
    }

    //根据userId 返回ServerConnectClientThread线程
    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hm.get(userId);
    }
    public static StringBuilder getOnlineUser() {
        //集合遍历 HashMap的key
        Iterator<String> iterator = hm.keySet().iterator();
        StringBuilder onlineUserList = new StringBuilder();
        while (iterator.hasNext()) {
            onlineUserList.append(iterator.next() + " ");
        }
        return onlineUserList;
    }
    public static void removeServerConnectClientThread(String userId) {
        hm.remove(userId);
    }
}
