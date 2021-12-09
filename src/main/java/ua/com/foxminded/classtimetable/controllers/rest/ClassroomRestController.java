package ua.com.foxminded.classtimetable.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.service.ClassroomService;
import ua.com.foxminded.classtimetable.validators.ClassroomValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/rest/v1/classrooms")
public class ClassroomRestController {

    @Autowired
    private ClassroomValidator validatorClassroom;

    private final ClassroomService serviceClassroom;

    public ClassroomRestController(ClassroomService serviceClassroom) {
        this.serviceClassroom = serviceClassroom;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClassroomDto> showAll() {
        return serviceClassroom.getAllAsDto();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassroomDto showById(@PathVariable("id") @Min(0) int id) {
        return serviceClassroom.getByIdAsDto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToDB(@Valid @RequestBody ClassroomDto classroomDto) {
        serviceClassroom.createUseDto(classroomDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody ClassroomDto classroomDto,
                       @PathVariable("id") @Min(0) int id) {
        serviceClassroom.updateUseDto(classroomDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") @Min(0) int id,
                       HttpServletRequest request) {
        validatorClassroom.checkForDeletion(serviceClassroom.getByIdAsDto(id), request);
        serviceClassroom.deleteById(id);
    }

}