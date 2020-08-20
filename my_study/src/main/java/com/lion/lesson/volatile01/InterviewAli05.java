package com.lion.lesson.volatile01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class InterviewAli05 {

    volatile int num = 0;

    public static void main(String[] args) {
        InterviewAli05 case01 = new InterviewAli05();

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            if (case01.num != 5) {
                LockSupport.park();
            }
            System.out.println("t2 end");
        }, "thread t2");

        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");

            for (int i = 0; i < 10; i++) {
                case01.num++;
                System.out.println(case01.num);

                if (case01.num == 5) {
                    LockSupport.unpark(t2);
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("t1 end");
        });

        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();
    }
}
