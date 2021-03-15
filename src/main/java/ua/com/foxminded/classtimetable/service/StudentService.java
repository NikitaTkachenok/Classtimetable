package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.dao.StudentDao;
import ua.com.foxminded.classtimetable.entities.Lesson;
import ua.com.foxminded.classtimetable.entities.Student;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    private final StudentDao daoStudent;
    private final LessonDao daoLesson;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentDao daoStudent, LessonDao daoLesson) {
        this.daoLesson = daoLesson;
        this.daoStudent = daoStudent;
    }

    public List<Student> getAll() {
        logger.info("getAll():\n {}", daoStudent.getAll());
        return daoStudent.getAll();
    }

    public Student getById(int id) {
        logger.info("getById: student = {}", daoStudent.getById(id));
        return daoStudent.getById(id);
    }

    public void create(Student student) {
        logger.info("create: student = {}", student);
        daoStudent.create(student);
    }

    public void update(Student student) {
        logger.info("update: student = {}", student);
        daoStudent.update(student);
    }

    public void delete(Student student) {
        logger.info("delete: student = {}", student);
        daoStudent.delete(student);
    }

    public List<Lesson> receiveLessonsOnDateRange(
            String studentFirstName, String studentLastName, LocalDate beginDate, LocalDate endDate) {
        logger.info("receiveLessonsOnDateRange: student first name = {}, student last name = {}, begin date = {}," +
                " end date = {}.", studentFirstName, studentLastName, beginDate, endDate);
        List<Lesson> lessons = daoLesson.getLessonsForStudentOnDateRange(
                studentFirstName, studentLastName, beginDate, endDate);
        if (lessons.isEmpty()) {
            logger.warn("The student with first name = {} and last name = {} has no lessons for this date range.",
                    studentFirstName, studentLastName);
        } else {
            logger.info("The student with first name = {} and last name = {} has {} lessons for this date range:",
                    studentFirstName, studentLastName, lessons.size());
            logger.info("Lessons:\n {}", lessons);
        }
        return lessons;
    }
}
