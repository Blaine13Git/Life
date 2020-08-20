package com.lion.lesson.threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 使用当前线程创建独有的ThreadLocalMap，存贮当前线程传递的内容
 */
public class StudyThreadLocal {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        case01();
    }

    static void case01() {

        new Thread(() -> {
            threadLocal.set(1);

            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

        }, "thread01").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }, "thread02").start();
    }

}
