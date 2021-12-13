package ua.com.foxminded.classtimetable.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.service.TeacherService;
import ua.com.foxminded.classtimetable.validators.TeacherValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/rest/v1/teachers")
public class TeacherRestController {

    @Autowired
    private TeacherValidator validatorTeacher;

    private final TeacherService serviceTeacher;

    public TeacherRestController(TeacherService serviceTeacher) {
        this.serviceTeacher = serviceTeacher;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDto> showAll() {
        return serviceTeacher.getAllAsDto();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDto showById(@PathVariable("id") @Min(0) int id) {
        return serviceTeacher.getByIdAsDto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToDB(@Valid @RequestBody TeacherDto teacherDto) {
        serviceTeacher.createFromDto(teacherDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody TeacherDto teacherDto) {
        serviceTeacher.updateFromDto(teacherDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Min(0) int id,
                       HttpServletRequest request) {
        validatorTeacher.checkForDeletion(serviceTeacher.getByIdAsDto(id), request);
        serviceTeacher.deleteById(id);
    }
}
