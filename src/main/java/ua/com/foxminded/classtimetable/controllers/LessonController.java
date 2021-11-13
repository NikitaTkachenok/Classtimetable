package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
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
    public String showAll(ModelMap model) {
        model.addAttribute("lessons", serviceLesson.getAllAsDto());
        model.addAttribute("classrooms", serviceClassroom);
        model.addAttribute("courses", serviceCourse);
        model.addAttribute("teachers", serviceTeacher);
        return "lessons/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("lesson", serviceLesson.getByIdAsDto(id)).
                addAttribute("classrooms", serviceClassroom.getAllAsDto()).
                addAttribute("courses", serviceCourse.getAll()).
                addAttribute("teachers", serviceTeacher.getAllAsDto());
        return "lessons/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("lesson") LessonDto lesson,
                         ModelMap model) {
        model.addAttribute("classrooms", serviceClassroom.getAllAsDto()).
                addAttribute("courses", serviceCourse.getAll()).
                addAttribute("teachers", serviceTeacher.getAllAsDto());
        return "lessons/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("lesson") LessonDto lesson) {
        serviceLesson.createFromDto(lesson);
        return "redirect:/lessons";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("lesson") LessonDto lesson) {
        serviceLesson.updateFromDto(lesson);
        return "redirect:/lessons";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("lesson") LessonDto lesson) {
        serviceLesson.deleteFromDto(lesson);
        return "redirect:/lessons";
    }
}
