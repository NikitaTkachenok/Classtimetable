package ua.com.foxminded.classtimetable.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.classtimetable.entities.Classroom;

@Repository
public class ClassroomDao implements DaoInterface<Classroom> {

	private final JdbcTemplate jdbcTemplate;

	private static final String GET_CLASSROOM_BY_ID = "SELECT * FROM classrooms WHERE id = ?";
	private static final String GET_ALL_CLASSROOMS = "SELECT * FROM classrooms";
	private static final String ADD_NEW_CLASSROOM = "INSERT INTO classrooms (room_name, room_type, room_capacity, building_id) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_CLASSROOM = "UPDATE classrooms SET room_name = ?, room_type = ?, room_capacity = ?, building_id = ? WHERE id = ?";
	private static final String REMOVE_CLASSROOM = "DELETE FROM classrooms WHERE id = ?";

	@Autowired
	public ClassroomDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Classroom getById(int id) {
		return jdbcTemplate.queryForObject(GET_CLASSROOM_BY_ID, rowMapper(), id);
	}

	@Override
	public List<Classroom> getAll() {
		return jdbcTemplate.query(GET_ALL_CLASSROOMS, rowMapper());
	}

	@Override
	public void create(Classroom classroom) {
		jdbcTemplate.update(ADD_NEW_CLASSROOM, classroom.getRoomName(), classroom.getRoomType(),
				classroom.getRoomCapacity(), classroom.getBuildingId());
	}

	@Override
	public void update(Classroom classroom) {
		jdbcTemplate.update(UPDATE_CLASSROOM, classroom.getRoomName(), classroom.getRoomType(),
				classroom.getRoomCapacity(), classroom.getBuildingId(), classroom.getId());
	}

	@Override
	public void delete(Classroom classroom) {
		jdbcTemplate.update(REMOVE_CLASSROOM, classroom.getId());
	}

	private RowMapper<Classroom> rowMapper() {
		RowMapper<Classroom> map = (resultSet, rowNumber) -> {
			Classroom classroom = new Classroom();
			classroom.setId(resultSet.getInt("id"));
			classroom.setRoomName(resultSet.getString("room_name"));
			classroom.setRoomType(resultSet.getString("room_type"));
			classroom.setRoomCapacity(resultSet.getInt("room_capacity"));
			classroom.setBuildingId(resultSet.getInt("building_id"));
			return classroom;
		};
		return map;
	}
	
}
