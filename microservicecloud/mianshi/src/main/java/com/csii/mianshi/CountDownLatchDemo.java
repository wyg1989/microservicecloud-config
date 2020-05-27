package com.csii.mianshi;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	 
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdLatch = new CountDownLatch(6);
		for(int i=1;i<=6;i++) {
			new Thread(() ->{
				
				System.out.println(Thread.currentThread().getName()+"被灭");
				cdLatch.countDown();
			},CountyEnum.forEach_CountyEnum(i).getRetMessage()).start();
		}
		cdLatch.await();
		System.out.println(Thread.currentThread().getName()+"秦灭六国，统一中国");
		System.out.println(CountyEnum.one);
		
	}

}
