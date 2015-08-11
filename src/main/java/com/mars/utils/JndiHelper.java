package com.mars.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by mars on 2015/4/6.
 */
@Slf4j
@Component
public class JndiHelper {

    public static Object lookup(String jndiName) {
        try {
            InitialContext context = new InitialContext();
            lookup(context, "java:comp/env/" + jndiName);
            Object jndiObject = lookup(context, "java:comp/env/" + jndiName);
            if (jndiObject != null) {
                return jndiObject;
            }
            return context.lookup(jndiName);
        } catch (NamingException e) {
            log.error("NamingException for JNDI Name : " + jndiName, e);
        }
        return null;
    }

    public static Object lookup(String jndiName, Object defaultValue) {
        Object value = lookup(jndiName);
        if (value != null) {
            return value;
        } else {
            log.info("return default value =" + defaultValue.toString());
            return defaultValue;
        }
    }

    private static Object lookup(InitialContext context, String jndiName) {
        try {
            return context.lookup(jndiName);
        } catch (NamingException e) {
            log.warn("NamingException for JNDI Name : " + jndiName);
            return null;
        }
    }

}
