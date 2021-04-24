package ua.com.foxminded.classtimetable.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.classtimetable.service.ClassroomService;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ClassroomService serviceClassroom;
    private final CourseService serviceCourse;
    private final TeacherService serviceTeacher;
    private final StudentService serviceStudent;

    public ScheduleController(ClassroomService serviceClassroom, CourseService serviceCourse,
                              TeacherService serviceTeacher, StudentService serviceStudent) {
        this.serviceClassroom = serviceClassroom;
        this.serviceCourse = serviceCourse;
        this.serviceTeacher = serviceTeacher;
        this.serviceStudent = serviceStudent;
    }

    @GetMapping()
    public String showForm(ModelMap model) {
        model.addAttribute("firstNames", receiveUniqueNames())
                .addAttribute("lastNames", receiveUniqueSurnames());
        return "schedule/fillData";
    }

    @GetMapping("/show")
    public String showSchedule(@RequestParam String role,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                               ModelMap model) {
        if (role.equals("teacher")) {
            model.addAttribute("schedule", serviceTeacher.receiveLessonsOnDateRange(
                    changeEncoding(firstName), changeEncoding(lastName), beginDate, endDate));
        } else {
            model.addAttribute("schedule", serviceStudent.receiveLessonsOnDateRange(
                    changeEncoding(firstName), changeEncoding(lastName), beginDate, endDate));
        }
        model.addAttribute("classrooms", serviceClassroom);
        model.addAttribute("courses", serviceCourse);
        model.addAttribute("teachers", serviceTeacher);
        return "schedule/show";
    }

    private Set<String> receiveUniqueNames() {
        List<String> names = new ArrayList<>();
        serviceTeacher.getAll().forEach(teacherDto -> names.add(teacherDto.getFirstName()));
        serviceStudent.getAll().forEach(studentDto -> names.add(studentDto.getFirstName()));
        return new HashSet<>(names);
    }

    private Set<String> receiveUniqueSurnames() {
        List<String> surnames = new ArrayList<>();
        serviceTeacher.getAll().forEach(teacherDto -> surnames.add(teacherDto.getLastName()));
        serviceStudent.getAll().forEach(studentDto -> surnames.add(studentDto.getLastName()));
        return new HashSet<>(surnames);
    }

    private String changeEncoding(String oldEncodedString) {
        return new String(oldEncodedString.getBytes(ISO_8859_1), UTF_8);
    }

    private String detachFirsName(String fullName) {
        return fullName.split(" ")[0];
    }

}
