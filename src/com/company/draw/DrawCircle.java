package com.company.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DrawCircle extends JFrame { //JFrame 对应一个窗口
    private Mypanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle() {
        mp = new Mypanel();
        this.add(mp);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可以显示
    }
}

//定义一个面板 MyPanel 继承JPanel类
class Mypanel extends JPanel {

    /*1.MyPanel对象就是一个画板
    * 2.Graphics g 把g理解成一个画笔 g.方法画出东西
    * 3.Graphics提供了很多现成的画图方法
    * */
    @Override
    public void paint(Graphics g) { //绘图方法
        super.paint(g); //调用父类的方法完成初始化
        g.setColor(Color.CYAN);
        g.fillRect(40, 40, 7, 50);
        g.fillRect(65, 40, 7, 50);
        g.fillRect(47, 47, 18, 36);
        g.setColor(Color.black);
        g.fillOval(46, 53, 18, 18);
        g.drawLine(56, 40, 56, 65);
//        g.drawOval(10, 10, 100, 100);


        /*
        g.drawLine(10, 10, 100, 100) 画直线 (起始点x 起始点y 终点x 终点y)
        g.drawRect(10, 10, 100, 100) 画空心矩形(起始点x 起始点y 宽 高)
        g.fillRect(10, 10, 100, 100) 填充矩形 先设置填充色 g.setColor(Color.blue)
        g.fillOval(10, 10, 100, 100) 填充椭圆
        Image image = Toolkit.getDefaultToolkit().getImage(Mypanel.class.getResource("photo.png")); //图片要放在out对应的class相对路径里
        g.drawImage(image, 10, 10, 175, 221, this)
        g.setFont(new Font("隶书", Font.BOLD, 50) //设置字体
        g.drawString("瑞瑞", 100, 100);
         */
    }
}