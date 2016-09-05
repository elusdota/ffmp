package com.jrtech.ffmp.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by suelmer on 2016/6/29.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.jrtech.ffmp.data.repositories"})
public class Config {

    /**
     * 指定本地容器管理 EntityManagerFactory,从而进行细粒度控制
     *
     * @param dataSource       指定spring定义的数据源
     * @param jpaVendorAdapter 指定实现厂商专用特性，即generateDdl= false表示不自动生成DDL，database= MySQL表示使用MySQL数据库；
     * @return 本地容器管理EntityManagerFactory
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource);
        lemfb.setJpaVendorAdapter(jpaVendorAdapter);
//        lemfb.setPersistenceUnitName("javaconfigPersistenceUnit");
        lemfb.setPackagesToScan("com.jrtech.ffmp.data.entities");
        lemfb.setJpaProperties(getJpaProperties());
        return lemfb;
    }

    private Properties getJpaProperties() {
        return new Properties() {
            private static final long serialVersionUID = 9102937342519479384L;

            {
//                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
            }
        };
    }
    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory(dataSource, jpaVendorAdapter).getObject());
        return jpaTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager(dataSource,jpaVendorAdapter));
        return transactionTemplate;
    }
}
