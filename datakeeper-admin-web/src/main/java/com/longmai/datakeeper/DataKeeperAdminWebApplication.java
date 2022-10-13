package com.longmai.datakeeper;

import com.longmai.datakeeper.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;


@MapperScan("com.longmai.datakeeper.dao.mapper")
@EnableAsync
@RestController
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients(value={"com.longmai.datakeeper.rest.api"})
public class DataKeeperAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataKeeperAdminWebApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
