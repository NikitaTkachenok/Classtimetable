package ua.com.foxminded.classtimetable.domain.converters;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.repository.dao.BuildingDao;
import ua.com.foxminded.classtimetable.repository.entities.Classroom;

@Component
public class ClassroomConverter {

    private final BuildingDao daoBuilding;

    public ClassroomConverter(BuildingDao daoBuilding) {
        this.daoBuilding = daoBuilding;
    }

    public ClassroomDto toDto(Classroom classroom) {
        ClassroomDto dto = new ClassroomDto();
        dto.setId(classroom.getId());
        dto.setRoomName(classroom.getRoomName());
        dto.setRoomType(classroom.getRoomType());
        dto.setRoomCapacity(classroom.getRoomCapacity());
        dto.setBuildingId(classroom.getBuilding().getId());
        return dto;
    }

    public Classroom toEntity(ClassroomDto dto) {
        Classroom classroom = new Classroom();
        classroom.setId(dto.getId());
        classroom.setRoomName(dto.getRoomName());
        classroom.setRoomType(dto.getRoomType());
        classroom.setRoomCapacity(dto.getRoomCapacity());
        classroom.setBuilding(daoBuilding.getById(dto.getBuildingId()));
        return classroom;
    }

}
