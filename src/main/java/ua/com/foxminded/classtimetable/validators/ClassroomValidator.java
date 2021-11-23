package ua.com.foxminded.classtimetable.validators;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.controllers.exceptions.IndelibleEntityException;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Component
public class ClassroomValidator {

    private final LessonService serviceLesson;

    public ClassroomValidator(LessonService serviceLesson) {
        this.serviceLesson = serviceLesson;
    }

    public void checkForDeletion(ClassroomDto classroom, HttpServletRequest request) {
        boolean undeletable = serviceLesson.getAllAsDto()
                .stream()
                .map(LessonDto::getClassroomId)
                .collect(Collectors.toList())
                .contains(classroom.getId());
        if (undeletable) {
            throw new IndelibleEntityException
                    ("The classroom cannot be deleted because still there are lessons which use it",
                            request.getHeader("Referer"));
        }
    }
}
