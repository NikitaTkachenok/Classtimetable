package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.entities.Course;
import ua.com.foxminded.classtimetable.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService serviceCourse;

    public CourseController(CourseService serviceCourse) {
        this.serviceCourse = serviceCourse;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("courses", serviceCourse.getAll());
        return "courses/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", serviceCourse.getById(id));
        return "courses/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("course") Course course) {
        return "courses/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("course") Course course) {
        serviceCourse.create(course);
        return "redirect:/courses";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("course") Course course) {
        serviceCourse.update(course);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("course") Course course) {
        serviceCourse.delete(course);
        return "redirect:/courses";
    }
}
