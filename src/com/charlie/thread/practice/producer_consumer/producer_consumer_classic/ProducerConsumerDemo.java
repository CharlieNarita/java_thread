package com.charlie.thread.practice.producer_consumer.producer_consumer_classic;

/**
 * this significant case is typical multi-threads example
 * it is simulating multi-threads visit one source
 * and how avoid non-synchronized fault
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(new Producer(resource)).start();
        new Thread(new Producer(resource)).start();
        new Thread(new Consumer(resource)).start();
        new Thread(new Consumer(resource)).start();
    }
}

/**
 * must use while to detect flag value
 * cause while can detect for loop that avoid waiting threads running directly
 * and then use notifyAll method avoid all threads are waiting
 */
class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void produce(String name) {
        while (flag) {   //use While instead of if, cause If just detect once time
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + "-" + count++;
        System.out.println(Thread.currentThread().getName() + "...produce..." + this.name);
        this.flag = true;
        this.notifyAll();   //invoke all that avoid all thread waiting
    }

    public synchronized void consume() {
        while (!flag) { //use While instead of if, cause If just detect once time
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "...consume..." + this.name);
        this.flag = false;
        this.notifyAll();   //invoke all that avoid all thread waiting
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res.produce("Laptop");
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res.consume();
        }
    }
}