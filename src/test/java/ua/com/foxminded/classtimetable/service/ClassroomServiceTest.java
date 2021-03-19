package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.dao.ClassroomDao;
import ua.com.foxminded.classtimetable.entities.Classroom;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomServiceTest {

    @InjectMocks
    private ClassroomService classroomServiceMock;

    @Mock
    private ClassroomDao classroomDaoMock;

    @Test
    public void should_callGetAllMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        classroomServiceMock.getAll();

        Mockito.verify(classroomDaoMock).getAll();
    }

    @Test
    public void should_callGetByIdMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        int id = 4;

        classroomServiceMock.getById(id);

        Mockito.verify(classroomDaoMock).getById(id);
    }

    @Test
    public void should_callCreateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Classroom classroom = new Classroom();
        classroom.setRoomName("C-18");
        classroom.setRoomType("Class");
        classroom.setRoomCapacity(20);
        classroom.setBuildingId(3);

        classroomServiceMock.create(classroom);

        Mockito.verify(classroomDaoMock).create(classroom);
    }

    @Test
    public void should_callUpdateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Classroom classroom = new Classroom();
        classroom.setRoomName("B-50");
        classroom.setRoomType("Lecture");
        classroom.setRoomCapacity(100);
        classroom.setBuildingId(2);

        classroomServiceMock.update(classroom);

        Mockito.verify(classroomDaoMock).update(classroom);
    }

    @Test
    public void should_callDeleteMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Classroom classroom = new Classroom();
        classroom.setRoomName("A-20");
        classroom.setRoomType("Laboratory");
        classroom.setRoomCapacity(5);
        classroom.setBuildingId(1);

        classroomServiceMock.create(classroom);
        classroomServiceMock.delete(classroom);

        Mockito.verify(classroomDaoMock).delete(classroom);
    }

}
