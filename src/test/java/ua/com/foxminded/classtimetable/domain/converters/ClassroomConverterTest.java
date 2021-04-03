package ua.com.foxminded.classtimetable.domain.converters;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.repository.dao.BuildingDao;
import ua.com.foxminded.classtimetable.repository.entities.Building;
import ua.com.foxminded.classtimetable.repository.entities.Classroom;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomConverterTest {

    @InjectMocks
    private ClassroomConverter classroomConverterMock;

    @Mock
    private BuildingDao buildingDaoMock;

    @Test
    public void should_returnDto_when_argumentOfMethodIsEntity() {

        Building building = new Building();
        building.setId(1);
        Classroom classroom = new Classroom();
        classroom.setId(1);
        classroom.setRoomName("roomName");
        classroom.setRoomType("roomType");
        classroom.setRoomCapacity(15);
        classroom.setBuilding(building);
        ClassroomDto dto = new ClassroomDto();
        dto.setId(1);
        dto.setRoomName("roomName");
        dto.setRoomType("roomType");
        dto.setRoomCapacity(15);
        dto.setBuildingId(1);

        Assert.assertEquals(dto, classroomConverterMock.toDto(classroom));
    }

    @Test
    public void should_returnEntity_when_argumentOfMethodIsDto() {

        ClassroomDto dto = new ClassroomDto();
        dto.setId(2);
        dto.setRoomName("name");
        dto.setRoomType("type");
        dto.setRoomCapacity(50);
        dto.setBuildingId(2);
        Classroom classroom = new Classroom();
        classroom.setId(2);
        classroom.setRoomName("name");
        classroom.setRoomType("type");
        classroom.setRoomCapacity(50);
        classroom.setBuilding(buildingDaoMock.getById(2));

        Assert.assertEquals(classroom, classroomConverterMock.toEntity(dto));
    }

    @Test
    public void should_callToEntityMethodInDaoClass_when_converterClassCallAppropriateMethod() {

        ClassroomDto dto = new ClassroomDto();
        dto.setRoomName("room's name");
        dto.setRoomType("room's type");
        dto.setRoomCapacity(25);
        dto.setBuildingId(1);

        classroomConverterMock.toEntity(dto);

        Mockito.verify(buildingDaoMock).getById(dto.getBuildingId());
    }

}
