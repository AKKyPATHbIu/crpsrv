package com.epam.crpsrv.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {

    public static final String DATA_SOURCE = "dataSource";
    public static final String TX_MANAGER = "txManager";

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS_NAME;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Bean(destroyMethod = "close", name = DATA_SOURCE)
    public HikariDataSource dataSource() {
        return createDataSource();
    }

    @Bean(name = TX_MANAGER)
    public PlatformTransactionManager transactionManager(
            @Autowired @Qualifier(DATA_SOURCE) HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private HikariDataSource createDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", 25000);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 20048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        dataSource.addDataSourceProperty("initializationFailFast", true);
        dataSource.setPoolName("poolName");

        return dataSource;
    }
}