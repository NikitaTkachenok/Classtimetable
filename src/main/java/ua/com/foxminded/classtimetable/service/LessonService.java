package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

import java.util.List;

@Service
public class LessonService {

    private final LessonDao daoLesson;
    private final Logger logger = LoggerFactory.getLogger(LessonService.class);

    public LessonService(LessonDao daoLesson) {
        this.daoLesson = daoLesson;
    }

    public List<Lesson> getAll() {
        List<Lesson> allLessons = daoLesson.getAll();
        logger.info("getAll():\n {}", allLessons);
        return allLessons;
    }

    public Lesson getById(int id) {
        Lesson lesson = daoLesson.getById(id);
        logger.info("getById: lesson = {}", lesson);
        return lesson;
    }

    public void create(Lesson lesson) {
        logger.info("create: lesson = {}", lesson);
        daoLesson.create(lesson);
    }

    public void update(Lesson lesson) {
        logger.info("update: lesson = {}", lesson);
        daoLesson.update(lesson);
    }

    public void delete(Lesson lesson) {
        logger.info("delete: lesson = {}", lesson);
        daoLesson.delete(lesson);
    }

}
