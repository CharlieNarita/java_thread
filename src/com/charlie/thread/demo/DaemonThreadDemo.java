package com.charlie.thread.demo;

public class DaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread myDaemonThread = new MyDaemonThread();

        //if we hope sub-thread finish automatically when main thread over
        //so need to set sub-thread as DaemonThread
        //setDaemon method must call before other threads start
        myDaemonThread.setDaemon(true);
        myDaemonThread.start();

        for (int i = 1; i <= 10; i++) {
            System.out.println("main thread running..." + i);
            Thread.sleep(1000);
        }
    }
}

class MyDaemonThread extends Thread {
    private int i;

    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("daemon thread holding..." + (++i));
        }
    }
}
