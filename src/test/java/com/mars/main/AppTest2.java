package com.mars.main;

import com.mars.route.StreamTestRouteBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mars on 2015/8/4.
 */
public class AppTest2 extends CamelTestSupport {

    @Override
    public String isMockEndpoints() {
        return "*";
    }

    @Test()
    public void testAppRoute() throws Exception {
        String testMessage = "This is a test message!";
        getMockEndpoint("mock:file:test").expectedBodiesReceived(testMessage);
        template.sendBody("direct:in", testMessage);
        assertMockEndpointsSatisfied();
    }

    @Before
    public void replaceStreamIn() throws Exception {
        context.getRouteDefinitions().get(0).adviceWith(context,
                new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        replaceFromWith("direct:in");
                    }
                });
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new StreamTestRouteBuilder();
    }
}