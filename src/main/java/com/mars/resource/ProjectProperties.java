package com.mars.resource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by mars on 2015/5/7.
 */
@Slf4j
@Data
@Component
public class ProjectProperties {

    @Value("${cy.security.aeskey}")
    private String aeskey;
    @Value("${cy.security.jaasUserName}")
    private String jaasUserName;
    @Value("${cy.security.jaasPassword}")
    private String jaasPassword;

    @Value("${cy.security.db.url}")
    private String dbUrl;
    @Value("${cy.security.db.username}")
    private String dbUserName;
    @Value("${cy.security.db.password}")
    private String dbPassword;

    @Value("${cy.security.db.maxPoolSize:200}")
    private int maxPoolSize;
    @Value("${cy.security.db.minPoolSize:10}")
    private int minPoolSize;
    @Value("${cy.security.db.maxStatements:200}")
    private int maxStatements;
    @Value("${cy.security.db.testConnection:true}")
    private boolean testConnection;
    @Value("${cy.security.totp.enable_two_factor:true}")
    private boolean enableTwoFactor;

    @Value("${cy.security.cache.authUser.timeToIdleSeconds:600}")
    private int userTimeToIdleSeconds;
    @Value("${cy.security.cache.authUser.timeToLiveSeconds:600}")
    private int userTimeToLiveSeconds;
    @Value("${cy.security.cache.authOrg.timeToIdleSeconds:600}")
    private int orgTimeToIdleSeconds;
    @Value("${cy.security.cache.authOrg.timeToLiveSeconds:600}")
    private int orgTimeToLiveSeconds;
    @Value("${cy.security.cache.authRole.timeToIdleSeconds:600}")
    private int roleTimeToIdleSeconds;
    @Value("${cy.security.cache.authRole.timeToLiveSeconds:600}")
    private int roleTimeToLiveSeconds;
    @Value("${cy.security.cache.authUrl.timeToIdleSeconds:1200}")
    private int urlTimeToIdleSeconds;
    @Value("${cy.security.cache.authUrl.timeToLiveSeconds:1200}")
    private int urlTimeToLiveSeconds;

    @PostConstruct
    void init() {
        log.info("SecurityProperties startup , display default value ..");
        log.info("aeskey = " + aeskey.substring(0, 3) + ".....");
        log.info("jaasUserName = " + jaasUserName.substring(0, 3) + ".....");
        log.info("jaasPassword = " + jaasPassword.substring(0, 3) + ".....");
        log.info("dbUrl = " + dbUrl);
        log.info("dbUserName = " + dbUserName);
        log.info("dbPassword = " + dbPassword.substring(0, 3) + ".....");
        log.info("maxPoolSize = " + maxPoolSize);
        log.info("minPoolSize = " + minPoolSize);
        log.info("maxStatements = " + maxStatements);
        log.info("testConnection = " + testConnection);
        log.info("enableTwoFactor = " + enableTwoFactor);
        log.info("Cache time -------------------------------------------------");
        log.info("userTimeToIdleSeconds = " + userTimeToIdleSeconds);
        log.info("userTimeToLiveSeconds = " + userTimeToLiveSeconds);
        log.info("orgTimeToIdleSeconds = " + orgTimeToIdleSeconds);
        log.info("orgTimeToLiveSeconds = " + orgTimeToLiveSeconds);
        log.info("roleTimeToIdleSeconds = " + roleTimeToIdleSeconds);
        log.info("roleTimeToLiveSeconds = " + roleTimeToLiveSeconds);
        log.info("urlTimeToIdleSeconds = " + urlTimeToIdleSeconds);
        log.info("urlTimeToLiveSeconds = " + urlTimeToLiveSeconds);
    }

}

