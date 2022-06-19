package com.company.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//通过键盘上下左右控制移动
public class BallMove extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.addKeyListener(mp); //监听键盘输入
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
//KeyListener是一个键盘输入的监听器
class MyPanel extends JPanel implements KeyListener {
    //为了让小球可以移动 我们设置小球的坐标
    int x = 10, y=10;
    final int row_move_speed = 10, col_move_speed=10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    //监听键盘输出时触发
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //当某个建按下去 触发
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) { //向下箭头的code
            y += row_move_speed;
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            y -= row_move_speed;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            x -= col_move_speed;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x += col_move_speed;
        }
        this.repaint();
    }
    //当某个键弹起了 触发
    @Override
    public void keyReleased(KeyEvent e) {

        //在java中
    }
}