package com.shaber.bookmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shaber.bookmanager.mapper")
public class BookspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookspringbootApplication.class, args);
    }

}
