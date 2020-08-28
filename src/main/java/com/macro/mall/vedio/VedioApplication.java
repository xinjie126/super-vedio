package com.macro.mall.vedio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan(basePackages ="com.macro.mall.vedio.mapper")
public class VedioApplication {
    public static void main(String[] args) {
        SpringApplication.run(VedioApplication.class, args);
    }

    @RequestMapping(value = "")
    public String welcome() {
        return "welcome to jetpack server";
    }
}

