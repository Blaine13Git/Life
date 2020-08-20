package com.lion.lesson.threadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StudyThreadPool02 {


    public static void main(String[] args) {
        /*
        线程池的7个参数，线程池推荐使用此种方式。
        */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, // 核心线程数
                4, // 最大线程数，核心线程数和队列都被占用，创建非核心线程，可查看源码得到c d d
                1, // 线程空闲时间
                TimeUnit.MINUTES, // 时间的单位
                new ArrayBlockingQueue(4), // 线程阻塞队列，容量4个
                Executors.defaultThreadFactory(), // 创建线程的工厂，一般需要自定义，定义一个比较有意义的名称
                new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略，即线程都处于忙碌状态，队列也满员了，执行拒绝策略。JDK默认提供了4种，一般自定义策略。

        for (int i = 0; i < 8; i++) {
            poolExecutor.execute(new Task(i));
        }

        System.out.println("queue:" + poolExecutor.getQueue());

        poolExecutor.execute(new Task(111));
        System.out.println("queue2:" + poolExecutor.getQueue());

        poolExecutor.shutdown();

    }

    static class Task implements Runnable {
        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + ": Task-" + num);

            try {
                System.in.read(); // 阻塞,方便查看队列情况
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public String toString() {
            return "Task{" + "num=" + num + '}';
        }
    }

}
