package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.domain.converters.LessonConverter;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.repository.dao.LessonDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    private final LessonDao daoLesson;
    private final LessonConverter converterLesson;
    private final Logger logger = LoggerFactory.getLogger(LessonService.class);

    public LessonService(LessonDao daoLesson, LessonConverter converterLesson) {
        this.daoLesson = daoLesson;
        this.converterLesson = converterLesson;
    }

    public List<LessonDto> getAll() {
        List<LessonDto> allLessons = new ArrayList<>();
        daoLesson.getAll().forEach(lesson -> allLessons.add(converterLesson.toDto(lesson)));
        logger.info("getAll():\n {}", allLessons);
        return allLessons;
    }

    public LessonDto getById(int id) {
        LessonDto lesson = converterLesson.toDto(daoLesson.getById(id));
        logger.info("getById: lesson = {}", lesson);
        return lesson;
    }

    public void create(LessonDto lesson) {
        logger.info("create: lesson = {}", lesson);
        daoLesson.create(converterLesson.toEntity(lesson));
    }

    public void update(LessonDto lesson) {
        logger.info("update: lesson = {}", lesson);
        daoLesson.update(converterLesson.toEntity(lesson));
    }

    public void delete(LessonDto lesson) {
        logger.info("delete: lesson = {}", lesson);
        daoLesson.delete(converterLesson.toEntity(lesson));
    }

}
