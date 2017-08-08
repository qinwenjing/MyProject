package com.thread;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by qwj on 2017/8/7.
 */
public class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }

    public String call() throws Exception {
        System.out.println("call 方法被调用,线程名字为："+Thread.currentThread().getName()+"   id="+id);
        if(new Random().nextBoolean()){
            throw new TaskException(Thread.currentThread().getName()+"出现异常！");
        }
        for(int i= 0; i <99999; i++);
        String returnString = "call 方法调用结束， 线程名字为："+Thread.currentThread().getName()+"   id="+id;
        return returnString;
    }
}
