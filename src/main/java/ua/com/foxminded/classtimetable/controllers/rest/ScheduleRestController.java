package ua.com.foxminded.classtimetable.controllers.rest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("/rest/v1/schedule")
public class ScheduleRestController {

    private final TeacherService serviceTeacher;
    private final StudentService serviceStudent;

    public ScheduleRestController(TeacherService serviceTeacher, StudentService serviceStudent) {
        this.serviceTeacher = serviceTeacher;
        this.serviceStudent = serviceStudent;
    }

    @GetMapping("/show")
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDto> getSchedule(@NotNull(message = "A person's selection is mandatory!")
                                       @RequestParam Object person,
                                       @NotNull(message = "The beginning of the date range is mandatory!")
                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                                       @NotNull(message = "The end of the date range is mandatory!")
                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<LessonDto> lessons;
        String id = person.toString().substring(
                person.toString().indexOf("id=") + 3, person.toString().indexOf("id=") + 4);
        if (person.toString().contains("Teacher")) {
            lessons = serviceTeacher.receiveLessonsOnDateRange(Integer.parseInt(id), beginDate, endDate);
        } else {
            lessons = serviceStudent.receiveLessonsOnDateRange(Integer.parseInt(id), beginDate, endDate);
        }
        return lessons;
    }
}
