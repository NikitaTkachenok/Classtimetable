package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Faculty;

@Repository
public class FacultyDao extends AbstractDao<Faculty> implements DaoInterface<Faculty> {

    public FacultyDao() {
        setClazz(Faculty.class);
    }

}
