package ua.com.foxminded.classtimetable.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.classtimetable.entities.Teacher;

@Repository
public class TeacherDao implements DaoInterface<Teacher> {

	private final JdbcTemplate jdbcTemplate;

	private static final String GET_TEACHER_BY_ID = "SELECT * FROM teachers WHERE id = ?";
	private static final String GET_ALL_TEACHERS = "SELECT * FROM teachers";
	private static final String ADD_NEW_TEACHER = "INSERT INTO teachers (first_name, last_name, faculty_id) VALUES (?, ?, ?)";
	private static final String UPDATE_TEACHER = "UPDATE teachers SET first_name = ?, last_name = ?, faculty_id = ? WHERE id = ?";
	private static final String REMOVE_TEACHER = "DELETE FROM teachers WHERE id = ?";

	@Autowired
	public TeacherDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Teacher getById(int id) {
		return jdbcTemplate.queryForObject(GET_TEACHER_BY_ID, rowMapper(), id);
	}

	@Override
	public List<Teacher> getAll() {
		return jdbcTemplate.query(GET_ALL_TEACHERS, rowMapper());
	}

	@Override
	public void create(Teacher teacher) {
		jdbcTemplate.update(ADD_NEW_TEACHER, teacher.getFirstName(), teacher.getLastName(), teacher.getFacultyId());
	}

	@Override
	public void update(Teacher teacher) {
		jdbcTemplate.update(UPDATE_TEACHER, teacher.getFirstName(), teacher.getLastName(), teacher.getFacultyId(),
				teacher.getId());
	}

	@Override
	public void delete(Teacher teacher) {
		jdbcTemplate.update(REMOVE_TEACHER, teacher.getId());
	}

	private RowMapper<Teacher> rowMapper() {
		RowMapper<Teacher> map = (resultSet, rowNumber) -> {
			Teacher teacher = new Teacher();
			teacher.setId(resultSet.getInt("id"));
			teacher.setFirstName(resultSet.getString("first_name"));
			teacher.setLastName(resultSet.getString("last_name"));
			teacher.setFacultyId(resultSet.getInt("faculty_id"));
			return teacher;
		};
		return map;
	}
}
