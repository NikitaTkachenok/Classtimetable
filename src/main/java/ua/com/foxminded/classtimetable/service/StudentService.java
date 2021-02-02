package ua.com.foxminded.classtimetable.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

@Service
public class StudentService {

	private final LessonDao daoLesson;
	private final Logger logger = LoggerFactory.getLogger(TeacherService.class);

	public StudentService(LessonDao daoLesson) {
		this.daoLesson = daoLesson;
	}

	public List<Lesson> receiveLessonsOnDateRange(int studentId, LocalDate beginDate, LocalDate endDate) {
		logger.info("The income parameters is: student ID = {}, begin date = {}, end date = {}.", studentId, beginDate,
				endDate);
		List<Lesson> lessons = daoLesson.getLessonsForStudentOnDateRange(studentId, beginDate, endDate);
		if (lessons.isEmpty()) {
			logger.info("The student with ID = {} has no lessons for this date range.", studentId);
		} else {
			logger.info("The student with ID {} has {} lessons for this date range:", studentId, lessons.size());
			for (Lesson lesson : lessons) {
				logger.info("The lesson: {}", lesson);
			}
		}
		return lessons;
	}
}
