package com.copuMemberCountD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Created by qwj on 2017/8/8.
 */
public class UpgradeTest extends WorkThreadTemplateServiceV2<String> {

    public void start(List<String> items) throws Throwable{

        CountDownLatch countDownLatch = new CountDownLatch(getThreadcounts());
        PaginationGson<String> pagination = new PaginationGson(10,items);
        List<Future> results = new ArrayList();

        for(int i=1;i<=10;i++){
            List<String> list = new ArrayList();
            try {
                list = pagination.getNextPage(i);
            }finally {
                results.add(submit(list,  countDownLatch));
            }
        }
        countDownLatch.await();
        System.out.println("UpgradeTest start");
       }

    protected void execute(List<String> items) {
        for(String m:items){
            try{
                Thread.sleep(1000);
                System.out.println(new Date()+"   m : "+m);

            }catch(Throwable t){
                System.out.println("UpgradeTest catch");
            }

        }
    }

    public static void main(String[] args) throws Throwable {
        List<String> items = new ArrayList<String>();
        for(int i = 0; i < 50; i++){
            items.add("i="+i);
        }

        UpgradeTest upgradeTest = new UpgradeTest();
        upgradeTest.start(items);

    }
}
