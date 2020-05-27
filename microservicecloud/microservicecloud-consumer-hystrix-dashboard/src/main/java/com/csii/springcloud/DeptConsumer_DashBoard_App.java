package com.csii.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
@SpringBootApplication
@EnableHystrixDashboard//开启基于hystrixDashboard的注解
public class DeptConsumer_DashBoard_App {
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
	}
	

	@Bean 
	public ServletRegistrationBean getServlet() {
		  HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet(); 
		  ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet); 
		  registrationBean.setLoadOnStartup(1);
		  registrationBean.addUrlMappings("/hystrix.stream");
		  registrationBean.setName("HystrixMetricsStreamServlet"); 
		  return registrationBean; 
		  }

	 

}
