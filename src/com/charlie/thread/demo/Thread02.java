package com.charlie.thread.demo;

/**
 * create a thread by implementing interface Runnable
 */
public class Thread02 {
    public static void main(String[] args) {

        Runnable r02 = new T02();
        //r02 can not call start() method cause Runnable hasn't the start() method
        //pass r02 to Thread constructor to new a Thread object;
        //then invoke start() method through Thread object
        Thread thread = new Thread(r02);
        thread.start();
    }
}

class T02 implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("T02 run..." + (++count) + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 50) {
                break;
            }
        }
    }
}


