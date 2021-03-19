package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.dao.BuildingDao;
import ua.com.foxminded.classtimetable.entities.Building;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingDao daoBuilding;
    private final Logger logger = LoggerFactory.getLogger(BuildingService.class);

    public BuildingService(BuildingDao daoBuilding) {
        this.daoBuilding = daoBuilding;
    }

    public List<Building> getAll() {
        List<Building> allBuildings = daoBuilding.getAll();
        logger.info("getAll():\n {}", allBuildings);
        return allBuildings;
    }

    public Building getById(int id) {
        Building building = daoBuilding.getById(id);
        logger.info("getById: building = {}", building);
        return building;
    }

    public void create(Building building) {
        logger.info("create: building = {}", building);
        daoBuilding.create(building);
    }

    public void update(Building building) {
        logger.info("update: building = {}", building);
        daoBuilding.update(building);
    }

    public void delete(Building building) {
        logger.info("update: building = {}", building);
        daoBuilding.delete(building);
    }

}
