package com.mars.main;


import com.mars.route.StringlJsonRouteBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
@Slf4j
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {

//        CamelContext context = new DefaultCamelContext(); // (1)
//        context.addRoutes(new RouteBuilder() { // (2)
//            @Override
//            public void configure() {
//                from("direct:foo") // (3)
//                        .to("log:MyCategory"); // (4)
//            }
//        });
//        context.start();
//        context.createProducerTemplate().sendBody("direct:foo", "Hello, World!"); // (5)

//        CamelContext ctx = new DefaultCamelContext();
//        ctx.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("direct:start")
//                        .to("log:end?level=INFO");
//            }
//        });
//        ctx.start();
//        log.info("Camel Server start");
//        ctx.createProducerTemplate().sendBody("direct:start", "Hello, world! ");
//        ctx.stop();
//        log.info("Camel Server Stop");

        Main m = new Main();
//        m.addRouteBuilder(new StreamTestRouteBuilder());
        m.addRouteBuilder(new StringlJsonRouteBuilder());
        m.run();
    }

}

