package ua.com.foxminded.classtimetable.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.entities.Building;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BuildingDao implements DaoInterface<Building> {

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_BUILDING_BY_ID = "SELECT * FROM buildings WHERE id = ?";
    private static final String GET_ALL_BUILDINGS = "SELECT * FROM buildings";
    private static final String ADD_NEW_BUILDING = "INSERT INTO buildings (building_name) VALUES (?)";
    private static final String UPDATE_BUILDING = "UPDATE buildings SET building_name = ? WHERE id = ?";
    private static final String REMOVE_BUILDING = "DELETE FROM buildings WHERE id = ?";

    @Autowired
    public BuildingDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Building getById(int id) {
        return jdbcTemplate.queryForObject(GET_BUILDING_BY_ID, rowMapper(), id);
    }

    @Override
    public List<Building> getAll() {
        return jdbcTemplate.query(GET_ALL_BUILDINGS, rowMapper());
    }

    @Override
    public void create(Building building) {
        jdbcTemplate.update(ADD_NEW_BUILDING, building.getBuildingName());
    }

    @Override
    public void update(Building building) {
        jdbcTemplate.update(UPDATE_BUILDING, building.getBuildingName(), building.getId());
    }

    @Override
    public void delete(Building building) {
        jdbcTemplate.update(REMOVE_BUILDING, building.getId());
    }

    private RowMapper<Building> rowMapper() {
        RowMapper<Building> map = (resultSet, rowNumber) -> {
            Building building = new Building();
            building.setId(resultSet.getInt("id"));
            building.setBuildingName(resultSet.getString("building_name"));
            return building;
        };
        return map;
    }

}
