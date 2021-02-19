package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonDao daoLesson;

    public LessonController(LessonDao daoLesson) {
        this.daoLesson = daoLesson;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("lessons", daoLesson.getAll());
        return "lessons/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("lesson", daoLesson.getById(id));
        return "lessons/showById";
    }

    @GetMapping("/newLesson")
    public String create(@ModelAttribute("lesson") Lesson lesson) {
        return "lessons/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("lesson") Lesson lesson) {
        daoLesson.create(lesson);
        return "redirect:/lessons";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("lesson") Lesson lesson) {
        daoLesson.update(lesson);
        return "redirect:/lessons";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("lesson") Lesson lesson) {
        daoLesson.delete(lesson);
        return "redirect:/lessons";
    }
}
