package com.csii.springcloud;

import java.util.concurrent.TimeUnit;

//定义资源类
class MyStaticdata
{
	volatile int number =0;
	public  void addTo50() {
		this.number=50;
	}
}

//验证volatile的可见性，用volatile修改的变量保证了各线程和主内存之间的可见性
public class VolatileDemoTest {
	public static void main(String[] args) {
		//SeeOkByVolatile();
	}
//volatile 保证了可见性，及时通知其他线程，主物理内存的值被修改了
	private static void SeeOkByVolatile() {
		MyStaticdata mydata = new MyStaticdata();
		new Thread(() ->{
			System.out.println(Thread.currentThread().getName()+"\t come in");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mydata.addTo50();
			System.out.println(Thread.currentThread().getName()+"\t number:"+mydata.number);
		}, "AAA") .start();
		while(mydata.number==0) {
			
		}
		System.out.println(Thread.currentThread().getName()+"\t 任务结束"+mydata.number);
	}
	
}
