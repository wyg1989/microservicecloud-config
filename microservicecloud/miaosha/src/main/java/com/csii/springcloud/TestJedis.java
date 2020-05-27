package com.csii.springcloud;

import redis.clients.jedis.Jedis;

public class TestJedis {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.137.11",6379);
		System.out.println(jedis.ping());
		
	}
}
