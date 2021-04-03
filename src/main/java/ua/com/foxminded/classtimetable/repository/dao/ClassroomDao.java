package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Classroom;

@Repository
public class ClassroomDao extends AbstractDao<Classroom> implements DaoInterface<Classroom> {

    public ClassroomDao() {
        setClazz(Classroom.class);
    }

}
