package com.mars.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
//@Component
public class TimerToLogRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("timer://foo?period=5s&daemon=false")
                .setBody().constant("<hello>world!</hello>")
                .log(">>> ${body}");
    }

}
