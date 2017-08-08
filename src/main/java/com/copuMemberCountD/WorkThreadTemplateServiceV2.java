package com.copuMemberCountD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qwj on 2017/8/8.
 */
public abstract class WorkThreadTemplateServiceV2 <K>{
    private static final Logger logger = LoggerFactory.getLogger(WorkThreadTemplateServiceV2.class);

    private int threadcounts = 2;
    private final AtomicInteger counter = new  AtomicInteger();
    private ExecutorService executor = Executors.newFixedThreadPool(threadcounts);


    public Future<Boolean> submit(List<K> list,  CountDownLatch countDownLatch){
        return executor.submit(new ExeuteCallable(list,countDownLatch));
    }
    protected abstract void execute(List<K> item);
    class ExeuteCallable implements Callable<Boolean> {
        private List<K> list;
        private CountDownLatch countDownLatch;
        ExeuteCallable(List<K> list,CountDownLatch countDownLatch){
            this.list=list;
            this.countDownLatch = countDownLatch;
        }

        public Boolean call() throws Exception {
            try{
                execute(list);
                counter.addAndGet(list.size());
                System.out.println(Thread.currentThread().getName()+"   list.size()="+list.size()+"   counter="+counter);
                return true;
            }catch(Throwable t){
                //logger.error(String.format("Failed to exeute runnable r [r:%s]",r), t);
                return false;
            }finally{
                countDownLatch.countDown();
                logger.debug(Thread.currentThread().getName()+" countDown:"+counter.get());
            }
        }
    }

    public int getThreadcounts() {
        return threadcounts;
    }

}
