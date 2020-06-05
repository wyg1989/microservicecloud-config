package com.csii.mianshi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
//CyclicBarrier(int parties, Runnable barrierAction) CyclicBarrier 构造方法
	//函数式接口：有且仅有一个抽象方法的接口
	public static void main(String[] args) {
		CyclicBarrier cycb = new CyclicBarrier(7,()->{System.out.println("开始开会了");}) ;
		for(int i=1;i<=7;i++) {
			new Thread(() ->{
				try {
					System.out.println(Thread.currentThread().getName()+"号来了");
					cycb.await();
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
		//System.out.println("开始开会");
	}
}
