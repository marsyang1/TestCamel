package com.mars.config;

import com.mars.resource.FusionDataSourceProperties;
import com.mars.resource.HrEipProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by mars on 2015/8/11.
 */
@Slf4j
@Configuration
public class DataConfig {

    @Autowired
    private HrEipProperties prop;
    @Autowired
    private FusionDataSourceProperties fusionProperties;

    @Bean
    public DataSource fusionSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(fusionProperties.getClassName());
            dataSource.setJdbcUrl(fusionProperties.getDbUrl());
            dataSource.setUser(fusionProperties.getDbUserName());
            dataSource.setPassword(fusionProperties.getDbPassword());
            dataSource.setMaxPoolSize(fusionProperties.getMaxPoolSize());
            dataSource.setMinPoolSize(fusionProperties.getMinPoolSize());
            dataSource.setMaxStatements(fusionProperties.getMaxStatements());
            dataSource.setTestConnectionOnCheckin(fusionProperties.isTestConnection());
            dataSource.setTestConnectionOnCheckout(fusionProperties.isTestConnection());
            log.info("dataSource connection is valid");
        } catch (PropertyVetoException e) {
            log.error("dataSource get Driver is fail", e);
        }
        return dataSource;
    }

    @Bean
    public DataSource hrSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(prop.getClassName());
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
