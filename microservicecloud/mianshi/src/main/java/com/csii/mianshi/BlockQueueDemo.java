package com.csii.mianshi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 1、队列
 * 2、阻塞队列
 * 2.1 组赛队列有没有好的一面
 * 2.2不得不阻塞，你如何管理
 *
 */
public class BlockQueueDemo {
	public static void main(String[] args) throws Exception {
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
		System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
		System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
	}

}
