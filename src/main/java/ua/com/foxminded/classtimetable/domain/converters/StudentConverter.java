package ua.com.foxminded.classtimetable.domain.converters;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.repository.dao.CourseDao;
import ua.com.foxminded.classtimetable.repository.dao.FacultyDao;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.repository.entities.Course;
import ua.com.foxminded.classtimetable.repository.entities.Student;

import java.util.HashSet;
import java.util.Set;

@Component
public class StudentConverter {

    private final FacultyDao daoFaculty;
    private final CourseDao daoCourse;

    public StudentConverter(FacultyDao daoFaculty, CourseDao daoCourse) {
        this.daoFaculty = daoFaculty;
        this.daoCourse = daoCourse;
    }

    public StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setFacultyId(student.getFaculty().getId());
        Set<Integer> coursesId = new HashSet<>();
        student.getStudentCourses().forEach(course -> coursesId.add(course.getId()));
        dto.setCoursesId(coursesId);
        return dto;
    }

    public Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setFaculty(daoFaculty.getById(dto.getFacultyId()));
        Set<Course> courses = new HashSet<>();
        dto.getCoursesId().forEach(integer -> courses.add(daoCourse.getById(integer)));
        student.setStudentCourses(courses);
        return student;
    }

}
