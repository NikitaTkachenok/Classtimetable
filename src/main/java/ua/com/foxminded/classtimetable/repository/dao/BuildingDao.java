package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Building;

@Repository
public class BuildingDao extends AbstractDao<Building> implements DaoInterface<Building> {

    public BuildingDao() {
        setClazz(Building.class);
    }

}
