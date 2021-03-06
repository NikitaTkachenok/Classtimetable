package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.com.foxminded.classtimetable.repository.dao.BuildingRepository;
import ua.com.foxminded.classtimetable.repository.entities.Building;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final Logger logger = LoggerFactory.getLogger(BuildingService.class);

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public List<Building> getAll() {
        List<Building> allBuildings = buildingRepository.findAll();
        logger.info("getAll():\n {}", allBuildings);
        return allBuildings;
    }

    public Building getById(int id) {
        Building building = buildingRepository.findById(id).orElse(null);
        logger.info("getById: building = {}", building);
        return building;
    }

    public void create(Building building) {
        logger.info("create: building = {}", building);
        buildingRepository.saveAndFlush(building);
    }

    public void update(Building building) {
        logger.info("update: building = {}", building);
        buildingRepository.saveAndFlush(building);
    }

    public void delete(Building building) {
        logger.info("delete: building = {}", building);
        buildingRepository.delete(building);
    }

    public void deleteById(int id) {
        logger.info("delete: building with ID = {}", id);
        buildingRepository.deleteById(id);
    }

}
