package ua.com.foxminded.classtimetable.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.exceptions.IndelibleEntityException;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Component
public class TeacherValidator {

    private final LessonService serviceLesson;
    private final Logger logger = LoggerFactory.getLogger(BuildingValidator.class);

    public TeacherValidator(LessonService serviceLesson) {
        this.serviceLesson = serviceLesson;
    }

    public void checkForDeletion(TeacherDto teacherDto, HttpServletRequest request) {
        boolean undeletable = serviceLesson.getAllAsDto()
                .stream()
                .map(LessonDto::getTeacherId)
                .distinct()
                .collect(Collectors.toList())
                .contains(teacherDto.getId());
        if (undeletable) {
            logger.error("checkForDeletion failed: teacher = {}", teacherDto);
            throw new IndelibleEntityException
                    ("The teacher cannot be deleted because still there are lessons which he teaches",
                            request.getHeader("Referer"));
        }
    }
}
