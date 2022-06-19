package com.company.qqserver.server;

import com.company.qqcommon.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ManageSendContent {
    public static void sendManageSendContent(String userId, Message message) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(userId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
