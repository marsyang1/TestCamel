package com.mars.route;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Created by mars on 2015/8/13.
 */
//@RunWith(CamelSpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = TestAppConfig.class)
//@MockEndpoints("*")
public class FileToJMSRouteBuilderTest extends CamelTestSupport{

    @Autowired
    CamelContext context;

    @EndpointInject(uri = "mock:jms:queue:APPROVAL")
    MockEndpoint mockNeedsApproval;

    @EndpointInject(uri = "mock:seda:transform")
    MockEndpoint mockTransform;

    @Produce
    ProducerTemplate template;

    @Before
    public void replaceFileInbound() throws Exception {
        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:start");
            }
        });
    }

    @DirtiesContext
    @Test
    public void testNeedsApprovalMessage() throws Exception {
        mockNeedsApproval.expectedMessageCount(1);
        mockTransform.expectedMessageCount(0);
        template.sendBodyAndHeader("direct:start", "message", "NeedsApproval", true);
        mockNeedsApproval.assertIsSatisfied();
        mockTransform.assertIsSatisfied();
    }

    @DirtiesContext
    @Test
    public void testDirectTransformMessage() throws Exception {
        mockNeedsApproval.expectedMessageCount(0);
        mockTransform.expectedMessageCount(1);
        context.createProducerTemplate()
                .sendBodyAndHeader("direct:start", "message", "NeedsApproval", false);
        mockNeedsApproval.assertIsSatisfied();
        mockTransform.assertIsSatisfied();
    }

    @Override
    public boolean isUseAdviceWith() {
        // tell we are using advice with, which allows us to advice the route
        // before Camel is being started, and thus can replace activemq with something else.
        return true;
    }

}