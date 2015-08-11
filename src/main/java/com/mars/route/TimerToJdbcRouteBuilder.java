package com.mars.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A Camel Java DSL Router
 */
@Component
public class TimerToJdbcRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("timer://timer1?period=5s&daemon=false")
                .setBody().constant("select * from user limit 10;")
                .to("jdbc:dataSource")
                .split().simple("${body}")
                .to("stream:out")
        ;
    }

}
