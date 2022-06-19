package com.company.tankgame;

import javax.swing.*;
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y, int direct) {
        super(x, y, direct);
    }

    @Override
    public void run() {
        while (true) {
            //若地方坦克存活且子弹已消亡
            if (isLive() && shots.size() == 0) {
                //
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY()+20, 1);
                        break;
                    case 2:
                        s = new Shot(getX()+20, getY()+60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY()+20, 3);
                        break;
                }
                if((int)(Math.random()*50) == 5) {
                    shots.add(s);
                    new Thread(s).start();
                }
            }
            //根据坦克方向移动
            switch (getDirect()) {
                case 0:
                    moveUp(2);
                    break;
                case 1:
                    moveRight(2);
                    break;
                case 2:
                    moveDown(2);
                    break;
                case 3:
                    moveLeft(2);
                    break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((int) (Math.random() * 20) == 5) {
                setDirect((int) (Math.random() * 4));
            }//raw_master_dwsj_variable_data.js
            //随机改变方向
            if (!isLive()) {
                break;
            }
        }
    }
}
