package com.lion.lesson.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试写时复制CopyOnWrite
 */
public class StudyCopyOnWrite {
    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<>(); //耗时：4865 ，lists.size = 100000 ，底层是ReentrantLock CAS
//        List<String> lists = new Vector<>(); // 耗时：82    lists.size = 100000 ，底层是synchronized
//        List<String> lists = new ArrayList<>(); // 耗时：80    lists.size = 99511 存在并发问题
        Random random = new Random();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            Runnable task = () -> {
                for (int j = 0; j < 1000; j++) {
                    lists.add("a" + random.nextInt(10000));
                }
            };

            threads[i] = new Thread(task);
        }

        runAndComputeTime(threads);
        System.out.println("lists.size = " + lists.size());
    }

    static void runAndComputeTime(Thread[] threads) {
        long start = System.currentTimeMillis();

        Arrays.asList(threads).forEach(thread -> thread.start());

        Arrays.asList(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

}
