package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.dao.FacultyDao;
import ua.com.foxminded.classtimetable.entities.Faculty;

import java.util.List;

@Service
public class FacultyService {
    private final FacultyDao daoFaculty;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyDao daoFaculty) {
        this.daoFaculty = daoFaculty;
    }

    public List<Faculty> getAll() {
        List<Faculty> allFaculties = daoFaculty.getAll();
        logger.info("getAll():\n {}", allFaculties);
        return allFaculties;
    }

    public Faculty getById(int id) {
        Faculty faculty = daoFaculty.getById(id);
        logger.info("getById: faculty = {}", faculty);
        return faculty;
    }

    public void create(Faculty faculty) {
        logger.info("create: faculty = {}", faculty);
        daoFaculty.create(faculty);
    }

    public void update(Faculty faculty) {
        logger.info("update: faculty = {}", faculty);
        daoFaculty.update(faculty);
    }

    public void delete(Faculty faculty) {
        logger.info("update: faculty = {}", faculty);
        daoFaculty.delete(faculty);
    }

}
