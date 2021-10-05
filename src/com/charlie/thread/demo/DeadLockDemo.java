package com.charlie.thread.demo;

public class DeadLockDemo {
    public static void main(String[] args) {
        DLD d1 = new DLD(true);
        d1.setName("Thread A");

        DLD d2 = new DLD(false);
        d2.setName("Thread B");

        d1.start();
        d2.start();
    }
}

class DLD extends Thread {
    private static Object o1 = new Object();
    private static Object o2 = new Object();
    boolean flag;

    public DLD(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " hold o1 and wanna get o2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " get o2!");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " hold o2 and wanna get o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " get o1!");
                }
            }
        }
    }
}
