package com.csii.springcloud;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//验证volatile和JMM的可见性
/**
 * 1、可见性
 *	 1.1 加入int number=0；number变量之前根本没有添加volatile关键字修饰 ，没有可见性
 *	 1.2 volatile 保证了可见性
 * 2、不保证原子性
 * 2.1 为什么不能保证原子性  出现重写主内存的变量值 线程速度太快超过volatile的可见性速度（及时通知机制）
 * 3解决不保证原子性
 * 3.1 使用synchronized 但是synchrdonized太重，杀鸡不能用宰牛刀
 * 3.2 使用JUC中java.util.concurrent.atomic 中的AtomicInteger 这些api是有自增的方法
 *
 *
 */
class Mydata
{
	volatile int number = 0; //volatile 关键字保证了各线程和主内存之间的可见性
	public void addTo60(){
		this.number=60;
	}
	public    void addPlusPlus() {
		number++;
	}
	AtomicInteger atomicInteger = new AtomicInteger();	
	public void addAtomic() {
		atomicInteger.getAndIncrement();
	}
	
}
public class VolatileDemo {
	public static void main(String[] args) {
		//SeeOkByVolatile();
		Mydata mydata = new Mydata();//资源类
		for(int i=1;i<=20;i++) {
			new Thread(() ->{
				for(int j=1;j<=1000;j++) {
					mydata.addPlusPlus();
					mydata.addAtomic();
				}
			},String.valueOf(i)).start();
		}
		//计算线程等待时间的最好方法，2一个是main线程，一个是gc线程 如果大于2表示上面的线程还没有执行完，Thread.yield表示等待
		while(Thread.activeCount()>2) {
			Thread.yield();
		}
		System.out.println("aaaaaaa"+mydata.number);
		System.out.println(Thread.currentThread().getName()+"\t 任务完成，main get number："+mydata.atomicInteger);
	}
//volatile保证可见性，及时通知其他线程，主物理内存的值被修改了
	private static void SeeOkByVolatile() {
		Mydata mydata = new Mydata();//资源类
		//new Thread(target, name)
		new Thread(() ->{
			System.out.println(Thread.currentThread().getName()+"\t come in");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mydata.addTo60();
			System.out.println(Thread.currentThread().getName()+"\t update number value ="+mydata.number);
		},"AAA").start();
		//第二个线程就是我们main线程
		while(mydata.number==0) {
			//System.out.println("main线程");
		}
		System.out.println(Thread.currentThread().getName()+"\t 任务完成，main get number："+mydata.number);
		
	}

}
