package ua.com.foxminded.classtimetable.validators;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.controllers.exceptions.IndelibleEntityException;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.repository.entities.Faculty;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Component
public class FacultyValidator {

    private final TeacherService serviceTeacher;
    private final StudentService serviceStudent;

    public FacultyValidator(TeacherService serviceTeacher, StudentService serviceStudent) {
        this.serviceTeacher = serviceTeacher;
        this.serviceStudent = serviceStudent;
    }

    public boolean checkForDeletion(Faculty faculty, HttpServletRequest request) {
        if (checkForTeacherUsing(faculty)) {
            throw new IndelibleEntityException
                    ("The faculty cannot be deleted because still there are teachers which use it",
                            request.getHeader("Referer"));
        }
        if (checkForStudentUsing(faculty)) {
            throw new IndelibleEntityException
                    ("The faculty cannot be deleted because still there are students which use it",
                            request.getHeader("Referer"));
        } else {
            return true;
        }
    }

    private boolean checkForTeacherUsing(Faculty faculty) {
        return serviceTeacher.getAllAsDto()
                .stream()
                .map(TeacherDto::getFacultyId)
                .distinct()
                .collect(Collectors.toList())
                .contains(faculty.getId());
    }

    private boolean checkForStudentUsing(Faculty faculty) {
        return serviceStudent.getAllAsDto()
                .stream()
                .map(StudentDto::getFacultyId)
                .distinct()
                .collect(Collectors.toList())
                .contains(faculty.getId());
    }

}
