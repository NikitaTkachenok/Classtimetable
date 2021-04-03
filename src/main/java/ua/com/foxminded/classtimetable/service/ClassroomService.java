package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.domain.converters.ClassroomConverter;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.repository.dao.ClassroomDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomDao daoClassroom;
    private final ClassroomConverter converterClassroom;
    private final Logger logger = LoggerFactory.getLogger(ClassroomService.class);

    public ClassroomService(ClassroomDao daoClassroom, ClassroomConverter converterClassroom) {
        this.daoClassroom = daoClassroom;
        this.converterClassroom = converterClassroom;
    }

    public List<ClassroomDto> getAll() {
        List<ClassroomDto> allClassrooms = new ArrayList<>();
        daoClassroom.getAll().forEach(classroom -> allClassrooms.add(converterClassroom.toDto(classroom)));
        logger.info("getAll():\n {}", allClassrooms);
        return allClassrooms;
    }

    public ClassroomDto getById(int id) {
        ClassroomDto classroom = converterClassroom.toDto(daoClassroom.getById(id));
        logger.info("getById: classroom = {}", classroom);
        return classroom;
    }

    public void create(ClassroomDto classroom) {
        logger.info("create: classroom = {}", classroom);
        daoClassroom.create(converterClassroom.toEntity(classroom));
    }

    public void update(ClassroomDto classroom) {
        logger.info("update: classroom = {}", classroom);
        daoClassroom.update(converterClassroom.toEntity(classroom));
    }

    public void delete(ClassroomDto classroom) {
        logger.info("delete: classroom = {}", classroom);
        daoClassroom.delete(converterClassroom.toEntity(classroom));
    }

}
