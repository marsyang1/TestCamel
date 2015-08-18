package com.mars.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * A Camel Java DSL Router
 */
@Component
public class TimerToImapMailRouteBuilder extends RouteBuilder {


    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        Map<String, Object> map = new HashMap<>();
        map.put("To", "mars@mars.bb.com");
        map.put("From", "marsyang1@gmail.com");
        map.put("Subject", "Camel rocks");

        String body = "Hello Claus.\nYes it does.\n\nRegards James.";

        from("timer://timer1?period=5s&daemon=false")
                .setHeader("To").constant("mars@mars.bb.com")
                .setHeader("From").constant("marsyang1@gmail.com")
                .setHeader("Subject").constant("Test")
                .setBody().simple(body)
                .to("smtp://localhost?username=mars@mars.bb.com&password=123456")
                .log("has sended mail")
        ;
    }

}
