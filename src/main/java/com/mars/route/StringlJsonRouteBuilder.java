package com.mars.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.vo.LeaveRequestMail;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

/**
 * A Camel Java DSL Router
 */
@Slf4j
public class StringlJsonRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS, false);
        JacksonDataFormat jackson = new JacksonDataFormat(objectMapper, LeaveRequestMail.class);

        from("stream:in")
                .unmarshal(jackson)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        LeaveRequestMail body = exchange.getIn().getBody(LeaveRequestMail.class);
                        log.info("body = " + body);
                    }
                })
                .to("stream:out")
//                .log("mail >> ${body}");
        ;
    }

}
