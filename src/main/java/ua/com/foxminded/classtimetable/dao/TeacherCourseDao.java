package ua.com.foxminded.classtimetable.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.classtimetable.entities.TeacherCourse;

@Repository
public class TeacherCourseDao implements DaoInterface<TeacherCourse> {

	private final JdbcTemplate jdbcTemplate;

	private static final String GET_TEACHERCOURSE_BY_STUDENT_ID = "SELECT * FROM teachers_courses WHERE teacher_id = ?";
	private static final String GET_TEACHERCOURSE_BY_COURSE_ID = "SELECT * FROM teachers_courses WHERE course_id = ?";
	private static final String GET_ALL_TEACHERCOURSES = "SELECT * FROM teachers_courses";
	private static final String ADD_TEACHER_TO_COURSE = "INSERT INTO teachers_courses (teacher_id, course_id) VALUES (?, ?)";
	private static final String CHANGE_COURSE_OF_TEACHER = "UPDATE teachers_courses SET course_id = ? WHERE teacher_id = ?";
	private static final String REMOVE_TEACHER_FROM_COURSE = "DELETE FROM teachers_courses WHERE teacher_id = ? AND course_id = ?";

	@Autowired
	public TeacherCourseDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public TeacherCourse getById(int id) {
		return jdbcTemplate.queryForObject(GET_TEACHERCOURSE_BY_STUDENT_ID, rowMapper(), id);
	}

	public TeacherCourse getByCourseId(int id) {
		return jdbcTemplate.queryForObject(GET_TEACHERCOURSE_BY_COURSE_ID, rowMapper(), id);
	}

	@Override
	public List<TeacherCourse> getAll() {
		return jdbcTemplate.query(GET_ALL_TEACHERCOURSES, rowMapper());
	}

	@Override
	public void create(TeacherCourse teacherCourse) {
		jdbcTemplate.update(ADD_TEACHER_TO_COURSE, teacherCourse.getTeacherId(), teacherCourse.getCourseId());
	}

	@Override
	public void update(TeacherCourse teacherCourse) {
		jdbcTemplate.update(CHANGE_COURSE_OF_TEACHER, teacherCourse.getCourseId(), teacherCourse.getTeacherId());
	}

	@Override
	public void delete(TeacherCourse teacherCourse) {
		jdbcTemplate.update(REMOVE_TEACHER_FROM_COURSE, teacherCourse.getTeacherId(), teacherCourse.getCourseId());
	}

	private RowMapper<TeacherCourse> rowMapper() {
		RowMapper<TeacherCourse> map = (resultSet, rowNumber) -> {
			TeacherCourse teacherCourse = new TeacherCourse();
			teacherCourse.setTeacherId(resultSet.getInt("teacher_id"));
			teacherCourse.setCourseId(resultSet.getInt("course_id"));
			return teacherCourse;
		};
		return map;
	}
}
