package com.mars.config;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mars on 2015/8/11.
 */
@Configuration
public class CamelConfig extends CamelConfiguration {

    @Override
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        // make Camel aware of Spring Boot’s application.properties
//        PropertiesComponent pc = new PropertiesComponent();
//        pc.setLocation("classpath:application.properties");
//        camelContext.addComponent("properties", pc);

        // enable performance metrics
        camelContext.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
        super.setupCamelContext(camelContext);
    }
}