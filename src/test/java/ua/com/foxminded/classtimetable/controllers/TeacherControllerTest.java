package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.entities.Teacher;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.service.TeacherService;

@RunWith(MockitoJUnitRunner.class)
public class TeacherControllerTest {

    @InjectMocks
    private TeacherController teacherControllerMock;

    @Mock
    private TeacherService teacherServiceMock;

    @Mock
    private FacultyService facultyServiceMock;

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        teacherControllerMock.showAll(model);

        Mockito.verify(teacherServiceMock).getAll();
    }

    @Test
    public void should_callShowByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        int id = 12;
        ModelMap model = new ModelMap();

        teacherControllerMock.showById(id, model);

        Mockito.verify(teacherServiceMock).getById(id);
    }

    @Test
    public void should_callAddToDBMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Jeff");
        teacher.setLastName("Coff");
        teacher.setFacultyId(3);

        teacherControllerMock.addToDB(teacher);

        Mockito.verify(teacherServiceMock).create(teacher);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Francesco");
        teacher.setLastName("Donni");
        teacher.setFacultyId(2);

        teacherControllerMock.update(teacher);

        Mockito.verify(teacherServiceMock).update(teacher);
    }

    @Test
    public void should_deleteMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        Teacher teacher = new Teacher();
        teacher.setFirstName("Pit");
        teacher.setLastName("Garrix");
        teacher.setFacultyId(1);

        teacherControllerMock.create(teacher, model);
        teacherControllerMock.delete(teacher);

        Mockito.verify(teacherServiceMock).delete(teacher);
    }

}
