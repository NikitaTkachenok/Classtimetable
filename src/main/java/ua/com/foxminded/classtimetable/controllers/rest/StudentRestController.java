package ua.com.foxminded.classtimetable.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.service.StudentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/rest/v1/students")
public class StudentRestController {

    private final StudentService serviceStudent;

    public StudentRestController(StudentService serviceStudent) {
        this.serviceStudent = serviceStudent;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> showAll() {
        return serviceStudent.getAllAsDto();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto showById(@PathVariable("id") @Min(0) int id) {
        return serviceStudent.getByIdAsDto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToDB(@Valid @RequestBody StudentDto studentDto) {
        serviceStudent.createFromDto(studentDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody StudentDto studentDto,
                       @PathVariable("id") @Min(0) int id) {
        serviceStudent.updateFromDto(studentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") @Min(0) int id) {
        serviceStudent.deleteById(id);
    }
}
