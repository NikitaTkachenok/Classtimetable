package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.dao.StudentDao;
import ua.com.foxminded.classtimetable.entities.Student;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentDao daoStudent;

    public StudentController(StudentDao daoStudent) {
        this.daoStudent = daoStudent;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("students", daoStudent.getAll());
        return "students/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", daoStudent.getById(id));
        return "students/showById";
    }

    @GetMapping("/newStudent")
    public String create(@ModelAttribute("student") Student student) {
        return "students/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("student") Student student) {
        daoStudent.create(student);
        return "redirect:/students";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") Student student) {
        daoStudent.update(student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("student") Student student) {
        daoStudent.delete(student);
        return "redirect:/students";
    }
}
