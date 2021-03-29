package ua.com.foxminded.classtimetable.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.entities.Course;

@Repository
public class CourseDao extends AbstractDao<Course> implements DaoInterface<Course> {

    public CourseDao() {
        setClazz(Course.class);
    }

}
