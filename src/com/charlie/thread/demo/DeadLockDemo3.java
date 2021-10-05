package com.charlie.thread.demo;

public class DeadLockDemo3 {
    public static void main(String[] args) {
        new Thread(new LockTest(true)).start();
        new Thread(new LockTest(false)).start();
    }
}

class LockTest implements Runnable {
    private boolean flag;

    LockTest(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (MyLock.lockA) {
                System.out.println("if lockA");
                synchronized (MyLock.lockB) {
                    System.out.println("if lockB");
                }
            }
        } else {
            synchronized (MyLock.lockB) {
                System.out.println("else lockB");
                synchronized (MyLock.lockA) {
                    System.out.println("else lockA");
                }
            }
        }
    }
}

class MyLock {
    static Object lockA = new Object();
    static Object lockB = new Object();
}
