package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.repository.entities.Faculty;
import ua.com.foxminded.classtimetable.service.FacultyService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Controller
@Validated
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
    public String showById(@PathVariable("id") @Min(0) int id,
                           ModelMap model) {
        model.addAttribute("faculty", serviceFaculty.getById(id));
        return "faculties/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("faculty") @NotNull Faculty faculty) {
        return "faculties/create";
    }

    @PostMapping()
    public String addToDB(@Valid @ModelAttribute("faculty") Faculty faculty) {
        serviceFaculty.create(faculty);
        return "redirect:/faculties";
    }

    @PutMapping("/{id}")
    public String update(@Valid @ModelAttribute("faculty") Faculty faculty) {
        serviceFaculty.update(faculty);
        return "redirect:/faculties";
    }

    @DeleteMapping("/{id}")
    public String delete(@Valid @ModelAttribute("faculty") Faculty faculty) {
        serviceFaculty.delete(faculty);
        return "redirect:/faculties";
    }
}
