package com.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by qwj on 2017/8/17.
 */
public class TestOrder {
    public static void main(String[] args){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(0);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        TestOrder to = new TestOrder();
        list = to.filter(list);
        System.out.println("filter after"+list);

    }
    public List filter(List list){
        List list1 = new ArrayList();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(1)||list.get(i).equals(2)){
                list1.add(list.get(i));
            }
        }
        return list1;
    }
}
