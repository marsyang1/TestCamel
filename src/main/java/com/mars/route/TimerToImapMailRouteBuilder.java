package com.mars.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;

/**
 * A Camel Java DSL Router
 */
public class TimerToImapMailRouteBuilder extends RouteBuilder {


    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
        startPolicy.setRouteStartTime("*/2 * * * * ?");

        from("pop3://localhost?username=mars@mars.bb.com&password=123456&debugMode=true&consumer.delay=60000")
                .routePolicy(startPolicy)
                .log("mail body = ${body}")
                .to("stream:out")
//                .log("mail >> ${body}");
        ;
    }

}
