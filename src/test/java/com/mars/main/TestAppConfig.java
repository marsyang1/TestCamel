package com.mars.main;

import com.mars.utils.ConfigPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.seda.SedaComponent;
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
public class TestAppConfig {

    @Bean
    public static PropertyPlaceholderConfigurer initSecurityProp() throws IOException {
        String path = ConfigPath.getFusionConfigRootPath();
        log.info("Loading properties path = " + path);
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocations(new PathResource(path + "hr-eip.properties"));
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertyPlaceholderConfigurer;
    }

    @Bean
    public SedaComponent sedaComponent() {
        return new SedaComponent();
    }

}
