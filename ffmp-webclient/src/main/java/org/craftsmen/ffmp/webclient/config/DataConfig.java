package org.craftsmen.ffmp.webclient.config;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({com.jrtech.ffmp.data.config.Config.class})
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceFactory factory = new DataSourceFactory();

        Properties props = new Properties();
        props.setProperty("testWhileIdle", "true");
        props.setProperty("testOnBorrow", "true");
        props.setProperty("testOnReturn", "false");
        props.setProperty("validationQuery", "SELECT 1");
        props.setProperty("validationInterval", "30000");
        props.setProperty("timeBetweenEvictionRunsMillis", "30000");
        props.setProperty("maxActive", "50");
        props.setProperty("minIdle", "10");
        props.setProperty("maxWait", "10000");
        props.setProperty("initialSize", "10");
        props.setProperty("removeAbandonedTimeout", "60");
        props.setProperty("removeAbandoned", "true");
        props.setProperty("logAbandoned", "true");
        props.setProperty("minEvictableIdleTimeMillis", "30000");
        props.setProperty("jmxEnabled", "true");
        props.setProperty("jdbcInterceptors", "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        props.setProperty("username", "root");
        props.setProperty("password", "123456");
        props.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/ffmps?useUnicode=true&characterEncoding=UTF-8");
        try {
            return factory.createDataSource(props);
        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
//        jpaVendorAdapter.setDatabase(Database.MYSQL);
//        jpaVendorAdapter.setGenerateDdl(false);
//        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }

}
