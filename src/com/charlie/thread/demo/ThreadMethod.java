package com.charlie.thread.demo;

public class ThreadMethod {
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.start();

        for (int i = 1; i <= 20; i++) {
            Thread.sleep(1000);
            System.out.println("main run..." + i);
            if (i == 5) {
                System.out.println("main let T1 run first...");
                //join method
                t1.join();

                //yield method;
//                Thread.yield();

                System.out.println("T1 running over, main continue run...");
            }
        }
    }
}

class T1 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 run..." + i);
        }
    }
}
