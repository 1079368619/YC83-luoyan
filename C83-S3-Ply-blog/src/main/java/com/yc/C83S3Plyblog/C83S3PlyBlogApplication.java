package com.yc.C83S3Plyblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yc.C83S3Plyblog.dao")
public class C83S3PlyBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(C83S3PlyBlogApplication.class, args);
	}


	
}
