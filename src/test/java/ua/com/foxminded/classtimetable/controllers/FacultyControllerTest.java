package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.entities.Faculty;
import ua.com.foxminded.classtimetable.service.FacultyService;

@RunWith(MockitoJUnitRunner.class)
public class FacultyControllerTest {

    @InjectMocks
    private FacultyController facultyControllerMock;

    @Mock
    private FacultyService facultyServiceMock;

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        facultyControllerMock.showAll(model);

        Mockito.verify(facultyServiceMock).getAll();
    }

    @Test
    public void should_callShowByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        int id = 1;
        ModelMap model = new ModelMap();

        facultyControllerMock.showById(id, model);

        Mockito.verify(facultyServiceMock).getById(id);

    }

    @Test
    public void should_callAddToDBMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Faculty faculty = new Faculty();
        faculty.setFacultyName("Faculty name");

        facultyControllerMock.addToDB(faculty);

        Mockito.verify(facultyServiceMock).create(faculty);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Faculty faculty = new Faculty();
        faculty.setFacultyName("Another faculty name");

        facultyControllerMock.update(faculty);

        Mockito.verify(facultyServiceMock).update(faculty);

    }

    @Test
    public void should_deleteMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Faculty faculty = new Faculty();
        faculty.setFacultyName("And another faculty name");

        facultyControllerMock.create(faculty);
        facultyControllerMock.delete(faculty);

        Mockito.verify(facultyServiceMock).delete(faculty);

    }

}
