package com.csii.springboot06;

/**
 * 死锁：两个或者两个以上的进程查询
 */
public class DeadLockDemo {

    private static int num =10;
    static{
        num = 20;

    }
}
