package com.charlie.thread.test;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        new Thread(ticket).start();
        Thread.sleep(100);
        ticket.flag = false;
        new Thread(ticket).start();

    }
}

class Ticket implements Runnable {
    private int tickets = 100;
    Object obj = new Object();
    boolean flag = true;
    boolean loop = true;

    @Override
    public void run() {
        if (flag) {
            while (loop) {
                synchronized (obj) {
                    if (tickets > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "...code: " + tickets--);
                    } else
                        loop = false;
                }
            }
        } else
            while (loop) {
                sell();
            }
    }

    public synchronized void sell() {
        if (tickets > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...code: " + tickets--);
        } else
            loop = false;
    }
}
