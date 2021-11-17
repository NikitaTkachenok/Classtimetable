package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.repository.entities.Classroom;
import ua.com.foxminded.classtimetable.service.BuildingService;
import ua.com.foxminded.classtimetable.service.ClassroomService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Validated
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService serviceClassroom;
    private final BuildingService serviceBuilding;

    public ClassroomController(ClassroomService serviceClassroom, BuildingService serviceBuilding) {
        this.serviceClassroom = serviceClassroom;
        this.serviceBuilding = serviceBuilding;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("classrooms", serviceClassroom.getAllAsDto());
        model.addAttribute("buildings", serviceBuilding);
        return "classrooms/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") @Min(0) int id,
                           ModelMap model) {
        model.addAttribute("classroom", serviceClassroom.getByIdAsDto(id)).
                addAttribute("buildings", serviceBuilding.getAll()).
                addAttribute("classroomTypes", getClassroomTypes());
        return "classrooms/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("classroom") @NotNull ClassroomDto classroom,
                         ModelMap model) {
        model.addAttribute("classroomTypes", getClassroomTypes())
                .addAttribute("buildings", serviceBuilding.getAll());
        return "classrooms/create";
    }

    @PostMapping()
    public String addToDB(@Valid @ModelAttribute("classroom") ClassroomDto classroom) {
        serviceClassroom.createUseDto(classroom);
        return "redirect:/classrooms";
    }

    @PutMapping("/{id}")
    public String update(@Valid @ModelAttribute("classroom") ClassroomDto classroom,
                         ModelMap model) {
        serviceClassroom.updateUseDto(classroom);
        return "redirect:/classrooms";
    }

    @DeleteMapping("/{id}")
    public String delete(@Valid @ModelAttribute("classroom") ClassroomDto classroom) {
        serviceClassroom.deleteUseDto(classroom);
        return "redirect:/classrooms";
    }

    private List<String> getClassroomTypes() {
        return serviceClassroom.getAll()
                .stream()
                .map(Classroom::getRoomType)
                .distinct()
                .collect(Collectors.toList());
    }
}
