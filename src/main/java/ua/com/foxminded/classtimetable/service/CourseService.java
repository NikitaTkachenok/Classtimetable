package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.dao.CourseDao;
import ua.com.foxminded.classtimetable.entities.Course;

import java.util.List;

@Service
public class CourseService {

    private final CourseDao daoCourse;
    private final Logger logger = LoggerFactory.getLogger(CourseService.class);

    public CourseService(CourseDao daoCourse) {
        this.daoCourse = daoCourse;
    }

    public List<Course> getAll() {
        List<Course> allCourses = daoCourse.getAll();
        logger.info("getAll():\n {}", allCourses);
        return allCourses;
    }

    public Course getById(int id) {
        Course course = daoCourse.getById(id);
        logger.info("getById: course = {}", course);
        return course;
    }

    public void create(Course course) {
        logger.info("create: course = {}", course);
        daoCourse.create(course);
    }

    public void update(Course course) {
        logger.info("update: course = {}", course);
        daoCourse.update(course);
    }

    public void delete(Course course) {
        logger.info("update: course = {}", course);
        daoCourse.delete(course);
    }

}
