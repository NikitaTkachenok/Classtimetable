package ua.com.foxminded.classtimetable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguratoin;
import ua.com.foxminded.classtimetable.database.DBManager;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException, SQLException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DBConfiguratoin.class);) {
			DBManager manager = context.getBean(DBManager.class);
			manager.createTables();
			manager.fillTables();
//			ScheduleGenerator generator = new ScheduleGenerator();
		}
	}
}
