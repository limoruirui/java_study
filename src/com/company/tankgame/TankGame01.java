package com.company.tankgame;

import javax.swing.*;

public class TankGame01 extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }
    public TankGame01() {
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1016, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
