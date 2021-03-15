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
        logger.info("getAll():\n {}", daoBuilding.getAll());
        return daoBuilding.getAll();
    }

    public Building getById(int id) {
        logger.info("getById: building = {}", daoBuilding.getById(id));
        return daoBuilding.getById(id);
    }

    public void create(Building building) {
        logger.info("create: student = {}", building);
        daoBuilding.create(building);
    }

    public void update(Building building) {
        logger.info("update: student = {}", building);
        daoBuilding.update(building);
    }

    public void delete(Building building) {
        logger.info("update: student = {}", building);
        daoBuilding.delete(building);
    }

}
