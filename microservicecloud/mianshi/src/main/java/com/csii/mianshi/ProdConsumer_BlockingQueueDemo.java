package com.csii.mianshi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 
 *
 */
class ShareResource1{
	/**
	 * 资源类
	 */
	private  volatile boolean Flag=true;
	private AtomicInteger atomicInteger = new AtomicInteger();//初始化为0
	private BlockingQueue<String> blockingQueue = null;
	public ShareResource1(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	//生产
	public void prod() throws InterruptedException {
		String data = null;
		boolean returnValue=true;
		while(Flag) {
			data = atomicInteger.getAndIncrement()+"";
			returnValue = blockingQueue.offer(data, 2l, TimeUnit.SECONDS);
			if(returnValue) {
				System.out.println(Thread.currentThread().getName()+data+"插入数据成功");
			}else {
				System.out.println(Thread.currentThread().getName()+"插入数据失败");
			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(Thread.currentThread().getName()+"叫停了");
	}
	//消费
	public void consumer() throws InterruptedException {
		String result=null;
		while(Flag) {
			result = blockingQueue.poll(2l, TimeUnit.SECONDS);
			
			if(result==null ||result.equalsIgnoreCase("")) {
				Flag=false;
				System.out.println(Thread.currentThread().getName()+"超过2秒没有取到数据，消费退出");	
			return;
			}
			System.out.println(Thread.currentThread().getName()+result+"消费数据成功");
		}
				
	}
	//叫停
	public void stop() {
		Flag=false;
	}
	
}
	
public class ProdConsumer_BlockingQueueDemo {
	public static void main(String[] args) {
		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
		ShareResource1 shareResource1 = new ShareResource1(arrayBlockingQueue);
		
		new Thread(() ->{
			System.out.println(Thread.currentThread().getName()+"生产线程启动");
			try {
				shareResource1.prod();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"prod").start();
		
		new Thread(() ->{
			System.out.println(Thread.currentThread().getName()+"消费线程启动");
			try {
				shareResource1.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"consumer").start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		shareResource1.stop();
		
		
	}


}
