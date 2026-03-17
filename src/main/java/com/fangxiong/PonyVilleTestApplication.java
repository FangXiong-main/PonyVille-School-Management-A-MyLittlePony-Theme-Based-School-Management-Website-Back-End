package com.fangxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //扫描Servlet、Filter、Listener,让spring支持Servlet、Filter、Listener
@SpringBootApplication
public class PonyVilleTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PonyVilleTestApplication.class, args);
	}

}
