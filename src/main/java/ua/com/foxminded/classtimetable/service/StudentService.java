package ua.com.foxminded.classtimetable.service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.dao.StudentCourseDao;
import ua.com.foxminded.classtimetable.entities.Lesson;
import ua.com.foxminded.classtimetable.entities.StudentCourse;

@Service
public class StudentService {

	private final LessonDao daoLesson;
	private final StudentCourseDao daoStudentCourse;

	public StudentService(DataSource dataSource) {
		daoLesson = new LessonDao(dataSource);
		daoStudentCourse = new StudentCourseDao(dataSource);
	}

	public List<Lesson> receiveLessonsForOneDate(int studentId, LocalDate requiredDate) {
		List<Lesson> lessonsOnDay = new LinkedList<>();
		for (StudentCourse studentCourse : daoStudentCourse.getByStudentId(studentId)) {
			lessonsOnDay.addAll(daoLesson.getLessonsWithOneCourseForOneDate(studentCourse.getCourseId(), requiredDate));
		}
		return lessonsOnDay;
	}

	public List<Lesson> receiveLessonsOnDateDiapason(int studentId, LocalDate beginDate, LocalDate endDate) {
		List<Lesson> lessons = new LinkedList<>();
		for (StudentCourse studentCourse : daoStudentCourse.getByStudentId(studentId)) {
			lessons.addAll(
					daoLesson.getLessonsWithOneCourseOnDateDiapason(studentCourse.getCourseId(), beginDate, endDate));
		}
		return lessons;
	}
}
