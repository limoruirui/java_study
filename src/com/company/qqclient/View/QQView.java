package com.company.qqclient.View;

import com.company.qqclient.service.MessageClientService;
import com.company.qqclient.service.UserClientService;

import java.util.Scanner;

public class QQView {
    private boolean loop = true;
    private String  key = "";//接收用户输入
    private UserClientService userClientService = new UserClientService();//用于登录服务器和注册用户
    public static void main(String[] args) throws InterruptedException {
        new QQView().mainMenu();
        System.out.println("退出系统");
    }
    private void mainMenu() throws InterruptedException {
        while (loop) {
            System.out.println("=======欢迎登录=====");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择: ");
            Scanner scanner = new Scanner(System.in);
            String key = scanner.next();
            switch (key) {
                case "1":
                    System.out.print("请输入用户号: ");
                    String userId = scanner.next();
                    System.out.print("请输入密码: ");
                    String pwd = scanner.next();
                    //到服务端验证用户是否合法
                    //写一个类 UserClientServer 去验证用户
                    if(userClientService.checkUser(userId, pwd)) {
                        System.out.println("=======欢迎 " + userId +" ===========");
                        //进入到二级菜单
                        while (loop) {
                            System.out.println("========网络通信系统二级菜单=====(用户 " + userId + " )=============");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择: ");
                            switch (scanner.next()) {
                                case "1":
                                    userClientService.onlineFriendList();
                                    Thread.sleep(1000);
                                    break;
                                case "2":
                                    System.out.print("请输入你要对大家说的话: ");
                                    String s = scanner.next();
                                    MessageClientService.sendMessageToall(s, userId);
                                    Thread.sleep(500);
                                    break;
                                case "3":
                                    System.out.print("请输入想聊天的用户号(在线): ");
                                    String getterId = scanner.next();
                                    System.out.print("请输入想说的话: ");
                                    String content = scanner.next();
                                    MessageClientService.sendMessageToOne(content, userId, getterId);
                                    Thread.sleep(500);
                                    break;
                                case "4":
                                    System.out.println("发送文件");
                                    break;
                                case "9":
                                    loop =false;
                                    userClientService.logout();
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}
