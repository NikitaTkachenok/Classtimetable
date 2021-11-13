package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.repository.dao.FacultyRepository;
import ua.com.foxminded.classtimetable.repository.entities.Faculty;

import java.util.List;

@Service
public class FacultyService implements ServiceInterface<Faculty> {

    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> allFaculties = facultyRepository.findAll();
        logger.info("getAll():\n {}", allFaculties);
        return allFaculties;
    }

    @Override
    public Faculty getById(int id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        logger.info("getById: faculty = {}", faculty);
        return faculty;
    }

    @Override
    public void create(Faculty faculty) {
        logger.info("create: faculty = {}", faculty);
        facultyRepository.saveAndFlush(faculty);
    }

    @Override
    public void update(Faculty faculty) {
        logger.info("update: faculty = {}", faculty);
        facultyRepository.saveAndFlush(faculty);
    }

    @Override
    public void delete(Faculty faculty) {
        logger.info("delete: faculty = {}", faculty);
        facultyRepository.delete(faculty);
    }

    @Override
    public void deleteById(int id) {
        logger.info("delete: faculty with ID = {}", id);
        facultyRepository.deleteById(id);
    }

}
