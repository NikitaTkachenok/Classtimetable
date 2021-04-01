package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.converters.TeacherConverter;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.dao.TeacherDao;
import ua.com.foxminded.classtimetable.dto.TeacherDto;
import ua.com.foxminded.classtimetable.entities.Lesson;
import ua.com.foxminded.classtimetable.entities.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherDao daoTeacher;
    private final LessonDao daoLesson;
    private final TeacherConverter converterTeacher;
    private final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public TeacherService(TeacherDao daoTeacher, LessonDao daoLesson, TeacherConverter converterTeacher) {
        this.daoTeacher = daoTeacher;
        this.daoLesson = daoLesson;
        this.converterTeacher = converterTeacher;
    }

    public List<TeacherDto> getAll() {
        List<TeacherDto> allTeachers = new ArrayList<>();
        for (Teacher teacher : daoTeacher.getAll()) {
            allTeachers.add(converterTeacher.convertToDto(teacher));
        }
        logger.info("getAll():\n {}", allTeachers);
        return allTeachers;
    }

    public TeacherDto getById(int id) {
        TeacherDto teacher = converterTeacher.convertToDto(daoTeacher.getById(id));
        logger.info("getById: teacher = {}", teacher);
        return teacher;
    }

    public void create(TeacherDto teacher) {
        logger.info("create: teacher = {}", teacher);
        daoTeacher.create(converterTeacher.convertToEntity(teacher));
    }

    public void update(TeacherDto teacher) {
        logger.info("update: teacher = {}", teacher);
        daoTeacher.update(converterTeacher.convertToEntity(teacher));
    }

    public void delete(TeacherDto teacher) {
        logger.info("delete: teacher = {}", teacher);
        daoTeacher.delete(converterTeacher.convertToEntity(teacher));
    }

    public void deleteById(int id) {
        daoTeacher.deleteById(id);
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
