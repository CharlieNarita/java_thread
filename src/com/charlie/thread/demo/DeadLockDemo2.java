package com.charlie.thread.demo;

public class DeadLockDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Ticketing ticketing = new Ticketing();
        new Thread(ticketing).start();
        Thread.sleep(10);
        ticketing.flag = false;
        new Thread(ticketing).start();
    }
}

class Ticketing implements Runnable {
    private int tickets = 300;
    Object lock = new Object();
    boolean flag = true;
    boolean loop = true;

    @Override
    public void run() {
        if (flag) {
            while (loop) {
                System.out.print("A...");
                sellA();
            }
        } else {
            while (loop) {
                System.out.print("B...");
                sellB();
            }
        }
    }

    private synchronized void sellA() { //synchronized this
        synchronized (lock) {   //synchronized object lock
            if (tickets > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " sell..." + tickets--);
            } else {
                loop = false;
            }
        }
    }

    private void sellB() {
        synchronized (lock) {   //synchronized object lock
            sellA();    //synchronized this
        }
    }
}