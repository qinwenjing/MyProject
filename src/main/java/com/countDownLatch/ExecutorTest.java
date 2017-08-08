package com.countDownLatch;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qwj on 2017/8/8.
 */
public class ExecutorTest<K> {
    private int threadCounts = 10;
    private ExecutorService executorService = Executors.newFixedThreadPool(threadCounts);

    class ExecuteCallable implements Callable{
        private List<K> list;
        private CountDownLatch countDownLatch;
        public ExecuteCallable(List<K> list, CountDownLatch countDownLatch){
            this.list = list;
            this.countDownLatch = countDownLatch;
        }
        public Object call() throws Exception {
            try{
                execute();

            }catch (Throwable t){
                System.out.println("catch throwable");
                return false;
            }finally {
                countDownLatch.countDown();
            }
            return null;
        }

        private void execute() {
            System.out.println(Thread.currentThread().getName());

        }
    }
}
