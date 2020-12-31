package ua.com.foxminded.classtimetable.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.classtimetable.entities.Course;

@Repository
public class CourseDao implements DaoInterface<Course> {

	private final JdbcTemplate jdbcTemplate;

	private static final String GET_COURSE_BY_ID = "SELECT * FROM courses WHERE id = ?";
	private static final String GET_ALL_COURSES = "SELECT * FROM courses";
	private static final String ADD_NEW_COURSE = "INSERT INTO courses (course_name) VALUES (?)";
	private static final String UPDATE_COURSE = "UPDATE courses SET course_name = ? WHERE id = ?";
	private static final String REMOVE_COURSE = "DELETE FROM courses WHERE id = ?";

	@Autowired
	public CourseDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Course getById(int id) {
		return jdbcTemplate.queryForObject(GET_COURSE_BY_ID, rowMapper(), id);
	}

	@Override
	public List<Course> getAll() {
		return jdbcTemplate.query(GET_ALL_COURSES, rowMapper());
	}

	@Override
	public void create(Course course) {
		jdbcTemplate.update(ADD_NEW_COURSE, course.getCourseName());
	}

	@Override
	public void update(Course course) {
		jdbcTemplate.update(UPDATE_COURSE, course.getCourseName(), course.getId());
	}

	@Override
	public void delete(Course course) {
		jdbcTemplate.update(REMOVE_COURSE, course.getId());
	}

	private RowMapper<Course> rowMapper() {
		RowMapper<Course> map = (resultSet, rowNumber) -> {
			Course course = new Course();
			course.setId(resultSet.getInt("id"));
			course.setCourseName(resultSet.getString("course_name"));
			return course;
		};
		return map;
	}
}
