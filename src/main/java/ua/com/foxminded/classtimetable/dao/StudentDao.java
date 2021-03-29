package ua.com.foxminded.classtimetable.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.entities.Student;

@Repository
public class StudentDao extends AbstractDao<Student> implements DaoInterface<Student> {

    public StudentDao() {
        setClazz(Student.class);
    }

}
