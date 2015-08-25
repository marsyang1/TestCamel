package com.mars.route;

import com.mars.process.FilterHtmlTagProcess;
import com.mars.process.TestProcess;
import com.mars.vo.LeaveRequestMail;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Camel Java DSL Router
 */
public class TimerToPop3MailJsonRouteBuilder extends RouteBuilder {

    @Autowired
    private FilterHtmlTagProcess filterTagProcess;
    @Autowired
    private TestProcess testProcess2;

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
        startPolicy.setRouteStartTime("*/2 * * * * ?");

        from("pop3://localhost?username=mars@mars.bb.com&password=123456&debugMode=true&consumer.delay=60000")
                .routePolicy(startPolicy)
                .process(filterTagProcess)
                .unmarshal().json(JsonLibrary.Jackson, LeaveRequestMail.class)
                .process(testProcess2)
                .log("mail body = ${body}")
                .to("stream:out")
//                .log("mail >> ${body}");
        ;
    }

}
