package com.lion.lesson.threadPool;

import java.util.concurrent.*;

public class StudyThreadPool01 {
    static ExecutorService threadPool = Executors.newCachedThreadPool();

    // 推荐，即使任务又有返回值
    static String case01_FutureTask() {

        String result = "";

        FutureTask<String> task = new FutureTask(() -> {
            String data = "FutureTask test";
            return data;
        });

        threadPool.submit(task); // 提交任务到线程池,异步

        try {
            result = task.get(); // 任务获取结果，get()是阻塞的
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }


    static String case02_Callable() {
        String result = "";
        Callable task = () -> {
            String data = "Callable test";
            return data;
        };

        Future<String> future = threadPool.submit(task);

        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(case01_FutureTask());
        System.out.println(case02_Callable());

        threadPool.shutdown();
    }

}
