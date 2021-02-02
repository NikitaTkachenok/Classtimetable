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

			int teacherId = 3;
			int studentId = 18;
			LocalDate beginDate = LocalDate.of(2021, 01, 20);
			LocalDate endDate = LocalDate.of(2021, 01, 28);

			TeacherService service = context.getBean(TeacherService.class);
			service.receiveLessonsOnDateRange(teacherId, beginDate, endDate);

			StudentService service2 = context.getBean(StudentService.class);
			service2.receiveLessonsOnDateRange(studentId, beginDate, endDate);

		}
	}
}
