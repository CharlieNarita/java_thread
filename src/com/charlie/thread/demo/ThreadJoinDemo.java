package com.charlie.thread.demo;

/**
 * when A thread execute B thread join method
 * A waiting for B over, then A run over...
 */
public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadJoin threadJoin = new ThreadJoin();
        Thread t1 = new Thread(threadJoin);
        Thread t2 = new Thread(threadJoin);

        t1.start();

        t1.join();  //join means get cpu execute priority

        t2.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "..." + i);
        }
        System.out.println("main thread over!");
    }
}

class ThreadJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "..." + i);
        }
        System.out.println(Thread.currentThread().getName() + " over!");
    }
}
