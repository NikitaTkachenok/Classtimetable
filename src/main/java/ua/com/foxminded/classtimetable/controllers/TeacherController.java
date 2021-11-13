package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService serviceTeacher;
    private final FacultyService serviceFaculty;
    private final CourseService serviceCourse;

    public TeacherController(TeacherService serviceTeacher, FacultyService serviceFaculty,
                             CourseService serviceCourse) {
        this.serviceTeacher = serviceTeacher;
        this.serviceFaculty = serviceFaculty;
        this.serviceCourse = serviceCourse;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("teachers", serviceTeacher.getAllAsDto())
                .addAttribute("faculties", serviceFaculty)
                .addAttribute("courses", serviceCourse);
        return "teachers/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("teacher", serviceTeacher.getByIdAsDto(id))
                .addAttribute("faculties", serviceFaculty.getAll())
                .addAttribute("allCourses", serviceCourse.getAll());
        return "teachers/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("teacher") TeacherDto teacher, ModelMap model) {
        model.addAttribute("faculties", serviceFaculty.getAll())
                .addAttribute("allCourses", serviceCourse.getAll());
        return "teachers/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("teacher") TeacherDto teacher) {
        serviceTeacher.createFromDto(teacher);
        return "redirect:/teachers";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("teacher") TeacherDto teacher) {
        serviceTeacher.updateFromDto(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("teacher") TeacherDto teacher) {
        serviceTeacher.deleteById(teacher.getId());
        return "redirect:/teachers";
    }

}
