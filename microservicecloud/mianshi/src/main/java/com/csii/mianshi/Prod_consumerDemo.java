package com.csii.mianshi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统的生产者消费者多线程模式
 * @author wyg17
 *题目：一个初始值为0的变量，通过两个线程操作，一个增加，一个减少，循环5论
 *线程   操作 资源类
 *判断  干活  通知唤醒
 *严防多线程并发状态下的虚假唤醒
 */
class ShareData{
	private int number =0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();//?干嘛的，没懂
	
	public void increame() {
		//多线程一定要加锁
		lock.lock();
		try {
			//多线程的循环判断用while
			//判断
			while(number != 0) {
				condition.await();
			}
			//干活
			number++;
			System.out.println(Thread.currentThread().getName()+" "+number);
			//通知唤醒
			condition.signalAll();//唤醒线程 用signal或者signalAll替换notify(线程的唤醒方法)
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	public void decreame() {
		//多线程一定要加锁
		lock.lock();
		try {
			//多线程的循环判断用while
			//判断
			while(number == 0) {
				condition.await();
			}
			//干活
			number--;
			System.out.println(Thread.currentThread().getName()+" "+number);
			//通知唤醒
			condition.signalAll();//唤醒线程 用signal或者signalAll替换notify(线程的唤醒方法)
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}
public class Prod_consumerDemo {
	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		new Thread(() ->{
			for(int i=0;i<5;i++) {
				shareData.increame();
			}
		},"AA").start();
		new Thread(() ->{
			for(int i=0;i<5;i++) {
				shareData.decreame();
			}
		},"BB").start();
	}
}
