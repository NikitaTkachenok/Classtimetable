package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.domain.converters.LessonConverter;
import ua.com.foxminded.classtimetable.domain.converters.TeacherConverter;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.repository.dao.LessonDao;
import ua.com.foxminded.classtimetable.repository.dao.TeacherDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherDao daoTeacher;
    private final LessonDao daoLesson;
    private final TeacherConverter converterTeacher;
    private final LessonConverter converterLesson;
    private final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public TeacherService(TeacherDao daoTeacher, LessonDao daoLesson, TeacherConverter converterTeacher,
                          LessonConverter converterLesson) {
        this.daoTeacher = daoTeacher;
        this.daoLesson = daoLesson;
        this.converterTeacher = converterTeacher;
        this.converterLesson = converterLesson;
    }

    public List<TeacherDto> getAll() {
        List<TeacherDto> allTeachers = new ArrayList<>();
        daoTeacher.getAll().forEach(teacher -> allTeachers.add(converterTeacher.toDto(teacher)));
        logger.info("getAll():\n {}", allTeachers);
        return allTeachers;
    }

    public TeacherDto getById(int id) {
        TeacherDto teacher = converterTeacher.toDto(daoTeacher.getById(id));
        logger.info("getById: teacher = {}", teacher);
        return teacher;
    }

    public void create(TeacherDto teacher) {
        logger.info("create: teacher = {}", teacher);
        daoTeacher.create(converterTeacher.toEntity(teacher));
    }

    public void update(TeacherDto teacher) {
        logger.info("update: teacher = {}", teacher);
        daoTeacher.update(converterTeacher.toEntity(teacher));
    }

    public void deleteById(int id) {
        logger.info("delete: teacher with ID = {}", id);
        daoTeacher.deleteById(id);
    }

    public List<LessonDto> receiveLessonsOnDateRange(String teacherFirstName, String teacherLastName,
                                                     LocalDate beginDate, LocalDate endDate) {
        logger.info("receiveLessonsOnDateRange: teacher first name = {}, teacher last name = {}," +
                " begin date = {}, end date = {}.", teacherFirstName, teacherLastName, beginDate, endDate);
        List<LessonDto> lessons = new ArrayList<>();
        daoLesson.getLessonsForTeacherOnDateRange(teacherFirstName, teacherLastName, beginDate, endDate)
                .forEach(lesson -> lessons.add(converterLesson.toDto(lesson)));
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
