package com.template.micro.client;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import swagger.annotation.EnableBaseSwagger2;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
//@EnableSwagger2
@EnableBaseSwagger2
@EnableScheduling
@MapperScan("com.template.micro.client.mapper")
@EnableFeignClients(basePackages = { "api"})
@ComponentScan(value = {"gateway.*", "log.*","com.template.micro.client.*"})
public class DataSystemClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSystemClientApplication.class,args);
    }

}
