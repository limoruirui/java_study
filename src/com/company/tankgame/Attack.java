package com.company.tankgame;

public class Attack implements Runnable {
    private Tank tank;

    public Attack(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void run() {
        System.out.println(tank + "射出了一颗子弹");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +"子弹到达边界或射中了坦克或者墙壁 子弹消失");
    }
}
