package ua.com.foxminded.classtimetable.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.entities.StudentCourse;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StudentCourseDao implements DaoInterface<StudentCourse> {

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_STUDENTCOURSE_BY_STUDENT_ID = "SELECT * FROM students_courses WHERE student_id = ?";
    private static final String GET_STUDENTCOURSE_BY_COURSE_ID = "SELECT * FROM students_courses WHERE course_id = ?";
    private static final String GET_ALL_STUDENTCOURSES = "SELECT * FROM students_courses";
    private static final String ADD_STUDENT_TO_COURSE = "INSERT INTO students_courses (student_id, course_id) VALUES (?, ?)";
    private static final String CHANGE_COURSE_OF_STUDENT = "UPDATE students_courses SET course_id = ? WHERE student_id = ?";
    private static final String REMOVE_STUDENT_FROM_COURSE = "DELETE FROM students_courses WHERE student_id = ? AND course_id = ?";

    @Autowired
    public StudentCourseDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public StudentCourse getById(int id) {
        return jdbcTemplate.queryForObject(GET_STUDENTCOURSE_BY_COURSE_ID, rowMapper(), id);
    }

    public List<StudentCourse> getByStudentId(int id) {
        return jdbcTemplate.query(GET_STUDENTCOURSE_BY_STUDENT_ID, rowMapper(), id);
    }

    @Override
    public List<StudentCourse> getAll() {
        return jdbcTemplate.query(GET_ALL_STUDENTCOURSES, rowMapper());
    }

    @Override
    public void create(StudentCourse studentCourse) {
        jdbcTemplate.update(ADD_STUDENT_TO_COURSE, studentCourse.getStudentId(), studentCourse.getCourseId());
    }

    @Override
    public void update(StudentCourse studentCourse) {
        jdbcTemplate.update(CHANGE_COURSE_OF_STUDENT, studentCourse.getCourseId(), studentCourse.getStudentId());
    }

    @Override
    public void delete(StudentCourse studentCourse) {
        jdbcTemplate.update(REMOVE_STUDENT_FROM_COURSE, studentCourse.getStudentId(), studentCourse.getCourseId());
    }

    private RowMapper<StudentCourse> rowMapper() {
        RowMapper<StudentCourse> map = (resultSet, rowNumber) -> {
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudentId(resultSet.getInt("student_id"));
            studentCourse.setCourseId(resultSet.getInt("course_id"));
            return studentCourse;
        };
        return map;
    }

}
