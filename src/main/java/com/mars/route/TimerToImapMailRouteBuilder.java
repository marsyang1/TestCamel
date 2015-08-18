package com.mars.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.springframework.stereotype.Component;

/**
 * A Camel Java DSL Router
 */
@Component
public class TimerToImapMailRouteBuilder extends RouteBuilder {


    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
        startPolicy.setRouteStartTime("*/5 * * * * ?");

        from("imap://localhost?username=mars@mars.bb.com&password=123456&debugMode=true&delete=false&unseen=true&consumer.delay=60000")
                .routePolicy(startPolicy)
                .convertBodyTo(String.class)
                .log("mail >> ${body}");
    }

}
