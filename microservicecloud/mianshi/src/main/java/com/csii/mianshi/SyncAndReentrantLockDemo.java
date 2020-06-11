package com.csii.mianshi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author wyg17
 * 题目：多线程之间顺序调用，实现A->B->C要求如下
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。
 * 来10轮
 *
 *多线程 操作 资源类
 *判断 干活 通知唤醒
 *绑定多个Condition 
 */

/**
 * 
 *资源类
 */
class ShareResource{
	private int number=1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	public void print5() {
		lock.lock();
		try {
			while(number !=1) {
				c1.await();
			}
			number = 2;
			for(int i=1;i<=5;i++ ) {
				System.out.println(Thread.currentThread().getName()+" "+i);
			}
			c2.signal();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	public void print10() {
		lock.lock();
		try {
			while(number !=2) {
				c2.await();
			}
			number = 3;
			for(int i=1;i<=10;i++ ) {
				System.out.println(Thread.currentThread().getName()+" "+i);
			}
			c3.signal();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	public void print15() {
		lock.lock();
		try {
			while(number !=3) {
				c3.await();
			}
			number = 1;
			for(int i=1;i<=15;i++ ) {
				System.out.println(Thread.currentThread().getName()+" "+i);
			}
			c1.signal();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
}
public class SyncAndReentrantLockDemo {

	public static void main(String[] args) {
		ShareResource sResource = new ShareResource();
		new Thread(() ->{
			for(int i=0;i<10;i++) {
				sResource.print5();
			}
			
		},"A").start();
		new Thread(() ->{
			for(int i=0;i<10;i++) {
				sResource.print10();
			}
			
		},"B").start();
		new Thread(() ->{
			for(int i=0;i<10;i++) {
				sResource.print15();
			}
			
		},"C").start();
	}

}
