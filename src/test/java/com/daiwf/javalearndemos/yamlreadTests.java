package com.daiwf.javalearndemos;


import com.daiwf.javalearndemos.spring.controller.ServerConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class yamlreadTests
{


     @Autowired
	 ServerConfig sc;

	@Test
	void contextLoads() {
		System.out.println("输出"+sc.toString());
	}

}
