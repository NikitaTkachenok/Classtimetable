package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.entities.Lesson;
import ua.com.foxminded.classtimetable.service.ClassroomService;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.LessonService;
import ua.com.foxminded.classtimetable.service.TeacherService;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService serviceLesson;
    private final ClassroomService serviceClassroom;
    private final CourseService serviceCourse;
    private final TeacherService serviceTeacher;

    public LessonController(LessonService serviceLesson, ClassroomService serviceClassroom, CourseService serviceCourse,
                            TeacherService serviceTeacher) {
        this.serviceLesson = serviceLesson;
        this.serviceClassroom = serviceClassroom;
        this.serviceCourse = serviceCourse;
        this.serviceTeacher = serviceTeacher;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("lessons", serviceLesson.getAll());
        model.addAttribute("classrooms", serviceClassroom);
        model.addAttribute("courses", serviceCourse);
        model.addAttribute("teachers", serviceTeacher);
        return "lessons/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("lesson", serviceLesson.getById(id)).
                addAttribute("classrooms", serviceClassroom.getAll()).
                addAttribute("courses", serviceCourse.getAll()).
                addAttribute("teachers", serviceTeacher.getAll());
        return "lessons/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("lesson") Lesson lesson,
                         Model model) {
        model.addAttribute("classrooms", serviceClassroom.getAll()).
                addAttribute("courses", serviceCourse.getAll()).
                addAttribute("teachers", serviceTeacher.getAll());
        return "lessons/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("lesson") Lesson lesson) {
        serviceLesson.create(lesson);
        return "redirect:/lessons";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("lesson") Lesson lesson) {
        serviceLesson.update(lesson);
        return "redirect:/lessons";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("lesson") Lesson lesson) {
        serviceLesson.delete(lesson);
        return "redirect:/lessons";
    }
}
