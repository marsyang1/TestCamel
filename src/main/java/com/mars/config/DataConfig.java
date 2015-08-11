package com.mars.config;

import com.mars.resource.ProjectProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by mars on 2015/8/11.
 */
@Slf4j
@Configuration
public class DataConfig {

    @Autowired
    private ProjectProperties prop;

    @Bean
    public JdbcTemplate sysJdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.mariadb.jdbc.Driver");
            dataSource.setJdbcUrl(prop.getDbUrl());
            dataSource.setUser(prop.getDbUserName());
            dataSource.setPassword(prop.getDbPassword());
            dataSource.setMaxPoolSize(prop.getMaxPoolSize());
            dataSource.setMinPoolSize(prop.getMinPoolSize());
            dataSource.setMaxStatements(prop.getMaxStatements());
            dataSource.setTestConnectionOnCheckin(prop.isTestConnection());
            dataSource.setTestConnectionOnCheckout(prop.isTestConnection());
            log.info("dataSource connection is valid");
        } catch (PropertyVetoException e) {
            log.error("dataSource get Driver is fail", e);
        }
        return dataSource;

    }

}
