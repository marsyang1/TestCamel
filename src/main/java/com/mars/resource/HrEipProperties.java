package com.mars.resource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by mars on 2015/7/21.
 */
@Slf4j
@Data
@Component
public class HrEipProperties {

    @Value("${cy.hr.eip.datasource.sqlserver.driver-class-name:com.microsoft.sqlserver.jdbc.SQLServerDriver}")
    private String className;
    @Value("${cy.hr.eip.datasource.sqlserver.url}")
    private String dbUrl;
    @Value("${cy.hr.eip.datasource.sqlserver.username}")
    private String dbUserName;
    @Value("${cy.hr.eip.datasource.sqlserver.password}")
    private String dbPassword;
    @Value("${cy.hr.eip.datasource.sqlserver.max-pool-size:50}")
    private int maxPoolSize;
    @Value("${cy.hr.eip.datasource.sqlserver.min-pool-size:10}")
    private int minPoolSize;
    @Value("${cy.hr.eip.datasource.sqlserver.max-statements:50}")
    private int maxStatements;
    @Value("${cy.hr.eip.datasource.sqlserver.test-connection:true}")
    private boolean testConnection;

    @PostConstruct
    void init() {
        log.info("HrEipProperties startup , display default value ..");
        log.info("className = " + className);
        log.info("dbUrl = " + dbUrl);
        log.info("dbUserName = " + dbUserName.substring(0, 2) + ".....");
        log.info("dbPassword = " + dbPassword.substring(0, 2) + ".....");
        log.info("maxPoolSize = " + maxPoolSize);
        log.info("minPoolSize = " + minPoolSize);
        log.info("maxStatements = " + maxStatements);
        log.info("testConnection = " + testConnection);
    }

}
