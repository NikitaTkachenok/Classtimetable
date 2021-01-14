package ua.com.foxminded.classtimetable.dao;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.classtimetable.entities.Lesson;

@Repository
public class LessonDao implements DaoInterface<Lesson> {

	private final JdbcTemplate jdbcTemplate;

	private static final String GET_LESSON_BY_ID = "SELECT * FROM lessons WHERE id = ?";
	private static final String GET_ALL_LESSONS = "SELECT * FROM lessons";
	private static final String GET_TEACHER_LESSONS_FOR_ONE_DATE = "SELECT * FROM lessons WHERE teacher_id = ? AND date = ?";
	private static final String GET_COURSE_LESSONS_FOR_ONE_DATE = "SELECT * FROM lessons WHERE course_id = ? AND date = ?";
	private static final String GET_TEACHER_LESSONS_IN_DATE_DIAPASON = "SELECT * FROM lessons WHERE teacher_id = ? AND date between ? AND ?";
	private static final String GET_COURSE_LESSONS_IN_DATE_DIAPASON = "SELECT * FROM lessons WHERE course_id = ? AND date between ? AND ?";
	private static final String ADD_NEW_LESSON = "INSERT INTO lessons (date, start_time, end_time, classroom_id, course_id, teacher_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_LESSON = "UPDATE lessons SET date = ?, start_time = ?, end_time = ?, classroom_id = ?, course_id = ?, teacher_id = ? WHERE id = ?";
	private static final String REMOVE_LESSON = "DELETE FROM lessons WHERE id = ?";

	public LessonDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Lesson getById(int id) {
		return jdbcTemplate.queryForObject(GET_LESSON_BY_ID, rowMapper(), id);
	}

	@Override
	public List<Lesson> getAll() {
		return jdbcTemplate.query(GET_ALL_LESSONS, rowMapper());
	}

	public List<Lesson> getLessonsForTeacherForOneDate(int teacherId, LocalDate date) {
		return jdbcTemplate.query(GET_TEACHER_LESSONS_FOR_ONE_DATE, rowMapper(), teacherId, date);
	}

	public List<Lesson> getLessonsWithOneCourseForOneDate(int courseId, LocalDate date) {
		return jdbcTemplate.query(GET_COURSE_LESSONS_FOR_ONE_DATE, rowMapper(), courseId, date);
	}

	public List<Lesson> getLessonsForTeacherOnDateDiapason(int teacherId, LocalDate startDate, LocalDate endDate) {
		return jdbcTemplate.query(GET_TEACHER_LESSONS_IN_DATE_DIAPASON, rowMapper(), teacherId, startDate, endDate);
	}

	public List<Lesson> getLessonsWithOneCourseOnDateDiapason(int courseId, LocalDate startDate, LocalDate endDate) {
		return jdbcTemplate.query(GET_COURSE_LESSONS_IN_DATE_DIAPASON, rowMapper(), courseId, startDate, endDate);
	}

	@Override
	public void create(Lesson lesson) {
		jdbcTemplate.update(ADD_NEW_LESSON, lesson.getDate(), lesson.getStartTime(), lesson.getEndTime(),
				lesson.getClassroomId(), lesson.getCourseId(), lesson.getTeacherId());
	}

	@Override
	public void update(Lesson lesson) {
		jdbcTemplate.update(UPDATE_LESSON, lesson.getDate(), lesson.getStartTime(), lesson.getEndTime(),
				lesson.getClassroomId(), lesson.getCourseId(), lesson.getTeacherId(), lesson.getId());
	}

	@Override
	public void delete(Lesson lesson) {
		jdbcTemplate.update(REMOVE_LESSON, lesson.getId());
	}

	private RowMapper<Lesson> rowMapper() {
		RowMapper<Lesson> map = (resultSet, rowNumber) -> {
			Lesson lesson = new Lesson();
			lesson.setId(resultSet.getInt("id"));
			lesson.setDate(resultSet.getDate("date").toLocalDate());
			lesson.setStartTime(resultSet.getTime("start_time").toLocalTime());
			lesson.setEndTime(resultSet.getTime("end_time").toLocalTime());
			lesson.setClassroomId(resultSet.getInt("classroom_id"));
			lesson.setCourseId(resultSet.getInt("course_id"));
			lesson.setTeacherId(resultSet.getInt("teacher_id"));
			return lesson;
		};
		return map;
	}

}
