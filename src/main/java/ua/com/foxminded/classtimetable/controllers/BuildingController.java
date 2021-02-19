package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.dao.BuildingDao;
import ua.com.foxminded.classtimetable.entities.Building;

@Controller
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingDao daoBuilding;

    public BuildingController(BuildingDao daoBuilding) {
        this.daoBuilding = daoBuilding;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("buildings", daoBuilding.getAll());
        return "buildings/showAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("building", daoBuilding.getById(id));
        return "buildings/showById";
    }

    @GetMapping("/newBuilding")
    public String create(@ModelAttribute("building") Building building) {
        return "buildings/create";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("building") Building building) {
        daoBuilding.create(building);
        return "redirect:/buildings";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("building") Building building) {
        daoBuilding.update(building);
        return "redirect:/buildings";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("building") Building building) {
        daoBuilding.delete(building);
        return "redirect:/buildings";
    }
}
