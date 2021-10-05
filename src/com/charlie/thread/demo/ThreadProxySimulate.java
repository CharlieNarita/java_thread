package com.charlie.thread.demo;

/**
 * simulation of class Thread, use proxy design pattern
 */
public class ThreadProxySimulate {
    public static void main(String[] args) {
        Runnable threadTest = new ThreadTest();
        ThreadProxy threadProxy = new ThreadProxy(threadTest);
        threadProxy.start();    //actually call start0();
    }
}

class ThreadProxy implements Runnable {
    private Runnable target = null;

    /*
    the question is this override run() looks like useless?
    what situation can be invoked this run() method?
     */
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();   //invoke start0
    }

    /*
    simulation start0 is the most important method of all
    because truly start0 is native, can create real multi-thread
     */
    public void start0() {
        target.run();
    }
}

class ThreadTest implements Runnable {
    @Override
    public void run() {
        System.out.println("ThreadTest running ...");
    }
}
