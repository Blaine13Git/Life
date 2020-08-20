package com.lion.lesson.volatile01;

import java.util.concurrent.TimeUnit;

public class InterviewAli03 {

    int num = 0;

    public static void main(String[] args) {
        InterviewAli03 case01 = new InterviewAli03();
        final Object lock = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    case01.num++;
                    System.out.println(case01.num);
                    if (case01.num == 5) {
                        lock.notify(); // notify()唤醒等待中的线程，但是不释放锁，通常wait()和notify()成对出现。
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");

            synchronized (lock) {
                if (case01.num != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");
                lock.notify();
            }
        }, "thread t2");

        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }
}
