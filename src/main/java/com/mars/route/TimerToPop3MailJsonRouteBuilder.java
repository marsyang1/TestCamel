package com.mars.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.process.FilterHtmlTagProcess;
import com.mars.process.TestProcess;
import com.mars.vo.Alert;
import com.mars.vo.LeaveRequestMail;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Camel Java DSL Router
 */
@Component
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

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS, false);
        JacksonDataFormat jackson = new JacksonDataFormat(objectMapper, LeaveRequestMail.class);

        from("pop3://localhost?username=mars@mars.bb.com&password=123456&debugMode=true&consumer.delay=60000")
                .routePolicy(startPolicy)
                .process(filterTagProcess)
                .log("mail body = ${body}")
                .unmarshal(jackson)
                .convertBodyTo(Alert.class)
                .log("alert body = ${body.status},${body.created},${body.createdBy},${body.updated},${body.updatedBy},${body.sender},${body.userContact},${body.type},${body.sendDate},${body.subject},${body.description},${body.hasRead}")
                .to("sql:INSERT IGNORE INTO fusion.ad_alert " +
                                "( " +
                                "Status, " +
                                "CreatedBy, " +
                                "Created, " +
                                "UpdatedBy, " +
                                "Updated, " +
                                "Sender, " +
                                "UserContact, " +
                                "Type, " +
                                "SendDate, " +
                                "Subject, " +
                                "Description, " +
                                "HasRead " +
                                ") " +
                                "VALUES " +
                                "( " +
                                ":#${body.status}, " +
                                ":#${body.createdBy}, " +
                                ":#${body.created}, " +
                                ":#${body.updatedBy}, " +
                                ":#${body.updated}, " +
                                ":#${body.sender}, " +
                                ":#${body.userContact}, " +
                                ":#${body.type}, " +
                                ":#${body.sendDate}, " +
                                ":#${body.subject}, " +
                                ":#${body.description}, " +
                                ":#${body.hasRead} " +
                                ")" +
                                "?dataSource=fusionSource"
                )
                .log("insert complete")
        ;
    }

}
