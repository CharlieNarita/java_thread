package com.charlie.thread.demo;

/**
 * encapsulate all synchronized code into monitor object methods
 */

public class ThreadsCommunicationDemo {
    public static void main(String[] args) throws InterruptedException {
        Res res = new Res();
        new Thread(new Input(res)).start();
        new Thread(new Output(res)).start();
    }
}

class Res {
    private String name;
    private String gender;
    private boolean flag = false;

    /**
     * @param name
     * @param gender encapsulate all synchronized code into this method
     *               the monitor object is "this"
     */
    public synchronized void setPara(String name, String gender) {
        if (flag) {
            try {
                this.wait();    //put current thread into thread pool
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        this.gender = gender;
        flag = true;
        this.notify();  //awaken other thread
    }

    /**
     * encapsulate all synchronized code into this method
     * the monitor object is "this"
     */
    public synchronized void printInfo() {
        if (!flag)
            try {
                this.wait();    //put current thread into thread pool
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(this.name + "..." + this.gender);
        this.flag = false;
        this.notify();  //awaken other thread
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class Input implements Runnable {
    private Res r;

    public Input(Res r) {
        this.r = r;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            if (x == 0)
                r.setPara("mary", "F");
            else
                r.setPara("jack", "M");
            x = (x + 1) % 2;
        }
    }
}

class Output implements Runnable {
    private Res r;

    public Output(Res r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.printInfo();
        }
    }
}


