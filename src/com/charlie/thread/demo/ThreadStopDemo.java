package com.charlie.thread.demo;

public class ThreadStopDemo {
    public static void main(String[] args) throws InterruptedException {
//        ThreadStop threadStop = new ThreadStop();
//        new Thread(threadStop).start();
//        new Thread(threadStop).start();

        ThreadStop2 threadStop2 = new ThreadStop2();
        Thread t1 = new Thread(threadStop2);
        Thread t2 = new Thread(threadStop2);
        t1.start();
        t2.start();

        int i = 0;
        while (true) {
            Thread.sleep(100);
            if (i++ == 50) {
                //threadStop2.changeLoop();   //despite change loop, those threads are all waiting there
                t1.interrupt(); //force thread change state from wait to runnable
                t2.interrupt(); //force thread change state from wait to runnable
                break;  //even break while and main thread over, those threads have no chance to run over
            }
            System.out.println(Thread.currentThread().getName() + "...run..." + i);
        }
        Thread.sleep(100);
        System.out.println("main thread over");
    }
}

class ThreadStop implements Runnable {
    private boolean loop = true;
    private int i = 0;

    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...run..." + i++);
        }
    }

    public void changeLoop() {
        loop = false;
    }
}

/**
 * synchronized run method and use wait that cause all threads are waiting there...
 * interrupt method can solve this problem but generate InterruptedException
 */
class ThreadStop2 implements Runnable {
    private boolean loop = true;
    private int i = 0;

    @Override
    public synchronized void run() {
        while (loop) {
            try {
                this.wait();    //threads are waiting here...
            } catch (InterruptedException e) {  //use interrupt method could generate the InterruptedException
                System.out.println(Thread.currentThread().getName() + " InterruptedException...");
                loop = false;
                //interrupt force thread back to runnable state and then change the loop will be ending the thread
            }
            System.out.println(Thread.currentThread().getName() + "...run..." + i++);
        }
    }

    public void changeLoop() {
        loop = false;
    }
}


