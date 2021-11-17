package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.repository.entities.Building;
import ua.com.foxminded.classtimetable.service.BuildingService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Controller
@Validated
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService serviceBuilding;

    public BuildingController(BuildingService serviceBuilding) {
        this.serviceBuilding = serviceBuilding;
    }

    @GetMapping()
    public String showAll(ModelMap model) {
        model.addAttribute("buildings", serviceBuilding.getAll());
        return "buildings/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") @Min(0) int id,
                           ModelMap model) {
        model.addAttribute("building", serviceBuilding.getById(id));
        return "buildings/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("building") @NotNull Building building) {
        return "buildings/create";
    }

    @PostMapping()
    public String addToDB(@Valid @ModelAttribute("building") Building building) {
        serviceBuilding.create(building);
        return "redirect:/buildings";
    }

    @PutMapping("/{id}")
    public String update(@Valid @ModelAttribute("building") Building building) {
        serviceBuilding.update(building);
        return "redirect:/buildings";
    }

    @DeleteMapping("/{id}")
    public String delete(@Valid @ModelAttribute("building") Building building) {
        serviceBuilding.delete(building);
        return "redirect:/buildings";
    }
}
