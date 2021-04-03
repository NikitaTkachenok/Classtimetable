package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.repository.entities.Course;

@Repository
public class CourseDao extends AbstractDao<Course> implements DaoInterface<Course> {

    public CourseDao() {
        setClazz(Course.class);
    }

}
