package com.charlie.thread.practice.singleton_pattern;

public class SingletonPatternDemo {
    public static void main(String[] args) {

        SingleLazy s = SingleLazy.getInstance();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SingleLazy s1 = SingleLazy.getInstance();
            }
        }).start();
    }
}

/**
 * single lazy pattern
 * use synchronized avoid multi-instance fault
 */
class SingleLazy {
    private static SingleLazy s = null;

    private SingleLazy() {
        System.out.println(Thread.currentThread().getName() + " new single instance...");
    }

    public static SingleLazy getInstance() {
        /**
         * here are two if double check
         * improve the efficiency
         * cause avoid threads enter synchronized every time
         */
        if (s == null) {
            synchronized (SingleLazy.class) {
                if (s == null) {
                    s = new SingleLazy();
                }
            }
        }
        return s;
    }
}


/**
 * single huger pattern
 * init an instance as static final declare
 * method getInstance return the instance
 */
class SingleHuger {
    private static final SingleHuger S = new SingleHuger();

    private SingleHuger() {
    }

    public static SingleHuger getInstance() {
        return S;
    }
}
