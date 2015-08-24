package com.mars.route;

import com.mars.process.FilterHtmlTagProcess;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Camel Java DSL Router
 */
@Component
public class TimerToPop3MailRouteBuilder extends RouteBuilder {

    @Autowired
    private FilterHtmlTagProcess testProcess;

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
        startPolicy.setRouteStartTime("*/2 * * * * ?");

        from("pop3://localhost?username=mars@mars.bb.com&password=123456&debugMode=true&consumer.delay=60000")
                .routePolicy(startPolicy)
                .process(testProcess)
                .log("mail body = ${body}")
                .to("stream:out")
//                .log("mail >> ${body}");
        ;
    }

}
