package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.entities.Student;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentControllerMock;

    @Mock
    private StudentService studentServiceMock;

    @Mock
    private FacultyService facultyServiceMock;

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        studentControllerMock.showAll(model);

        Mockito.verify(studentServiceMock).getAll();
    }

    @Test
    public void should_callShowByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        int id = 52;
        ModelMap model = new ModelMap();

        studentControllerMock.showById(id, model);

        Mockito.verify(studentServiceMock).getById(id);
    }

    @Test
    public void should_callAddToDBMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Student student = new Student();
        student.setFirstName("Jeffrey");
        student.setLastName("Coffee");
        student.setFacultyId(1);

        studentControllerMock.addToDB(student);

        Mockito.verify(studentServiceMock).create(student);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Student student = new Student();
        student.setFirstName("Fran");
        student.setLastName("Bow");
        student.setFacultyId(2);

        studentControllerMock.update(student);

        Mockito.verify(studentServiceMock).update(student);
    }

    @Test
    public void should_deleteMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        Student student = new Student();
        student.setFirstName("Peter");
        student.setLastName("Potter");
        student.setFacultyId(3);

        studentControllerMock.create(student, model);
        studentControllerMock.delete(student);

        Mockito.verify(studentServiceMock).delete(student);
    }

}
