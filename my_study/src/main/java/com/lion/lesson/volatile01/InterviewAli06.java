package com.lion.lesson.volatile01;

import java.util.concurrent.locks.LockSupport;

public class InterviewAli06 {
    // 添加volatile，使t2线程能够得到通知
    volatile int num = 0;
    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        InterviewAli06 case01 = new InterviewAli06();

        t1 = new Thread(() -> {
            System.out.println("t1 start");

            for (int i = 0; i < 10; i++) {
                case01.num++;

                System.out.println(case01.num);

                if (case01.num == 5) {
                    LockSupport.unpark(t2); // 开走
                    LockSupport.park(t1); // 停
                }
            }
            System.out.println("t1 end");
        }, "thread t1");

        t2 = new Thread(() -> {
            System.out.println("t2 start");

            LockSupport.park(); // 停放

            System.out.println("t2 end");

            LockSupport.unpark(t1); // 开走

        }, "thread t2");

        t2.start();
        t1.start();
    }
}
