package com.mars.config;

import com.mars.utils.ConfigPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import java.io.IOException;

/**
 * Created by mars on 2015/8/11.
 */
@Slf4j
@Configuration
@ComponentScan("com.mars")
public class AppConfig {

    @Bean
    public static PropertyPlaceholderConfigurer initSecurityProp() throws IOException {
        String path = ConfigPath.getFusionConfigRootPath();
        log.info("Loading properties path = " + path);
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocations(new PathResource(path + "security.properties"));
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertyPlaceholderConfigurer;
    }

}
