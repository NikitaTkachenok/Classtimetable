package ua.com.foxminded.classtimetable.service;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

@Service
public class StudentService {

	private final LessonDao daoLesson;

	public StudentService(DataSource dataSource) {
		daoLesson = new LessonDao(dataSource);
	}

	public List<Lesson> receiveLessonsOnDateRange(int studentId, LocalDate beginDate, LocalDate endDate) {
		return daoLesson.getLessonsForStudentOnDateRange(studentId, beginDate, endDate);
	}
}
