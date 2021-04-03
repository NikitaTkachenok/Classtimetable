package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Student;

@Repository
public class StudentDao extends AbstractDao<Student> implements DaoInterface<Student> {

    public StudentDao() {
        setClazz(Student.class);
    }

}
