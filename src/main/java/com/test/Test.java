package com.test;

/**
 * Created by qwj on 2017/7/28.
 */
public class Test {
    public static void main(String[] args){
        Integer a = new Integer(1);
        Integer b= new Integer(1);
        System.out.println(a == b);
        System.out.println(b.equals(a));
        char[] ss = {'a','b', 'c'};
        for(char s: ss){
            switch (s){
                case 'a':
                    System.out.println(s);
                    break;
                case 'b':
                    System.out.println(s);
                    break;
                case 'c':
                    System.out.println(s);
                    break;
            }
        }
    }
}
