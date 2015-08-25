package com.mars.process;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created by mars on 2015/8/24.
 */
@Slf4j
@Component
public class TestProcess implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String s = in.getBody().toString();
        log.info("body = " + s);
    }
}
