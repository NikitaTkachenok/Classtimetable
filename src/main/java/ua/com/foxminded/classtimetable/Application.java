package ua.com.foxminded.classtimetable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguration;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException, SQLException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DBConfiguration.class);) {
		}
	}
}
