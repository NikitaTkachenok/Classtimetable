package ua.com.foxminded.classtimetable.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("ua.com.foxminded.classtimetable")
@PropertySource("classpath:dbconfig.properties")
@EnableWebMvc
public class DBConfiguration {

	@Autowired
	Environment environment;

	private final String DRIVER = "driver";
	private final String URL = "url";
	private final String USER = "dbuser";
	private final String PASSWORD = "password";

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty(DRIVER));
		dataSource.setUrl(environment.getProperty(URL));
		dataSource.setUsername(environment.getProperty(USER));
		dataSource.setPassword(environment.getProperty(PASSWORD));
		return dataSource;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
}
