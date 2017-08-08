package com.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by qwj on 2017/8/8.
 */
public class Sample {
    private final static CountDownLatch countDownLatch = new CountDownLatch(2);

    private static class WorkingThread extends Thread{
        private final String mThreadName ;
        private final int mSleepTime;

        private WorkingThread(String mThreadName, int mSleepTime) {
            this.mThreadName = mThreadName;
            this.mSleepTime = mSleepTime;
        }

        public void run() {
            System.out.println("[WorkingThread] started!"+"   线程名字："+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("[WorkingThread] end!"+"   线程名字："+Thread.currentThread().getName());

        }
    }
    private static class SampleThread extends Thread{

        public void run() {
            System.out.println("[SampleThread] start"+"   线程名字："+Thread.currentThread().getName());
            try {
                // 会阻塞在这里等待 mCountDownLatch 里的count变为0；
                // 也就是等待另外的WorkingThread调用countDown()
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[SampleThread] end"+"   线程名字："+Thread.currentThread().getName());

        }
    }
    public static void main(String[] args){
        new SampleThread().start();
        new WorkingThread("workingThread1",1000).start();
        //new WorkingThread("workingThread2",1000).start();
    }
}
