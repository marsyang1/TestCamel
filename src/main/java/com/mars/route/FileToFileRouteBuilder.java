package com.mars.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class FileToFileRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("file:input")
                .to("log:end?level=INFO")
                .to("file:output");
    }

}
