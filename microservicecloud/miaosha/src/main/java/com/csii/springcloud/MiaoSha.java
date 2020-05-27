package com.csii.springcloud;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiaoSha {
	public static void main(String[] args) {
		//SpringApplication.run(MiaoSha.class, args);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int a = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DATE, -(a-2));
		System.out.println(sdf.format(calendar.getTime()));
	}
	

}
