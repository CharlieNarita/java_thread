package com.charlie.thread.demo;

/**
 * create a thread by extending class Thread
 */
public class Thread01 {
    //this is main thread
    public static void main(String[] args) {
        //create t01 object will be a thread cause it extends Thread
        T01 t01 = new T01();
        t01.start();    //start a thread, call run() method automatically
        mainRun();

        /*

        public synchronized void start() {
            start0();
        }

        //start0() is a native method, invoked by JVM
        //start0() essentially built by c/c++

        private native void start0();

         */

    }

    public static void mainRun() {
        int times = 0;
        for (int i = 0; i < 50; i++) {
            System.out.println("main run..." + (++times) + " " + Thread.currentThread().getName()); //main
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class T01 extends Thread {
    int times = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("T01 run..." + (++times) + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 100) {
                break;  //quit the while loop and thread
            }
        }
    }
}
