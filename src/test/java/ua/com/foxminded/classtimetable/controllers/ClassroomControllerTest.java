package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.entities.Classroom;
import ua.com.foxminded.classtimetable.service.BuildingService;
import ua.com.foxminded.classtimetable.service.ClassroomService;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomControllerTest {

    @InjectMocks
    private ClassroomController classroomControllerMock;

    @Mock
    private ClassroomService classroomServiceMock;

    @Mock
    private BuildingService buildingServiceMock;

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        classroomControllerMock.showAll(model);

        Mockito.verify(classroomServiceMock).getAll();
    }

    @Test
    public void should_callShowByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        int id = 5;
        ModelMap model = new ModelMap();

        classroomControllerMock.showById(id, model);

        Mockito.verify(classroomServiceMock).getById(id);
    }

    @Test
    public void should_callAddToDBMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Classroom classroom = new Classroom();
        classroom.setRoomName("C-3");
        classroom.setRoomType("Class");
        classroom.setRoomCapacity(25);
        classroom.setBuildingId(3);

        classroomControllerMock.addToDB(classroom);

        Mockito.verify(classroomServiceMock).create(classroom);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        Classroom classroom = new Classroom();
        classroom.setRoomName("A-6");
        classroom.setRoomType("Lecture");
        classroom.setRoomCapacity(100);
        classroom.setBuildingId(1);

        classroomControllerMock.update(classroom, model);

        Mockito.verify(classroomServiceMock).update(classroom);

    }

    @Test
    public void should_deleteMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        Classroom classroom = new Classroom();
        classroom.setRoomName("B-10");
        classroom.setRoomType("Laboratory");
        classroom.setRoomCapacity(10);
        classroom.setBuildingId(2);

        classroomControllerMock.create(classroom, model);
        classroomControllerMock.delete(classroom);

        Mockito.verify(classroomServiceMock).delete(classroom);
    }

}
