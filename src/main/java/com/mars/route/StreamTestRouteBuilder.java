package com.mars.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class StreamTestRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("stream:in")
                .to("stream:out")
                .to("file:test")
        ;
    }

}
