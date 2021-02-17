package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.dao.BuildingDao;
import ua.com.foxminded.classtimetable.entities.Building;

@Controller
@RequestMapping("/buildings")
public class BuldingsController {

    private final BuildingDao daoBuilding;

    public BuldingsController(BuildingDao daoBuilding) {
        this.daoBuilding = daoBuilding;
    }

    @GetMapping("/allBuildings")
    public String showAllBuildings(Model model) {
        model.addAttribute("buildings", daoBuilding.getAll());
        return "buildings/showAllBuildings";
    }

    @GetMapping("/{id}")
    public String showOneBuildingById(@PathVariable("id") int id, Model model) {
        model.addAttribute("building", daoBuilding.getById(id));
        return "buildings/showOneBuildingById";
    }

    @GetMapping("/newBuilding")
    public String createNewBuilding(@ModelAttribute("building") Building building) {
        return "buildings/createNewBuilding";
    }

    @PostMapping()
    public String addBuildingToDB(@ModelAttribute("building") Building building) {
        daoBuilding.create(building);
        return "redirect:/buildings/allBuildings";
    }

    @GetMapping("/{id}/edit")
    public String updateBuildingData(@PathVariable("id") int id, Model model) {
        model.addAttribute("building", daoBuilding.getById(id));
        return "buildings/updateBuildingData";
    }

    @PatchMapping("/{id}")
    public String updateBuildingIntoDB(@ModelAttribute("building") Building building) {
        daoBuilding.update(building);
        return "redirect:/buildings/allBuildings";
    }

    @DeleteMapping("/{id}")
    public String deleteBuildingfromDB(@ModelAttribute("building") Building building) {
        daoBuilding.delete(building);
        return "redirect:/buildings/allBuildings";
    }
}
