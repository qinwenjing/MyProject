package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by qwj on 2017/8/7.
 */
public class ExecutorServiceTest {
    public static void main(String[] args){
        //ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> resultFuture = new ArrayList();
        for(int i = 0; i < 10; i++){
            Future<String> future = executorService.submit(new TaskWithResult(i));
            resultFuture.add(future);
        }
        for(Future<String> fs : resultFuture){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
