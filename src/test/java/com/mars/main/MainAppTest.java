package com.mars.main;

import com.mars.route.StreamTestRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

/**
 * Created by mars on 2015/8/4.
 */
public class MainAppTest extends CamelTestSupport {
    static PipedInputStream in;
    static PipedOutputStream out;
    static InputStream originalIn;

    @Test()
    public void testAppRoute() throws Exception {
        out.write("This is a test message!\n".getBytes());
        Thread.sleep(2000);
        assertTrue(new File("test").listFiles().length == 1);
    }

    @BeforeClass()
    public static void setup() throws IOException {
        originalIn = System.in;
        out = new PipedOutputStream();
        in = new PipedInputStream(out);
        System.setIn(in);
        FileUtils.deleteDirectory(new File("test"));
    }

    @After()
    public void teardown() throws IOException {
        out.close();
        System.setIn(originalIn);
    }

    @Override
    public boolean isCreateCamelContextPerClass() {
        return false;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new StreamTestRouteBuilder();
    }
}