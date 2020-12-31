package ua.com.foxminded.classtimetable.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.classtimetable.entities.Faculty;

@Repository
public class FacultyDao implements DaoInterface<Faculty> {

	private final JdbcTemplate jdbcTemplate;

	private static final String GET_FACULTY_BY_ID = "SELECT * FROM faculties WHERE id = ?";
	private static final String GET_ALL_FACULTIES = "SELECT * FROM faculties";
	private static final String ADD_NEW_FACULTY = "INSERT INTO faculties (faculty_name) VALUES (?)";
	private static final String UPDATE_FACULTY = "UPDATE faculties SET faculty_name = ? WHERE id = ?";
	private static final String REMOVE_FACULTY = "DELETE FROM faculties WHERE id = ?";

	@Autowired
	public FacultyDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Faculty getById(int id) {
		return jdbcTemplate.queryForObject(GET_FACULTY_BY_ID, implementRowMapper(), id);
	}

	@Override
	public List<Faculty> getAll() {
		return jdbcTemplate.query(GET_ALL_FACULTIES, implementRowMapper());
	}

	@Override
	public void save(Faculty faculty) {
		jdbcTemplate.update(ADD_NEW_FACULTY, faculty.getFacultyName());
	}

	@Override
	public void update(Faculty faculty) {
		jdbcTemplate.update(UPDATE_FACULTY, faculty.getFacultyName(), faculty.getId());
	}

	@Override
	public void delete(Faculty faculty) {
		jdbcTemplate.update(REMOVE_FACULTY, faculty.getId());
	}

	private RowMapper<Faculty> implementRowMapper() {
		RowMapper<Faculty> map = (resultSet, rowNumber) -> {
			Faculty faculty = new Faculty();
			faculty.setId(resultSet.getInt("id"));
			faculty.setFacultyName(resultSet.getString("faculty_name"));
			return faculty;
		};
		return map;
	}
}
