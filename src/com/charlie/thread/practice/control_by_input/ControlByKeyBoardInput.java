package com.charlie.thread.practice.control_by_input;

import java.util.Scanner;

/**
 * 1.run 2 threads with main thread
 * 2.loop print random integer 0~100 in first thread
 * 3.terminate first thread until read "esc" key input by second thread
 */
public class ControlByKeyBoardInput {
    public static void main(String[] args) {
        Looper looper = new Looper();
        LooperController looperController = new LooperController(looper);

        looper.start();
        looperController.start();
    }
}

class Looper extends Thread {
    private boolean loop = true;

    @Override
    public void run() {
        //output 1~100
        while (loop) {
            //random integer
            System.out.println((int) (Math.random() * 100 + 1));
            //sleep
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("looper thread terminate");
    }

    //setLoop can modify loop attribute value
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}

//build a controller or proxy class for looper
class LooperController extends Thread {
    private Looper target = null;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            //receive keyboard input
            System.out.println("please enter the order('Q' is quit): ");
            char c = scanner.next().toUpperCase().charAt(0);
            if ('Q' == c) {
                target.setLoop(false);
                break;
            }
        }
        System.out.println("controller thread terminate");
    }

    //constructor init target
    public LooperController(Looper target) {
        this.target = target;
    }
}


