package com.mars.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class FileToJMSRouteBuilder extends RouteBuilder {


    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("file:inbound")
                .choice()
                .when(header("NeedsApproval").isEqualTo(true))
                .to("jms:queue:APPROVAL")
                .otherwise().to("seda:transform");
        from("file:inbound")
                .choice()
                .when(header("").isEqualTo(true))
                .to("jms:queue:APPROVAL")
                .otherwise()
                .to("seda:transform");
    }

}
