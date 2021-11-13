package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.repository.dao.CourseRepository;
import ua.com.foxminded.classtimetable.repository.entities.Course;

import java.util.List;

@Service
public class CourseService implements ServiceInterface<Course> {

    @Autowired
    private final CourseRepository courseRepository;

    private final Logger logger = LoggerFactory.getLogger(CourseService.class);

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll() {
        List<Course> allCourses = courseRepository.findAll();
        logger.info("getAll():\n {}", allCourses);
        return allCourses;
    }

    @Override
    public Course getById(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        logger.info("getById: course = {}", course);
        return course;
    }

    @Override
    public void create(Course course) {
        logger.info("create: course = {}", course);
        courseRepository.saveAndFlush(course);
    }

    @Override
    public void update(Course course) {
        logger.info("update: course = {}", course);
        courseRepository.saveAndFlush(course);
    }

    @Override
    public void delete(Course course) {
        logger.info("delete: course = {}", course);
        courseRepository.delete(course);
    }

    @Override
    public void deleteById(int id) {
        logger.info("delete: course with ID = {}", id);
        courseRepository.deleteById(id);
    }

}
