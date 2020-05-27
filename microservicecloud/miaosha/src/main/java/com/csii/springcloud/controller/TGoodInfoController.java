package com.csii.springcloud.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csii.springcloud.service.TGoodInfoService;

@RestController
public class TGoodInfoController {
	public TGoodInfoController(TGoodInfoService tGoodInfoService) {
		this.tGoodInfoService = tGoodInfoService;
	}
	@Autowired
	private TGoodInfoService tGoodInfoService;
	private static final int USER_NUM=100;
	private static CountDownLatch  countDownLatch= new CountDownLatch(USER_NUM);
	private CyclicBarrier cyc = new CyclicBarrier(USER_NUM+1);
	@RequestMapping("/update")
	public void updateAmout() throws InterruptedException {
		Long start = System.currentTimeMillis();
		for(int i=0;i<USER_NUM;i++) {
			new Thread(new UserRequest("apple",3)).start();
			 
			if(i==USER_NUM) {
				Thread.currentThread().sleep(1000);
			}
			countDownLatch.countDown();
		}
		try {
			cyc.await();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long end = System.currentTimeMillis();
		System.out.println("花费的时间为："+(end -start));
		 
		
		
	}

	public class UserRequest implements Runnable{
		private String code;
		private int buys;
		public UserRequest (String code,int buys) {
			this.code=code;
			this.buys=buys;
		}
		@Override
		public void run() {
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String code = "apple";
			int buys =3;
			Map map = new HashMap();
			boolean result = updateAmout(map);
			
		}
		public synchronized boolean updateAmout(Map map)
		{
			
			map.put("code",code);
		
			Integer amout = tGoodInfoService.queryAmout(map);
			if(amout<=3) {
				return false ;
			}
			
			map.put("buys", buys);
			return tGoodInfoService.updateAmount(map)>0?true:false;
		}
	}
}
