/**
 * Created by dinesh.k.masthaiah on 1/22/2018.
 */

public class SimpleDeadLock {
    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread t1 = new Thread() {
            public void run() {
                synchronized (lock1) {
                    System.out.println("1st block of t1 thread");
                    try {
                        Thread.sleep(1000);
                        System.out.println("t1 woken up");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("2nd block of t1 thread");
                    }
                }

            }
        };
        Thread t2 = new Thread() {
            public void run() {
                synchronized (lock2) {
                    System.out.println("1st block of t2 thread");

                    synchronized (lock1) {
                        System.out.println("2nd block of t2 thread");
                    }
                }

            }
        };
        t1.start();
        t2.start();
    }
}
