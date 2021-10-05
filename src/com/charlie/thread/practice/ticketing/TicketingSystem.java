package com.charlie.thread.practice.ticketing;

public class TicketingSystem {
    public static void main(String[] args) {
//        //just one object, so consider attribute tickets is not static
//        Ticketing ticketing = new Ticketing();
//
//        //create threads
//        new Thread(ticketing).start();
//        new Thread(ticketing).start();
//        new Thread(ticketing).start();
//        new Thread(ticketing).start();
//        new Thread(ticketing).start();

        new SellingWindow().start();
        new SellingWindow().start();
        new SellingWindow().start();
        new SellingWindow().start();
        new SellingWindow().start();

    }
}

//first way implements Runnable
class Ticketing implements Runnable {
    //in this case just create one object, so need not use static
    private int tickets = 100;
    private boolean loop = true;

    //sell() is a synchronized method
    //now the synchronized lock is add on this object(the object who invoke the sell())
    private /*synchronized*/ void sell() {

        //add synchronized on code block
        //non_static method add lock on this object(the object who invoke the sell())
        synchronized (this) {
            if (tickets <= 0) {
                System.out.println("tickets over");
                loop = false;
                return; //must return sell method, otherwise oversell will occur
            }
            //wait 1 second...
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //sell
            System.out.println(Thread.currentThread().getName() +
                    " sell a ticket, remain tickets = " +
                    (--tickets));
        }
    }

    @Override
    public void run() {
        while (loop) {
            sell(); //sell is synchronized method
        }
    }
}

//second way to extends Thread
class SellingWindow extends Thread {
    //define static attributes
    private static int tickets = 100;
    private static boolean loop = true;

    private static void sell() {
        //add synchronized on code block
        //static method add lock on sellingWindow.class!!!
        synchronized (SellingWindow.class) {
            if (tickets <= 0) {
                System.out.println("tickets over");
                loop = false;
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +
                    " sell a ticket, remain tickets = " +
                    (--tickets));
        }
    }

    @Override
    public void run() {
        while (loop) {
            sell();
        }
    }
}
