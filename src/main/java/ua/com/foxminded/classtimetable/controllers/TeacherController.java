package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.entities.Teacher;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService serviceTeacher;
    private final FacultyService serviceFaculty;

    public TeacherController(TeacherService serviceTeacher, FacultyService serviceFaculty) {
        this.serviceTeacher = serviceTeacher;
        this.serviceFaculty = serviceFaculty;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("teachers", serviceTeacher.getAll());
        model.addAttribute("faculties", serviceFaculty);
        return "teachers/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("teacher", serviceTeacher.getById(id))
                .addAttribute("faculties", serviceFaculty.getAll());
        return "teachers/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("teacher") Teacher teacher,
                         ModelMap model) {
        model.addAttribute("faculties", serviceFaculty.getAll());
        return "teachers/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("teacher") Teacher teacher) {
        serviceTeacher.create(teacher);
        return "redirect:/teachers";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("teacher") Teacher teacher) {
        serviceTeacher.update(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("teacher") Teacher teacher) {
        serviceTeacher.delete(teacher);
        return "redirect:/teachers";
    }
}
