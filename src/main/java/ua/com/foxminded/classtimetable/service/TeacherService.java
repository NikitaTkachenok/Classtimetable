package ua.com.foxminded.classtimetable.service;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

@Service
public class TeacherService {

	private final LessonDao daoLesson;

	public TeacherService(DataSource dataSource) {
		daoLesson = new LessonDao(dataSource);
	}

	public List<Lesson> receiveLessonsForOneDate(int teacherId, LocalDate requiredDate) {
		return daoLesson.getLessonsForTeacherForOneDate(teacherId, requiredDate);
	}

	public List<Lesson> receiveLessonsOnDateDiapason(int teacherId, LocalDate beginDate, LocalDate endDate) {
		return daoLesson.getLessonsForTeacherOnDateDiapason(teacherId, beginDate, endDate);
	}
}
