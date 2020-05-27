package com.csii.springcloud;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 
 * @author wyg17
 * 线程不安全问题
 * ArrayList 
 *
 */
public class ContainerNotSafeDemo {
	public static void main(String[] args) {
		//ListNotSafe();
		//SetNotSafe();
		Map map = new ConcurrentHashMap();	
		for(int i=0;i<30;i++) {
			new Thread(() ->{
				map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			},String.valueOf(i)) .start();
		}
		
	}

	private static void SetNotSafe() {
		/**
		 * 1、解决方案
		 * 1、1 Collections.synchronizedSet(new HashSet());
		 * 1、2 Set<String> set = new CopyOnWriteArraySet<String>(); 
		 * 		他的底层构造方法中用的还是CopyOnWriteArrayList
		 */
		Set<String> set1 = new HashSet<>();
		//Set<String> set = Collections.synchronizedSet(new HashSet());
		Set<String> set = new CopyOnWriteArraySet<String>();
		for(int i=0;i<30;i++) {
			new Thread(() ->{
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			},String.valueOf(i)) .start();
		}
	}

/**
 * ArrayList线程不安全
 */
	private static void ListNotSafe() {
		/**
		 * 
		 * 1、故障现象java.util.ConcurrentModificationException 并发修改异常
		 * 2、导致原因
		 * 	并发争抢导致，参考花名册
		 * 3、解决方案
		 * 	3.1、new Vector()
		 * 	3.2、List<String> list = Collections.synchronizedList(new ArrayList());
		 * 	 创建一个线程安全的ArrayList
		 *  3.3、 new CopyOnWriteArrayList(); 写时复制，主要时读写分离的思想
		 *  
		 *  
		 * 4、优化建议
		 * 
		 * 
		 */
		List<String> list = new CopyOnWriteArrayList<String>();
		
		for(int i=0;i<30;i++) {
			new Thread(() ->{
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			},String.valueOf(i)) .start();
		}
	}
}
