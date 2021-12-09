package ua.com.foxminded.classtimetable.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.classtimetable.repository.entities.Building;
import ua.com.foxminded.classtimetable.service.BuildingService;
import ua.com.foxminded.classtimetable.validators.BuildingValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/rest/v1/buildings")
public class BuildingRestController {

    @Autowired
    private BuildingValidator validatorBuilding;

    private final BuildingService serviceBuilding;

    public BuildingRestController(BuildingService buildingService) {
        this.serviceBuilding = buildingService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Building> showAll() {
        return serviceBuilding.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Building showById(@PathVariable("id") @Min(0) int id) {
        return serviceBuilding.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addToDB(@Valid @RequestBody Building building) {
        serviceBuilding.create(building);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody Building building,
                       @PathVariable("id") @Min(0) int id) {
        serviceBuilding.update(building);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") @Min(0) int id,
                       HttpServletRequest request) {
        validatorBuilding.checkForDeletion(serviceBuilding.getById(id), request);
        serviceBuilding.deleteById(id);
    }

}