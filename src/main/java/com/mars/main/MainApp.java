package com.mars.main;


import com.mars.route.GmailStreamRouteBuilder;
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
//        Main main = new Main();
//        main.enableHangupSupport();
//        main.addRouteBuilder(new MyRouteBuilder());
//        main.run(args);

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

//        Main m = new Main();
//        m.enableHangupSupport();
//        m.addRouteBuilder(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("direct:start")
//                        .to("log:end?level=INFO");
//            }
//        });
//        m.run();

        Main m = new Main();
//        m.addRouteBuilder(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("file:input")
//                        .to("log:end?level=INFO")
//                        .to("file:output");
//            }
//        });
        m.addRouteBuilder(new GmailStreamRouteBuilder());
        m.run();
    }

}

