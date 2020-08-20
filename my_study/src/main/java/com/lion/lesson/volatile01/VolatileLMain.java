package com.lion.lesson.volatile01;

public class VolatileLMain {

    private int num = 100;

    public synchronized void m() {
        boolean flag =true;
        while (flag) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num--;
            System.out.println(Thread.currentThread() + ": num = " + num);
            if (num==0) {
                flag=false;
            }
            if (num == 80) {
                int i = num / 0;
            }
        }
    }


    public static void main(String[] args) {
        VolatileLMain volatileLMain = new VolatileLMain();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                volatileLMain.m();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable, "thread 1").start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(runnable, "thread 2").start();


    }
}
