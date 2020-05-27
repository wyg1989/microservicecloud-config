package com.csii.springcloud;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 
 * @author wyg17
 * ABA问题的解决 AtomicStampReference 时间戳原子引用
 *
 */
public class AtomicStampReferenceDemo {
	static AtomicReference<Integer>  atomicReference = new AtomicReference<Integer>(100);
	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);
	public static void main(String[] args) {
		System.out.println("=============以下是ABA问题的产生=============");
		new Thread(()->{
			atomicReference.compareAndSet(100, 101);
			atomicReference.compareAndSet(101, 100);
			System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.get());
		},"t1").start();
	
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			atomicReference.compareAndSet(100,2019);
			System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.get());
		},"t2").start();
		System.out.println("=============以下是ABA问题的解决=============");
		
		new Thread(()->{
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t 第2次版本号"+atomicStampedReference.getStamp());
			atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t 第3次版本号"+atomicStampedReference.getStamp());
		},"t3").start();
		
		new Thread(()->{
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp);
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t"+result+"当前最新值"+atomicStampedReference.getReference());
		},"t4").start();
	}
	
}
