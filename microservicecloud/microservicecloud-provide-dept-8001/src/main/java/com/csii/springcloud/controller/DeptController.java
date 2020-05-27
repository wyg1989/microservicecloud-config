package com.csii.springcloud.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csii.springcloud.entities.Dept;
import com.csii.springcloud.service.DeptService;
import com.csii.springcloud.service.TGoodInfoService;

@RestController
public class DeptController extends Thread {
	public DeptController(TGoodInfoService tGoodInfoService) {
		this.tGoodInfoService=tGoodInfoService;
	}
	@Autowired
	private DeptService deptService;
	@Autowired
	private TGoodInfoService tGoodInfoService;
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/dept/add", method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		
		return deptService.add(dept);
	}
	@RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}
	@RequestMapping(value="/dept/list")
	public List<Dept> getList(){
		//return deptService.list();.
		Map map = new HashMap();
		Date start = new Date();
		System.out.println(start);
		for(int i=0;i<10000;i++) {
			
			Thread thread = new Thread(new DeptController(tGoodInfoService));
		
		
			thread.start();
			
		}
		run();
		Date end = new Date();
		System.out.println(end);
		return null;
	}
	@Override
	public void run() {
		System.out.println("线程编号========="+Thread.currentThread().getId());
		Map<String, String> map = new HashMap();
		map.put("code", "apple");
		map.put("buys", "3");
		tGoodInfoService.updateAmount(map);	
		/*
		 * synchronized (map) {
		 * 
		 * }
		 */
		
	}
	/*
	 * @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET) public
	 * Object discovery() { List<String> list = client.getServices();
	 * System.out.println("**********" + list);
	 * 
	 * List<ServiceInstance> srvList =
	 * client.getInstances("MICROSERVICECLOUD-DEPT"); for (ServiceInstance element :
	 * srvList) { System.out.println(element.getServiceId() + "\t" +
	 * element.getHost() + "\t" + element.getPort() + "\t" + element.getUri()); }
	 * return this.client; }
	 */

	
}
