package com.lion.lesson.threadPool;

import java.util.concurrent.*;

public class StudyThreadPoolExecutor {

    /*
    线程池的7个参数，线程池推荐使用此种方式。
    */
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            2, // 核心线程数
            4, // 最大线程数
            1, // 线程空闲时间
            TimeUnit.MINUTES, // 时间的单位
            new ArrayBlockingQueue(4), // 线程阻塞队列，容量4个
            Executors.defaultThreadFactory(), // 创建线程的工厂，一般需要自定义，定义一个比较有意义的名称
            new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略，即线程都处于忙碌状态，队列也满员了，执行拒绝策略。JDK默认提供了4种，一般自定义策略。


    public static void main(String[] args) {

        System.out.println(Integer.SIZE); // 32位

    }
}
