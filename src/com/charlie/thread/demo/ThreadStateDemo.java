package com.charlie.thread.demo;

public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        TSD tsd = new TSD();    //NEW state
        System.out.println(tsd.getName() + " " + tsd.getState());
        tsd.start();    //RUNNABLE state

        while (Thread.State.TERMINATED != tsd.getState()) {
            System.out.println(tsd.getName() + " " + tsd.getState());
            Thread.sleep(1000);
        }

        System.out.println(tsd.getName() + " " + tsd.getState());
    }
}

class TSD extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("tsd run..." + i);
            }
            break;
        }
    }
}
