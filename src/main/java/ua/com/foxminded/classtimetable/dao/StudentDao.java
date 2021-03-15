package ua.com.foxminded.classtimetable.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.entities.Student;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StudentDao implements DaoInterface<Student> {

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_STUDENT_BY_ID = "SELECT * FROM students WHERE id = ?";
    private static final String GET_ALL_STUDENTS = "SELECT * FROM students";
    private static final String ADD_NEW_STUDENT = "INSERT INTO students (first_name, last_name, faculty_id) VALUES (?, ?, ?)";
    private static final String UPDATE_STUDENT = "UPDATE students SET first_name = ?, last_name = ?, faculty_id = ? WHERE id = ?";
    private static final String REMOVE_STUDENT = "DELETE FROM students WHERE id = ?";

    @Autowired
    public StudentDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Student getById(int id) {
        return jdbcTemplate.queryForObject(GET_STUDENT_BY_ID, rowMapper(), id);
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.query(GET_ALL_STUDENTS, rowMapper());
    }

    @Override
    public void create(Student student) {
        jdbcTemplate.update(ADD_NEW_STUDENT, student.getFirstName(), student.getLastName(), student.getFacultyId());
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update(UPDATE_STUDENT, student.getFirstName(), student.getLastName(), student.getFacultyId(),
                student.getId());
    }

    @Override
    public void delete(Student student) {
        jdbcTemplate.update(REMOVE_STUDENT, student.getId());
    }

    private RowMapper<Student> rowMapper() {
        RowMapper<Student> map = (resultSet, rowNumber) -> {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setFacultyId(resultSet.getInt("faculty_id"));
            return student;
        };
        return map;
    }
}
