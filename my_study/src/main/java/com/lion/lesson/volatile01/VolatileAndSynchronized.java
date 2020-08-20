package com.lion.lesson.volatile01;

import java.util.ArrayList;

public class VolatileAndSynchronized {

    volatile int a = 0;
//    int a = 0;

    synchronized void m() {
        for (int i = 0; i < 1000000; i++) {
            a++;
        }

    }

    public static void main(String[] args) {


        VolatileAndSynchronized vas = new VolatileAndSynchronized();

        ArrayList<Thread> threads = new ArrayList();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(vas::m, "thread" + i));
        }

        threads.forEach(thread -> thread.start());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(vas.a);
    }


}
