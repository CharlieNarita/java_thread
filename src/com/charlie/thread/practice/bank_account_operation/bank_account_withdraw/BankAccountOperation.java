package com.charlie.thread.practice.bank_account_operation.bank_account_withdraw;

public class BankAccountOperation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Thread user1 = new Thread(account);
        Thread user2 = new Thread(account);
        user1.setName("user_1");
        user2.setName("user_2");

        user1.start();
        user2.start();
    }
}

class BankAccount implements Runnable {
    private int deposit = 10000;
    private int withdrawal = 1000;
    private final Object LOCK = new Object();

    public BankAccount(int deposit, int withdrawal) {
        this.deposit = deposit;
        this.withdrawal = withdrawal;
    }

    public BankAccount() {
    }

    @Override
    public void run() {
        while (true) {
            //threads scramble for lock(equals to 'this')
            synchronized (LOCK) {
                if (deposit <= 0) {
                    System.out.println("account is empty ");
                    break;
                } else if (deposit < withdrawal) {
                    System.out.println("account is not enough");
                    break;
                }
                //withdraw money
                withdraw();

                //wait time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void withdraw() {
        System.out.println(Thread.currentThread().getName()
                + " withdraw $" + withdrawal + "... account remains = "
                + (deposit -= withdrawal));
    }
}
