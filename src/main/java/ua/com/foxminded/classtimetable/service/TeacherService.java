package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.dao.TeacherDao;
import ua.com.foxminded.classtimetable.entities.Lesson;
import ua.com.foxminded.classtimetable.entities.Teacher;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherDao daoTeacher;
    private final LessonDao daoLesson;
    private final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public TeacherService(TeacherDao daoTeacher, LessonDao daoLesson) {
        this.daoTeacher = daoTeacher;
        this.daoLesson = daoLesson;
    }

    public List<Teacher> getAll() {
        List<Teacher> allTeachers = daoTeacher.getAll();
        logger.info("getAll():\n {}", allTeachers);
        return allTeachers;
    }

    public Teacher getById(int id) {
        Teacher teacher = daoTeacher.getById(id);
        logger.info("getById: teacher = {}", teacher);
        return teacher;
    }

    public void create(Teacher teacher) {
        logger.info("create: teacher = {}", teacher);
        daoTeacher.create(teacher);
    }

    public void update(Teacher teacher) {
        logger.info("update: teacher = {}", teacher);
        daoTeacher.update(teacher);
    }

    public void delete(Teacher teacher) {
        logger.info("delete: teacher = {}", teacher);
        daoTeacher.delete(teacher);
    }

    public List<Lesson> receiveLessonsOnDateRange(String teacherFirstName, String teacherLastName,
                                                  LocalDate beginDate, LocalDate endDate) {
        logger.info("receiveLessonsOnDateRange: teacher first name = {}, teacher last name = {}," +
                " begin date = {}, end date = {}.", teacherFirstName, teacherLastName, beginDate, endDate);
        List<Lesson> lessons = daoLesson.getLessonsForTeacherOnDateRange(teacherFirstName, teacherLastName,
                beginDate, endDate);
        if (lessons.isEmpty()) {
            logger.warn("The teacher with first name = {} and last name = {} has no lessons for this date range.",
                    teacherFirstName, teacherLastName);
        } else {
            logger.info("Teacher with first name = {} and last name = {} has {} lessons for this date range:",
                    teacherFirstName, teacherLastName, lessons.size());
            logger.info("Lessons:\n {}", lessons);
        }
        return lessons;
    }
}
