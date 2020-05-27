package com.csii.springcloud;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestApi {
		public static void main(String[] args) {
			//Jedis jedis = new Jedis("192.168.137.11",6379);
			//jedis.set("k1", "v1");
			//jedis.set("k2", "v2");
			//jedis.set("k3", "v3");
			//jedis.set("k4", "v4");
		/*
		 * System.out.println(jedis.get("k1")); Set<String> set = jedis.keys("*");
		 * System.out.println(set.size());
		 * 
		 * System.out.println(jedis.dbSize());
		 */
			
			
		/*
		 * Set set = jedis.keys("*"); for(Iterator iterator =
		 * set.iterator();iterator.hasNext();) { String key = (String)iterator.next();
		 * System.out.println(key); }
		 */
			//System.out.println(jedis.keys("k?"));
			//System.out.println(jedis.exists("k1"));
			//System.out.println(jedis.move("k1", 2));
			//System.out.println(jedis.expire("k2", 101));
			//jedis.exp
			//System.out.println(jedis.ttl("k2"));
			//System.out.println(jedis.del("k4"));
			
		/*
		 * Transaction Transaction = jedis.multi();
		 * System.out.println(Transaction.set("k5","v5")); Transaction.set("k6","v6");
		 * Transaction.set("k7","v7"); System.out.println(Transaction.exec());
		 */
			Boolean result = null;
			try {
				result = testTx();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(result);
		}
		
		
		public static boolean testTx() throws InterruptedException {
			 int balance;// 可用余额    
			  int debt;// 欠额     
			  int amtToSubtract = 10;// 实刷额度 
			Jedis jedis = new Jedis("192.168.137.11",6379);
			jedis.watch("balance");
			Thread.sleep(20000);
			 balance = Integer.parseInt(jedis.get("balance"));  
			 if (balance < amtToSubtract) {   
				  jedis.unwatch();       
				  System.out.println("modify");   
				  return false;     
				  
			 }else {
				 System.out.println("***********transaction");      
				  Transaction transaction = jedis.multi(); 
				  transaction.decrBy("balance", amtToSubtract);  
				  transaction.incrBy("debt", amtToSubtract);
				  transaction.exec();      
				  balance = Integer.parseInt(jedis.get("balance"));    
				  debt = Integer.parseInt(jedis.get("debt"));      
				  System.out.println("*******" + balance);   
				  System.out.println("*******" + debt);     
				  return true;   
			 }
		
		}

}
