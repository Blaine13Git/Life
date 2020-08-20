package com.lion.lesson.volatile01;

import java.util.concurrent.TimeUnit;

public class InterviewAli01 {

    volatile int num = 0;

    void numAdd() {
        for (int i = 0; i < 10; i++) {
            num++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
        }
    }


    public static void main(String[] args) {

        InterviewAli01 case01 = new InterviewAli01();

        Thread t1 = new Thread(() -> case01.numAdd(), "thread t1");

        Thread t2 = new Thread(() -> {
            System.out.println("listening");
            while (true) {
                System.out.println("num = " + case01.num);
                if (case01.num == 4) {
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("num 已经等于了 " + case01.num);
        }, "thread t2");

        t1.start();
        t2.start();

    }
}
