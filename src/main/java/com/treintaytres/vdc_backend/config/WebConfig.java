package com.treintaytres.vdc_backend.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.treintaytres.vdc_backend.model")
                .persistenceUnit("default")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:/images/");
    }

    @Bean
    @Primary
    public DataSource dataSource(
            @Qualifier("dbTest") DataSource dbTest,
            @Qualifier("dbPre") DataSource dbPre
    ) {
        DynamicRouting routing = new DynamicRouting();

        Map<Object,Object> sources = new HashMap<>();
        sources.put("dbt",dbTest);
        sources.put("dbpre",dbPre);

        routing.setTargetDataSources(sources);
        routing.setDefaultTargetDataSource(dbTest);

        routing.afterPropertiesSet();

        System.out.println("hola caracola");

        return routing;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dbt")
    public DataSource dbTest() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dbpre")
    public DataSource dbPre() {
        return DataSourceBuilder.create().build();
    }
}
