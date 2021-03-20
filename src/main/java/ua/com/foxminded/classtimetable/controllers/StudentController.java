package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.entities.Student;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService serviceStudent;
    private final FacultyService serviceFaculty;

    public StudentController(StudentService serviceStudent, FacultyService serviceFaculty) {
        this.serviceStudent = serviceStudent;
        this.serviceFaculty = serviceFaculty;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("students", serviceStudent.getAll());
        model.addAttribute("faculties", serviceFaculty);
        return "students/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("student", serviceStudent.getById(id))
                .addAttribute("faculties", serviceFaculty.getAll());
        return "students/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("student") Student student,
                         ModelMap model) {
        model.addAttribute("faculties", serviceFaculty.getAll());
        return "students/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("student") Student student) {
        serviceStudent.create(student);
        return "redirect:/students";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("student") Student student) {
        serviceStudent.update(student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("student") Student student) {
        serviceStudent.delete(student);
        return "redirect:/students";
    }
}
