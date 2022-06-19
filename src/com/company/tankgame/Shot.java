package com.company.tankgame;

import java.awt.*;

public class Shot implements Runnable {
    int x;
    int y;
    int direct = 0;
    int speed = 10;
    private boolean isLive = true;
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() { //射击行为
        while (isLive) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            System.out.println("子弹 x" + x + "y" + y);
            if (!(x > 0 && y > 0 && x < 1000 && y < 750)) {
                System.out.println("子弹碰到了边界 子弹消失");
                this.isLive = false;
                break;
            }
        }
    }
}
