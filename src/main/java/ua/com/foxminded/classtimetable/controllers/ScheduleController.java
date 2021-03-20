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
    public String showForm() {
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
                    firstName, lastName, beginDate, endDate));
        } else {
            model.addAttribute("schedule", serviceStudent.receiveLessonsOnDateRange(
                    firstName, lastName, beginDate, endDate));
        }
        model.addAttribute("classrooms", serviceClassroom);
        model.addAttribute("courses", serviceCourse);
        model.addAttribute("teachers", serviceTeacher);
        return "schedule/show";
    }
}
