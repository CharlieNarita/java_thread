package com.charlie.thread.practice.producer_consumer.producer_consumer_jdk5_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * after JDK 1.5, there is interface Lock instead of synchronized
 */
public class ProducerConsumerLockDemo {
    public static void main(String[] args) {
        Resource computer = new Resource();
        new Thread(new Producer(computer)).start();
        new Thread(new Producer(computer)).start();
        new Thread(new Consumer(computer)).start();
        new Thread(new Consumer(computer)).start();
    }
}

class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;
    /**
     * the Re_entrant lock is implementation of interface Lock
     * create Lock instance first
     * then get Condition instance through Lock instance
     */
    private Lock lock = new ReentrantLock();

    private Condition condition_producer = lock.newCondition();  //Condition instance create by lock
    private Condition condition_consumer = lock.newCondition();  //Condition instance create by lock

    /**
     * @param name
     * @throws InterruptedException
     */
    /*
        1.lock.lock();
        2.condition.await();
        3.condition.signal();
        4.lock.unlock();
        attention try and finally
     */
    public void produce(String name) throws InterruptedException {
        lock.lock();    //lock() is like synchronized
        try {
            while (flag)    //while instead if
                condition_producer.await(); //all producer await here
            this.name = name + " " + count++;
            System.out.println(Thread.currentThread().getName() + " produce---@ " + this.name);
            this.flag = true;
            condition_consumer.signal(); //signal() is like notify(); signalAll() is like notifyAll()
        } finally {
            lock.unlock();  //unlock() is release lock
        }
    }

    /**
     * @throws InterruptedException
     */
    /*
        1.lock.lock();
        2.condition.await();
        3.condition.signal();
        4.lock.unlock();
        attention try and finally
     */
    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (!flag)   //while instead if
                condition_consumer.await(); //all consumer await here
            System.out.println(Thread.currentThread().getName() + " consume...$ " + this.name);
            this.flag = false;
            condition_producer.signal();    //awaken all producer
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private Resource res;

    public Producer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                res.produce("Computer");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Resource res;

    public Consumer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                res.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
