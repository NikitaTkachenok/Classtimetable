package ua.com.foxminded.classtimetable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguration;
import ua.com.foxminded.classtimetable.service.StudentService;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException, SQLException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DBConfiguration.class);) {

			StudentService service = context.getBean(StudentService.class);

			LocalDate beginDate = LocalDate.of(2021, 01, 20);
			LocalDate endDate = LocalDate.of(2021, 01, 31);

			System.out.println(service.receiveLessonsOnDateRange(3, beginDate, endDate));
		}
	}
}
