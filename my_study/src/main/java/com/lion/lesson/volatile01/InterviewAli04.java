package com.lion.lesson.volatile01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class InterviewAli04 {

    volatile int num = 0;

    public static void main(String[] args) {
        InterviewAli04 case01 = new InterviewAli04();

        CountDownLatch latch = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");

            for (int i = 0; i < 10; i++) {
                case01.num++;
                System.out.println(case01.num);

                if (case01.num == 5) {
                    latch.countDown(); // latch的值：1-1=0，开始运行
                }

                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            if (case01.num != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end");
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
