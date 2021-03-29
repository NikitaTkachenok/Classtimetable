package ua.com.foxminded.classtimetable.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.entities.Teacher;

@Repository
public class TeacherDao extends AbstractDao<Teacher> implements DaoInterface<Teacher> {

    public TeacherDao() {
        setClazz(Teacher.class);
    }


}
