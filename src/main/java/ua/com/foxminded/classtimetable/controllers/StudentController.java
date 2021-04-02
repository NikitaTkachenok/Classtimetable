package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService serviceStudent;
    private final FacultyService serviceFaculty;
    private final CourseService serviceCourse;

    public StudentController(StudentService serviceStudent, FacultyService serviceFaculty,
                             CourseService serviceCourse) {
        this.serviceStudent = serviceStudent;
        this.serviceFaculty = serviceFaculty;
        this.serviceCourse = serviceCourse;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("students", serviceStudent.getAll())
                .addAttribute("faculties", serviceFaculty)
                .addAttribute("courses", serviceCourse);
        return "students/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("student", serviceStudent.getById(id))
                .addAttribute("faculties", serviceFaculty.getAll())
                .addAttribute("allCourses", serviceCourse.getAll());
        return "students/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("student") StudentDto student,
                         ModelMap model) {
        model.addAttribute("faculties", serviceFaculty.getAll())
                .addAttribute("allCourses", serviceCourse.getAll());
        return "students/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("student") StudentDto student) {
        serviceStudent.create(student);
        return "redirect:/students";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("student") StudentDto student) {
        serviceStudent.update(student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("student") StudentDto student) {
        serviceStudent.deleteById(student.getId());
        return "redirect:/students";
    }

}
