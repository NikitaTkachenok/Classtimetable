package ua.com.foxminded.classtimetable.domain.converters;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.repository.dao.ClassroomDao;
import ua.com.foxminded.classtimetable.repository.dao.CourseDao;
import ua.com.foxminded.classtimetable.repository.dao.TeacherDao;
import ua.com.foxminded.classtimetable.repository.entities.Lesson;

@Component
public class LessonConverter {

    private final ClassroomDao daoClassroom;
    private final CourseDao daoCourse;
    private final TeacherDao daoTeacher;

    public LessonConverter(ClassroomDao daoClassroom, CourseDao daoCourse, TeacherDao daoTeacher) {
        this.daoClassroom = daoClassroom;
        this.daoCourse = daoCourse;
        this.daoTeacher = daoTeacher;
    }

    public LessonDto toDto(Lesson lesson) {
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setDate(lesson.getDate());
        dto.setStartTime(lesson.getStartTime());
        dto.setEndTime(lesson.getEndTime());
        dto.setClassroomId(lesson.getClassroom().getId());
        dto.setCourseId(lesson.getCourse().getId());
        dto.setTeacherId(lesson.getTeacher().getId());
        return dto;
    }

    public Lesson toEntity(LessonDto dto) {
        Lesson lesson = new Lesson();
        lesson.setId(dto.getId());
        lesson.setDate(dto.getDate());
        lesson.setStartTime(dto.getStartTime());
        lesson.setEndTime(dto.getEndTime());
        lesson.setClassroom(daoClassroom.getById(dto.getClassroomId()));
        lesson.setCourse(daoCourse.getById(dto.getCourseId()));
        lesson.setTeacher(daoTeacher.getById(dto.getTeacherId()));
        return lesson;
    }

}
