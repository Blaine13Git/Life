package com.lion.lesson.threadPool;

import java.util.concurrent.locks.LockSupport;

public class Interview02 {
    // 打印A1B2C3
//    static char[] dataChar = {'A', 'B', 'C', 'D', 'E', 'F'};
//    static char[] dataNum = {'1', '2', '3', '4', '5', '6'};
    static char[] dataChar = "ABCDEF".toCharArray();
    static char[] dataNum = "123456".toCharArray();

    static Thread t1, t2;

    static volatile boolean t2Start = false;

    public static void main(String[] args) {
        case03();
    }

    // 单线程实现
    static void case01() {
        for (int i = 0; i < dataChar.length; i++) {
            System.out.print(dataChar[i] + "" + (i + 1));
        }
    }

    // 两个线程实现--LockSupport
    static void case02() {
        t1 = new Thread(() -> {
            for (int i = 0; i < dataChar.length; i++) {
                System.out.print(dataChar[i]);
                LockSupport.unpark(t2); // 运行t2线程
                LockSupport.park(); // 暂停t1线程
            }
        }, "thread_01");


        t2 = new Thread(() -> {
            for (int i = 0; i < dataNum.length; i++) {
                LockSupport.park();
                System.out.print(dataNum[i]);
                LockSupport.unpark(t1);
            }
        }, "thread_02");

        t1.start();
        t2.start();
    }

    //synchronized的 notify() and wait()
    static Object lock = new Object();

    static void case03() {
        t1 = new Thread(() -> {
            synchronized (lock) {
                t2Start = true;
                for (int i = 0; i < dataChar.length; i++) {
                    System.out.print(dataChar[i]);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify(); // 必须否则不会结束线程
            }
        }, "t1");

        t2 = new Thread(() -> {
            synchronized (lock) {

                // 控制线程启动顺序
                while (!t2Start) {
                    try {
                        lock.notify();
                        lock.wait(); // 释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < dataNum.length; i++) {
                    System.out.print(dataNum[i]);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                lock.notify(); // 必须，否则不会结束线程
            }
        }, "t2");

        t2.start();
        t1.start();
    }

}
