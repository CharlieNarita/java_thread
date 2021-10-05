package com.charlie.thread.demo;

public class ThreadAnonymousDemo {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "...run..." + i);
            Thread.yield();
        }

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "...run..." + i);
                    Thread.yield();
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "...run..." + i);
                    Thread.yield();
                }
            }
        }).start();

    }
}
