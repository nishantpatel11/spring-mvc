package com.spring.demo.mvc.config;

import static org.hibernate.cfg.Environment.*;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.spring.demo.mvc.model.Authorities;
import com.spring.demo.mvc.model.User;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScans(value = { @ComponentScan("com.spring.demo.mvc.dao"),
	    @ComponentScan("com.spring.demo.mvc.service") })
public class HibernateConfig {

	@Autowired
	private Environment env;



	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		Properties props = new Properties();

		// Setting JDBC properties
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.put("hibernate.connection.driver_class", env.getProperty("mysql.driver"));
		props.put("hibernate.connection.url", env.getProperty("mysql.jdbcUrl"));
		props.put("hibernate.connection.username", env.getProperty("mysql.username"));
		props.put("hibernate.connection.password", env.getProperty("mysql.password"));
	 
	      
		
		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

		factoryBean.setHibernateProperties(props);
		factoryBean.setAnnotatedClasses(User.class,Authorities.class);
		return factoryBean;
	}






	  @Bean
	  public HibernateTransactionManager getTransactionManager() {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	    transactionManager.setSessionFactory(getSessionFactory().getObject());
	    return transactionManager;
	  }
}
