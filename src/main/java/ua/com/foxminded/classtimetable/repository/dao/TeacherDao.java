package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Teacher;

@Repository
public class TeacherDao extends AbstractDao<Teacher> implements DaoInterface<Teacher> {

    public TeacherDao() {
        setClazz(Teacher.class);
    }


}
