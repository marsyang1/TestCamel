package com.mars.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class GmailStreamRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("imaps://imap.gmail.com?debugMode=true&username=fusion@mail.chungyo.net&password=ecj84web&delete=false&unseen=true&consumer.delay=60000")
                .to("stream:out")
                .to("file:test")
        ;
    }

}
