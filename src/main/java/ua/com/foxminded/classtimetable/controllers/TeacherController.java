package ua.com.foxminded.classtimetable.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.dao.TeacherDao;
import ua.com.foxminded.classtimetable.entities.Teacher;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherDao daoTeacher;

    public TeacherController(TeacherDao daoTeacher) {
        this.daoTeacher = daoTeacher;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("teachers", daoTeacher.getAll());
        return "teachers/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", daoTeacher.getById(id));
        return "teachers/showById";
    }

    @GetMapping("/newTeacher")
    public String create(@ModelAttribute("teacher") Teacher teacher) {
        return "teachers/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("teacher") Teacher teacher) {
        daoTeacher.create(teacher);
        return "redirect:/teachers";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("teacher") Teacher teacher) {
        daoTeacher.update(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("teacher") Teacher teacher) {
        daoTeacher.delete(teacher);
        return "redirect:/teachers";
    }
}
