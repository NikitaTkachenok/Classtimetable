package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.domain.converters.LessonConverter;
import ua.com.foxminded.classtimetable.domain.converters.StudentConverter;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.repository.dao.LessonDao;
import ua.com.foxminded.classtimetable.repository.dao.StudentDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentDao daoStudent;
    private final LessonDao daoLesson;
    private final StudentConverter converterStudent;
    private final LessonConverter converterLesson;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentDao daoStudent, LessonDao daoLesson, StudentConverter converterStudent,
                          LessonConverter converterLesson) {
        this.daoLesson = daoLesson;
        this.daoStudent = daoStudent;
        this.converterStudent = converterStudent;
        this.converterLesson = converterLesson;
    }

    public List<StudentDto> getAll() {
        List<StudentDto> allStudents = new ArrayList<>();
        daoStudent.getAll().forEach(student -> allStudents.add(converterStudent.toDto(student)));
        logger.info("getAll():\n {}", allStudents);
        return allStudents;
    }

    public StudentDto getById(int id) {
        StudentDto student = converterStudent.toDto(daoStudent.getById(id));
        logger.info("getById: student = {}", student);
        return student;
    }

    public void create(StudentDto student) {
        logger.info("create: student = {}", student);
        daoStudent.create(converterStudent.toEntity(student));
    }

    public void update(StudentDto student) {
        logger.info("update: student = {}", student);
        daoStudent.update(converterStudent.toEntity(student));
    }

    public void deleteById(int id) {
        logger.info("delete: student with ID = {}", id);
        daoStudent.deleteById(id);
    }

    public List<LessonDto> receiveLessonsOnDateRange(int id, LocalDate beginDate, LocalDate endDate) {
        logger.info("receiveLessonsOnDateRange: student id = {}, begin date = {}," +
                " end date = {}.", id, beginDate, endDate);
        List<LessonDto> lessons = daoLesson.getLessonsForStudentOnDateRange(id, beginDate, endDate)
                .stream()
                .map(converterLesson::toDto)
                .collect(Collectors.toList());
        if (lessons.isEmpty()) {
            logger.warn("The student with id = {} has no lessons for this date range.", id);
        } else {
            logger.info("The student with id = {} has {} lessons for this date range:", id, lessons.size());
            logger.info("Lessons:\n {}", lessons);
        }
        return lessons;
    }

}
