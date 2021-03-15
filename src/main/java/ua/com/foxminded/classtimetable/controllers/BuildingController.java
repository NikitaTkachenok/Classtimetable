package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.entities.Building;
import ua.com.foxminded.classtimetable.service.BuildingService;

@Controller
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService serviceBuilding;

    public BuildingController(BuildingService serviceBuilding) {
        this.serviceBuilding = serviceBuilding;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("buildings", serviceBuilding.getAll());
        return "buildings/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("building", serviceBuilding.getById(id));
        return "buildings/showById";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("building") Building building) {
        return "buildings/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("building") Building building) {
        serviceBuilding.create(building);
        return "redirect:/buildings";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("building") Building building) {
        serviceBuilding.update(building);
        return "redirect:/buildings";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("building") Building building) {
        serviceBuilding.delete(building);
        return "redirect:/buildings";
    }
}
