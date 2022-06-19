package com.company.tankgame;

public class Hero extends Tank {
    //定义一个Shot对象
    private Shot shot = null;

    public Hero(int x, int y) {
        super(x, y, 0);
    }

    public Shot getShot() {
        return shot;
    }

    public void Attack() {
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), getDirect());
                super.getShots().add(shot);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, getDirect());
                super.getShots().add(shot);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, getDirect());
                super.getShots().add(shot);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, getDirect());
                super.getShots().add(shot);
                break;
        }
        //启动设计线程
        new Thread(shot).start();
    }
}
