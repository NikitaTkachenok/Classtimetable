package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.domain.converters.LessonConverter;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.repository.dao.LessonRepository;
import ua.com.foxminded.classtimetable.repository.entities.Lesson;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService implements ServiceInterface<Lesson> {

    private final LessonRepository lessonRepository;
    private final LessonConverter converterLesson;
    private final Logger logger = LoggerFactory.getLogger(LessonService.class);

    public LessonService(LessonRepository lessonRepository, LessonConverter converterLesson) {
        this.lessonRepository = lessonRepository;
        this.converterLesson = converterLesson;
    }

    @Override
    public List<Lesson> getAll() {
        List<Lesson> allLessons;
        allLessons = lessonRepository.findAll();
        logger.info("getAll():\n {}", allLessons);
        return allLessons;
    }

    public List<LessonDto> getAllAsDto() {
        return getAll()
                .stream()
                .map(converterLesson::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Lesson getById(int id) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        logger.info("getById: lesson = {}", lesson);
        return lesson;
    }

    public LessonDto getByIdAsDto(int id) {
        return converterLesson.toDto(getById(id));
    }

    @Override
    public void create(Lesson lesson) {
        logger.info("create: lesson = {}", lesson);
        lessonRepository.saveAndFlush(lesson);
    }

    public void createFromDto(LessonDto lesson) {
        create(converterLesson.toEntity(lesson));
    }

    @Override
    public void update(Lesson lesson) {
        logger.info("update: lesson = {}", lesson);
        lessonRepository.saveAndFlush(lesson);
    }

    public void updateFromDto(LessonDto lesson) {
        update(converterLesson.toEntity(lesson));
    }

    @Override
    public void delete(Lesson lesson) {
        logger.info("delete: lesson = {}", lesson);
        lessonRepository.delete(lesson);
    }

    public void deleteFromDto(LessonDto lesson) {
        delete(converterLesson.toEntity(lesson));
    }

    @Override
    public void deleteById(int id) {
        logger.info("delete: lesson with ID = {}", id);
        lessonRepository.deleteById(id);
    }

}
