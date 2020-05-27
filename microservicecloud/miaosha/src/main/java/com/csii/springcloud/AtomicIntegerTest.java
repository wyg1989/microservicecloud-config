package com.csii.springcloud;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(5);
		atomicInteger.compareAndSet(5, 2019);
		atomicInteger.getAndIncrement();
	}
}
