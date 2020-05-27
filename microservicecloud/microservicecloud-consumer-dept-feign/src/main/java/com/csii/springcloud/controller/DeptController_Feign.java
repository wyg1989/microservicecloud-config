package com.csii.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.csii.springcloud.entities.Dept;
import com.csii.springcloud.service.DeptClientService;
@RestController
public class DeptController_Feign {
	/*
	 * //private String REST_URL_PREFIX="http://localhost:8001"; private static
	 * final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
	 * 
	 * @Autowired private RestTemplate restTemplate;
	 * 
	 * 
	 * @RequestMapping(value="/consumer/dept/add",method=RequestMethod.POST) public
	 * boolean add(Dept dept) { //return
	 * restTemplate.postForObject(PRE_FIX+"/dept/add", dept, responseType,
	 * uriVariables) return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",
	 * dept, Boolean.class); }
	 * 
	 * @RequestMapping(value="/consumer/dept/get/{id}") public Dept
	 * get(@PathVariable("id") Long id) { return
	 * restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class); }
	 * 
	 * @RequestMapping(value="consumer/dept/list") public List<Dept> getList(){
	 * return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class); }
	 */
	@Autowired
	private DeptClientService service;
	
	 @RequestMapping(value = "/consumer/dept/get/{id}")
	  public Dept get(@PathVariable("id") Long id)
	  {
	   return this.service.get(id);
	  }
	 
	  @RequestMapping(value = "/consumer/dept/list")
	  public List<Dept> list()
	  {
	   return this.service.list();
	  }
	 
	  @RequestMapping(value = "/consumer/dept/add")
	  public Object add(Dept dept)
	  {
	   return this.service.add(dept);
	  }

	

}
