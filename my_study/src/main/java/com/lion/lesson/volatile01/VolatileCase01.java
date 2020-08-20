package com.lion.lesson.volatile01;

import java.util.concurrent.TimeUnit;

public class VolatileCase01 {

    /**
     * volatile--不稳定的
     * 特点：
     * 1、保证线程可见性（不能保证多线程的原子性，即被修改的值，可以同时被多个线程访问）
     * 在A、B线程中，都使用了共享内存区域的某个变量（running），如果A线程改变共享内存区域的该变量的值，会立即刷新回共享内存中，B线程会拿到刷新后的值。
     * 本质是使用了CPU的缓存一致性协议（MESI），即两个线程运行在不同的cpu上。
     * <p>
     * 2、禁止指令重排序（通过内存屏障（读写），防止对象半初始化，synchronized不能防止指令重排序）
     * 创建对象的步骤在指令操作分三步
     * a、分配内存，赋默认值
     * b、赋值操作（程序中实际指定的值）
     * c、赋值给栈中的变量
     */
    volatile boolean running = true;
//    boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {
        }

        System.out.println("m end");
    }


    public static void main(String[] args) {
        VolatileCase01 case01 = new VolatileCase01();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                case01.m();
            }
        });*/

        // 上面代码的 lambda 表达式简写
        new Thread(case01::m, "case01").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        case01.running = false;
    }
}
