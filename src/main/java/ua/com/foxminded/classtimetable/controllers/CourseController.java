package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.repository.entities.Course;
import ua.com.foxminded.classtimetable.service.CourseService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Controller
@Validated
@RequestMapping("/courses")
public class CourseController {

    private final CourseService serviceCourse;

    public CourseController(CourseService serviceCourse) {
        this.serviceCourse = serviceCourse;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("courses", serviceCourse.getAll());
        return "courses/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") @Min(0) int id,
                           ModelMap model) {
        model.addAttribute("course", serviceCourse.getById(id));
        return "courses/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("course") @NotNull Course course) {
        return "courses/create";
    }

    @PostMapping()
    public String addToDB(@Valid @ModelAttribute("course") Course course) {
        serviceCourse.create(course);
        return "redirect:/courses";
    }

    @PutMapping("/{id}")
    public String update(@Valid @ModelAttribute("course") Course course) {
        serviceCourse.update(course);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String delete(@Valid @ModelAttribute("course") Course course) {
        serviceCourse.delete(course);
        return "redirect:/courses";
    }
}
