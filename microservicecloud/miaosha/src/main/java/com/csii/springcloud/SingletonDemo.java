package com.csii.springcloud;

public class SingletonDemo {
private static volatile SingletonDemo instance = null;
private SingletonDemo(){
	System.out.println(Thread.currentThread().getName()+"我是构造方法");
	

}
//DCL(double check lock 双端检索机制)
	public static SingletonDemo getInstance() {
	
		if(instance==null) { //1、加锁前判断
			synchronized (SingletonDemo.class) {//同步代码上加锁
				if(instance==null) {//2、枷锁后判断
					instance = new SingletonDemo();
				}
			}
		}
		return instance; 
	}
	public static void main(String[] args) {
		System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
		System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
		System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
		
		for(int i=1;i<=20;i++) {
			new Thread(() ->{
				
				SingletonDemo.getInstance();
				
			},String.valueOf(i)).start();;
		}
	}
	
}
