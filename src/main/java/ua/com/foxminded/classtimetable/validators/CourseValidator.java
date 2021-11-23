package ua.com.foxminded.classtimetable.validators;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.controllers.exceptions.IndelibleEntityException;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.repository.entities.Course;
import ua.com.foxminded.classtimetable.service.LessonService;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseValidator {

    private final TeacherService serviceTeacher;
    private final StudentService serviceStudent;
    private final LessonService serviceLesson;

    public CourseValidator(TeacherService serviceTeacher, StudentService serviceStudent, LessonService serviceLesson) {
        this.serviceTeacher = serviceTeacher;
        this.serviceStudent = serviceStudent;
        this.serviceLesson = serviceLesson;
    }

    public boolean checkForDeletion(Course course, HttpServletRequest request) {
        if (checkForTeacherUsing(course)) {
            throw new IndelibleEntityException
                    ("The course cannot be deleted because still there are teachers which use it",
                            request.getHeader("Referer"));
        }
        if (checkForStudentUsing(course)) {
            throw new IndelibleEntityException
                    ("The course cannot be deleted because still there are students which use it",
                            request.getHeader("Referer"));
        }
        if (checkForLessonUsing(course)) {
            throw new IndelibleEntityException
                    ("The course cannot be deleted because still there are lessons which use it",
                            request.getHeader("Referer"));
        } else {
            return true;
        }
    }

    private boolean checkForTeacherUsing(Course course) {
        List<Integer> actualTeachersCourses = new ArrayList<>();
        serviceTeacher.getAllAsDto()
                .stream()
                .map(TeacherDto::getCoursesId)
                .forEach(set -> actualTeachersCourses.add(set.iterator().next()));
        return actualTeachersCourses
                .stream()
                .distinct()
                .collect(Collectors.toList())
                .contains(course.getId());
    }

    private boolean checkForStudentUsing(Course course) {
        List<Integer> actualStudentsCourses = new ArrayList<>();
        serviceStudent.getAllAsDto()
                .stream()
                .map(StudentDto::getCoursesId)
                .forEach(set -> actualStudentsCourses.add(set.iterator().next()));
        return actualStudentsCourses
                .stream()
                .distinct()
                .collect(Collectors.toList())
                .contains(course.getId());
    }

    private boolean checkForLessonUsing(Course course) {
        return serviceLesson.getAllAsDto()
                .stream()
                .map(LessonDto::getCourseId)
                .collect(Collectors.toList())
                .contains(course.getId());
    }
}

