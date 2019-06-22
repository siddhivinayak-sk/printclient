package com.dps.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.dps")
@EnableTransactionManagement
public class JpaConfig {

	/*
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:testdb");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		Properties p = new Properties();
        p.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        p.put("hibernate.show_sql", "true");
        p.put("hibernate.cache.use_second_level_cache", "true");
        p.put("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.SingletonEhCacheProvider");
		lcemfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lcemfb.setJpaProperties(p);
		lcemfb.setDataSource(dataSource());
		lcemfb.setPackagesToScan("com.dps");
		lcemfb.afterPropertiesSet();
		return lcemfb.getObject();
	}
	*/
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(entityManagerFactory);
		return jtm;
	}
	
}
