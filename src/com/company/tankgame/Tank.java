package com.company.tankgame;

import java.util.Vector;

public class Tank {
    private int x;//坦克横坐标
    private int y;// 坦克纵坐标
    private int direct;//坦克方向 0上 1右 2下 3左
    private Vector<Shot> shots = new Vector<>();
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    public void moveUp(int moveSpeed) {
        if (getY() > 0) {
            y -= moveSpeed;
        } else {setDirect((int) (Math.random()*4));}
    }

    public void moveRight(int moveSpeed) {
        if (getX() + 60 < 1000) {
            x += moveSpeed;
        } else {setDirect((int) (Math.random()*4));}
    }

    public void moveDown(int moveSpeed) {
        if (getY() + 60 < 750) {
            y += moveSpeed;
        } else {setDirect((int) (Math.random()*4));}
    }

    public void moveLeft(int moveSpeed) {
        if (getX() > 0) {
            x -= moveSpeed;
        } else {setDirect((int) (Math.random()*4));}
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
