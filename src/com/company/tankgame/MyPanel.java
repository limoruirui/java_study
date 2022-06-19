package com.company.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    // 定义敌人坦克 放入Vector中
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;
    //定义游戏中的乙方坦克的移动速度
    int moveSpeed = 10;

    public MyPanel() {
        hero = new Hero(100, 100);//初始位置
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 200, 2);
            new Thread(enemyTank).start();
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        if (hero != null && hero.isLive()) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
            for (int i = 0; i < hero.getShots().size(); i++) {
                Shot shot = hero.getShots().get(i);
                if (shot != null && shot.isLive()) {
                    drawShot(hero.getShots().get(i).getX(), hero.getShots().get(i).getY(), g);
                } else {
                    hero.getShots().remove(shot);
                }
            }
        } else {
            hero = null;
        }

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (!enemyTank.isLive()) {
                continue;
            }
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
            //画出敌人坦克的子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (shot.isLive()) {
                    g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                } else {
                    //若子弹死亡则从Vector移除
                    enemyTank.shots.remove(shot);
                }
            }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        // 根据坦克方向, 来绘制对应形状坦克
        //规定0表示向上 1表示向右 2表示向下 3表示向左
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false); //左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//圆形盖子
                g.drawLine(x + 20, y, x + 20, y + 30);//炮筒
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 60, y + 20, x + 30, y + 20);
                break;

            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 60, x + 20, y + 30);
                break;

            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x, y + 20, x + 30, y + 20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    //判断我方子弹是否击中敌方坦克
    public static void hitTank(Shot s, Tank tank) {
        //判断s 击中坦克
        switch (tank.getDirect()) {
            case 0:
            case 2:
                if (s.getX() > tank.getX() && s.getX() < tank.getX() + 40 && s.getY() > tank.getY() && s.getY() < tank.getY() + 60) {
                    s.setLive(false);
                    tank.setLive(false);
                }
                break;
            case 1:
            case 3:
                if (s.getX() > tank.getX() && s.getX() < tank.getX() + 60 && s.getY() > tank.getY() && s.getY() < tank.getY() + 40) {
                    s.setLive(false);
                    tank.setLive(false);
                }
                break;
        }
    }

    public static void hitHero(Vector<EnemyTank> enemyTanks, Hero hero) {
        //遍历所有坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克 然后遍历该坦克的所有子弹
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (hero.isLive() && shot.isLive()) {
                    hitTank(shot, hero);
                }
            }
        }
        if (!hero.isLive()) {
            System.out.println("我方坦克阵亡 游戏结束");
        }
    }

    //画出子弹
    public void drawShot(int x, int y, Graphics g) {
        g.fillOval(x, y, 5, 5);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp(moveSpeed);
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight(moveSpeed);
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown(moveSpeed);
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft(moveSpeed);
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_J) {
            if (hero.getShots().size() < 5) {
                hero.Attack();
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            //判断是否击中了敌方坦克
            if (hero.getShot() != null && hero.getShot().isLive()) {
                //遍历所有坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    for (int j = 0; j < hero.getShots().size(); j++) {
                        hitTank(hero.getShots().get(j), enemyTank);
                    }
                    if (!enemyTank.isLive()) {
                        enemyTanks.remove(enemyTank);
                    }
                }
            }
            hitHero(enemyTanks, hero);
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
