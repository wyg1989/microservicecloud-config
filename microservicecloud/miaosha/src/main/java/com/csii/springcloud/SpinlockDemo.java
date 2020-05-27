package com.csii.springcloud;
/**
 * 
 * 自旋锁
 * 是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式获取锁，这个样的好处是减少上下文切换的消耗，缺点是循环会消耗cpu
 *
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import ch.qos.logback.core.util.TimeUtil;

public class SpinlockDemo {
	AtomicReference<Thread> atomicReference = new AtomicReference<>();
	public void mylock() {
		Thread thread = Thread.currentThread();
		System.out.println(Thread.currentThread().getName()+"\t come in");
		while(!atomicReference.compareAndSet(null, thread)) {
			//System.out.println(Thread.currentThread().getName()+"\t come in");
		}
	} 
	public void unmylock() {
		Thread thread = Thread.currentThread();
		atomicReference.compareAndSet(thread, null);
		System.out.println(Thread.currentThread().getName()+"\t come out");
		
	}
	public static void main(String[] args) {
		SpinlockDemo spinlockDemo = new SpinlockDemo();
		new Thread(()->{
			spinlockDemo.mylock();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			spinlockDemo.unmylock(); 
		},"AA") .start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(()->{
			
			spinlockDemo.mylock();
			
			spinlockDemo.unmylock();
		},"BB") .start();
	}
}
