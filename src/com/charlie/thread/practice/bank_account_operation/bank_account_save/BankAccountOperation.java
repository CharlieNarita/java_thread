package com.charlie.thread.practice.bank_account_operation.bank_account_save;

public class BankAccountOperation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        new Customer(account).start();
        new Customer(account).start();
        new Customer(account).start();
    }
}

class BankAccount {
    private int deposit = 0;

    void add(int n) {
        synchronized (this) {
            deposit += n;
            System.out.println(Thread.currentThread().getName()
                    + " save $" + n + ", deposit = " + deposit);
        }
    }
}

class Customer extends Thread {
    private BankAccount account;
    private int money = 100;

    public Customer(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        save(money);
    }

    private void save(int n) {
        for (int i = 0; i < 10; i++) {
            account.add(n);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}




