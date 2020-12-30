package ua.com.foxminded.classtimetable;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguratoin;
import ua.com.foxminded.classtimetable.database.DBManager;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DBConfiguratoin.class);) {
			DBManager manager = context.getBean(DBManager.class);
			manager.createTables();
		}
	}
}
