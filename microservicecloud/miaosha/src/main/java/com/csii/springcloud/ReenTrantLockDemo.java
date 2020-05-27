package com.csii.springcloud;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 *在同一线程外层方法获取锁的时候，在内存方法会自动获取锁
 *也就是说：线程可以进入任何一个他已经拥有的锁所同步着的代码快
 *
 */
class phone implements Runnable{
	Lock lock = new ReentrantLock();
	@Override
	public void run() {
		get();
		
	}
	private void get() {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			 
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
		
	}
	private void set() {
		lock.lock();
		lock.lock();
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
			lock.unlock();
		}
		
		
	}


	
}
public class ReenTrantLockDemo {

}
