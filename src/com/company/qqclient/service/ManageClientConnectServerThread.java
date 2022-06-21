package com.company.qqclient.service;

import java.util.HashMap;

public class ManageClientConnectServerThread {
    //把多个线程放入到集合中 key就是用户id value就是线程
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    //加入
    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
        hm.put(userId, clientConnectServerThread);
    }

    //通过userId获得线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hm.get(userId);
    }
}
