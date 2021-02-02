package ua.com.foxminded.classtimetable.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

@Service
public class TeacherService {

	private final LessonDao daoLesson;
	private final Logger logger = LoggerFactory.getLogger(TeacherService.class);

	public TeacherService(LessonDao daoLesson) {
		this.daoLesson = daoLesson;
	}

	public List<Lesson> receiveLessonsOnDateRange(int teacherId, LocalDate beginDate, LocalDate endDate) {
		logger.info("The income parameters is: teacher ID = {}, begin date = {}, end date = {}.", teacherId, beginDate,
				endDate);
		List<Lesson> lessons = daoLesson.getLessonsForTeacherOnDateRange(teacherId, beginDate, endDate);
		if (lessons.isEmpty()) {
			logger.info("The teacher with ID = {} has no lessons for this date range.", teacherId);
		} else {
			logger.info("Teacher with ID {} has {} lessons for this date range:", teacherId, lessons.size());
			for (Lesson lesson : lessons) {
				logger.info("The lesson: {}", lesson);
			}
		}
		return lessons;
	}
}
