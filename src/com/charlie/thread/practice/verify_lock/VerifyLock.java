package com.charlie.thread.practice.verify_lock;

public class VerifyLock {
    public static void main(String[] args) throws InterruptedException {

        Ticketing ticketing = new Ticketing();

        new Thread(ticketing).start();

        Thread.sleep(100);   //main thread sleep
        ticketing.setFlag(false);
        new Thread(ticketing).start();
    }
}

class Ticketing implements Runnable {
    /**
     * attention if tickets is static, the synchronized reference will be (Ticketing.class)
     */
    private int tickets = 100;
    private final Object LOCK = new Object();
    boolean flag = true;
    boolean loop = true;


    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        if (flag) {
            while (loop) {
                sellA();
            }
        } else
            while (loop)
                sellB();
    }

    /**
     * sellA synchronized reference is LOCK
     */
    private void sellA() {
        synchronized (LOCK) {
            if (tickets > 0) {
                //sleep
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "...sellA: " + tickets--);
            } else if (tickets <= 1) {
                setLoop(false);
            }
        }
    }

    /**
     * sellB synchronized reference is this
     */
    private void sellB() {
        synchronized (this) {
            if (tickets > 0) {
                //sleep
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "...sellB: " + tickets--);
            } else if (tickets <= 1) {
                setLoop(false);
            }
        }
    }
}


