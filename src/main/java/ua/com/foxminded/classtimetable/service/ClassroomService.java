package ua.com.foxminded.classtimetable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.classtimetable.domain.converters.ClassroomConverter;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.repository.dao.ClassroomRepository;
import ua.com.foxminded.classtimetable.repository.entities.Classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomService implements ServiceInterface<Classroom> {

    private final ClassroomRepository classroomRepository;
    private final ClassroomConverter converterClassroom;
    private final Logger logger = LoggerFactory.getLogger(ClassroomService.class);

    public ClassroomService(ClassroomRepository classroomRepository, ClassroomConverter converterClassroom) {
        this.classroomRepository = classroomRepository;
        this.converterClassroom = converterClassroom;
    }

    @Override
    public List<Classroom> getAll() {
        List<Classroom> allClassrooms;
        allClassrooms = classroomRepository.findAll();
        logger.info("getAll():\n {}", allClassrooms);
        return allClassrooms;
    }

    public List<ClassroomDto> getAllAsDto() {
        return getAll()
                .stream()
                .map(converterClassroom::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Classroom getById(int id) {
        Classroom classroom = classroomRepository.findById(id).orElse(null);
        logger.info("getById: classroom = {}", classroom);
        return classroom;
    }

    public ClassroomDto getByIdAsDto(int id) {
        return converterClassroom.toDto(getById(id));
    }

    @Override
    public void create(Classroom classroom) {
        logger.info("create: classroom = {}", classroom);
        classroomRepository.saveAndFlush(classroom);
    }

    public void createUseDto(ClassroomDto classroom) {
        create(converterClassroom.toEntity(classroom));
    }

    @Override
    public void update(Classroom classroom) {
        logger.info("update: classroom = {}", classroom);
        classroomRepository.saveAndFlush(classroom);
    }

    public void updateUseDto(ClassroomDto classroom) {
        update(converterClassroom.toEntity(classroom));
    }

    @Override
    public void delete(Classroom classroom) {
        logger.info("delete: classroom = {}", classroom);
        classroomRepository.delete(classroom);
    }

    public void deleteUseDto(ClassroomDto classroom) {
        delete(converterClassroom.toEntity(classroom));
    }

    @Override
    public void deleteById(int id) {
        logger.info("delete: classroom with ID = {}", id);
        classroomRepository.deleteById(id);

    }

}
