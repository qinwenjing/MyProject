package com.guavaTest;

import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by qwj on 2017/8/17.
 */
public class Guava {
    public Guava() throws ExecutionException {
    }

    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        String strProValue = "hello "+key+" !";
                        return strProValue;
                    }
                });
        
        System.out.println("jerry value:"+ cacheBuilder.apply("jerry"));
        System.out.println("jerry value:"+cacheBuilder.get("jerry"));
        System.out.println("peida value:"+cacheBuilder.get("peida"));
        System.out.println("peida value:"+cacheBuilder.apply("peida"));
        System.out.println("lisa value:"+cacheBuilder.apply("lisa"));

        //直接向缓存中插入值，这会直接覆盖掉给定键之前映射的值。
        cacheBuilder.put("harry", "ssdded");
        System.out.println("harry value:"+cacheBuilder.get("harry"));

        System.out.println("------------------------------------");

        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
        /**
         * 所有类型的Guava Cache，不管有没有自动加载功能，都支持get(K, Callable<V>)方法。
         * 这个方法返回缓存中相应的值，或者用给定的Callable运算并把结果加入到缓存中。在整个加载方法完成前，缓存项相关的可观察状态都不会更改。
         * 下面代码简便地实现了模式"如果有缓存则返回；否则运算、缓存、然后返回"。
         */
        String resultVal = cache.get("jerry", new Callable<String>() {
            public String call() throws Exception {
                String strProValue = "hello "+"jerry"+"!";
                return strProValue;
            }
        });
        System.out.println("jerry value :"+resultVal);

        System.out.println("------------------------------------");

        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                String str = "cacheLoader return "+s+" !";
                return str;
            }
        };
        RemovalListener<String, String> removalListener = new RemovalListener<String, String>() {
            public void onRemoval(RemovalNotification<String, String> removalNotification) {
                System.out.println("this is RemovalListener");
            }
        };

        LoadingCache loadingCache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS)
                .removalListener(removalListener)
                .build(loader);
        System.out.println("jerry value:"+loadingCache.get("jerry"));
        System.out.println("peida value:"+loadingCache.get("peida"));
        loadingCache.invalidate("jerry");
        System.out.println("lisa value:"+loadingCache.apply("lisa"));
        System.out.println("qwj value"+loadingCache.get("qwj", new Callable() {
            public Object call() throws Exception {
                return "now return qwj";
            }
        }));

        System.out.println("------------------------------------");

        System.out.println("222222222222222222222222");






    }


}
