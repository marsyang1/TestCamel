package com.mars.main;

import com.mars.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * Created by mars on 2015/8/11.
 */
@Slf4j
@EnableAutoConfiguration
public class SpringApp {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

}
