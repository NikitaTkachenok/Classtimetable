package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.repository.entities.Faculty;
import ua.com.foxminded.classtimetable.service.FacultyService;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService serviceFaculty;

    public FacultyController(FacultyService serviceFaculty) {
        this.serviceFaculty = serviceFaculty;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("faculties", serviceFaculty.getAll());
        return "faculties/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("faculty", serviceFaculty.getById(id));
        return "faculties/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("faculty") Faculty faculty) {
        return "faculties/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("faculty") Faculty faculty) {
        serviceFaculty.create(faculty);
        return "redirect:/faculties";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("faculty") Faculty faculty) {
        serviceFaculty.update(faculty);
        return "redirect:/faculties";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("faculty") Faculty faculty) {
        serviceFaculty.delete(faculty);
        return "redirect:/faculties";
    }
}
