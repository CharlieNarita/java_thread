package com.charlie.thread.demo;

public class ThreadExit {
    public static void main(String[] args) throws Exception {
        TE te = new TE();
        te.start();

        System.out.println("main thread sleep 10 seconds...");
        Thread.sleep(10000);

        te.setLoop(false);
    }
}

class TE extends Thread {
    private int count = 0;
    //define a control variable
    private boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("TE is running..." + (++count));
        }
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
