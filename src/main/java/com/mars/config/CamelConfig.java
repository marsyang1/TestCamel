package com.mars.config;

import com.mars.converter.AlertTypeConverter;
import com.mars.vo.Alert;
import com.mars.vo.LeaveRequestMail;
import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mars on 2015/8/11.
 */
@Configuration
public class CamelConfig extends CamelConfiguration {

    @Autowired
    private AlertTypeConverter converter;


    @Override
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        // make Camel aware of Spring Boot’s application.properties
//        PropertiesComponent pc = new PropertiesComponent();
//        pc.setLocation("classpath:application.properties");
//        camelContext.addComponent("properties", pc);

        // enable performance metrics
        camelContext.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
        camelContext.getTypeConverterRegistry().addTypeConverter(Alert.class, LeaveRequestMail.class, converter);
        super.setupCamelContext(camelContext);
    }

}