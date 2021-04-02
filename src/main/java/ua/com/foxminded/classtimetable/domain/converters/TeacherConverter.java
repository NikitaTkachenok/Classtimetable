package ua.com.foxminded.classtimetable.domain.converters;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.repository.dao.CourseDao;
import ua.com.foxminded.classtimetable.repository.dao.FacultyDao;
import ua.com.foxminded.classtimetable.repository.entities.Course;
import ua.com.foxminded.classtimetable.repository.entities.Teacher;

import java.util.HashSet;
import java.util.Set;

@Component
public class TeacherConverter {

    private final FacultyDao daoFaculty;
    private final CourseDao daoCourse;

    public TeacherConverter(FacultyDao daoFaculty, CourseDao daoCourse) {
        this.daoFaculty = daoFaculty;
        this.daoCourse = daoCourse;
    }

    public TeacherDto toDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setFacultyId(teacher.getFaculty().getId());
        Set<Integer> coursesId = new HashSet<>();
        teacher.getTeacherCourses().forEach(course -> coursesId.add(course.getId()));
        dto.setCoursesId(coursesId);
        return dto;
    }

    public Teacher toEntity(TeacherDto dto) {
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setFaculty(daoFaculty.getById(dto.getFacultyId()));
        Set<Course> courses = new HashSet<>();
        dto.getCoursesId().forEach(integer -> courses.add(daoCourse.getById(integer)));
        teacher.setTeacherCourses(courses);
        return teacher;
    }

}
