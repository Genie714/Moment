package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
@MapperScan("com.example.demo.dao")
public class MomentApplication extends SpringBootServletInitializer{

	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(MomentApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MomentApplication.class, args);
	}

}
