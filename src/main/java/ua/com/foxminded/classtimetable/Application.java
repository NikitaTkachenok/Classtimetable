package ua.com.foxminded.classtimetable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguration;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException, SQLException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DBConfiguration.class);) {

		}
	}
}
