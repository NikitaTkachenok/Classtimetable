package ua.com.foxminded.classtimetable.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.exceptions.ClassroomCapacityException;
import ua.com.foxminded.classtimetable.exceptions.InvalidLessonConditionsException;
import ua.com.foxminded.classtimetable.service.LessonService;
import ua.com.foxminded.classtimetable.validators.LessonValidator;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@RestController
@Validated
@RequestMapping("/rest/v1/lessons")
public class LessonRestController {

    @Autowired
    private LessonValidator validatorLesson;

    private final LessonService serviceLesson;

    public LessonRestController(LessonService serviceLesson) {
        this.serviceLesson = serviceLesson;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDto> showAll() {
        return serviceLesson.getAllAsDto();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LessonDto showById(@PathVariable("id") @Min(0) int id) {
        return serviceLesson.getByIdAsDto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToDB(@Valid @RequestBody LessonDto lessonDto) {
        validatorLesson.checkConditions(lessonDto);
        validatorLesson.checkClassroomCapacity(lessonDto);
        serviceLesson.createFromDto(lessonDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody LessonDto lessonDto) {
        serviceLesson.updateFromDto(lessonDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Min(0) int id) {
        serviceLesson.deleteById(id);
    }

    @ExceptionHandler({InvalidLessonConditionsException.class})
    public ResponseEntity<Object> handleInvalidLessonConditionsException(InvalidLessonConditionsException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler({ClassroomCapacityException.class})
    public ResponseEntity<Object> handleClassroomCapacityExceptionException(ClassroomCapacityException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }

}
