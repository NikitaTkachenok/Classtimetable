package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.dao.ClassroomDao;
import ua.com.foxminded.classtimetable.entities.Classroom;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomDao daoClassroom;
    private final Logger logger = LoggerFactory.getLogger(ClassroomService.class);

    public ClassroomService(ClassroomDao daoClassroom) {
        this.daoClassroom = daoClassroom;
    }

    public List<Classroom> getAll() {
        List<Classroom> allClassrooms = daoClassroom.getAll();
        logger.info("getAll():\n {}", allClassrooms);
        return allClassrooms;
    }

    public Classroom getById(int id) {
        Classroom classroom = daoClassroom.getById(id);
        logger.info("getById: classroom = {}", classroom);
        return classroom;
    }

    public void create(Classroom classroom) {
        logger.info("create: classroom = {}", classroom);
        daoClassroom.create(classroom);
    }

    public void update(Classroom classroom) {
        logger.info("update: classroom = {}", classroom);
        daoClassroom.update(classroom);
    }

    public void delete(Classroom classroom) {
        logger.info("update: classroom = {}", classroom);
        daoClassroom.delete(classroom);
    }

}
