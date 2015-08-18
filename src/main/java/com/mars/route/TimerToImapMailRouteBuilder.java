package com.mars.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class TimerToImapMailRouteBuilder extends RouteBuilder {


    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("timer://timer1?period=10s&daemon=false")
                .to("imaps://localhost?username=mars@mars.bb.com&password=123456&debugMode=true&delete=false&unseen=true&consumer.delay=60000")
                .convertBodyTo(String.class)
                .log("mail >> ${body}");
    }

}
