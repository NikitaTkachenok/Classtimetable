package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.service.CourseService;
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

    @Mock
    private CourseService courseServiceMock;

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

        TeacherDto teacher = new TeacherDto();
        teacher.setFirstName("Jeff");
        teacher.setLastName("Coff");
        teacher.setFacultyId(1);

        teacherControllerMock.addToDB(teacher);

        Mockito.verify(teacherServiceMock).create(teacher);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        TeacherDto teacher = new TeacherDto();
        teacher.setFirstName("Francesco");
        teacher.setLastName("Donni");
        teacher.setFacultyId(2);

        teacherControllerMock.update(teacher);

        Mockito.verify(teacherServiceMock).update(teacher);
    }

    @Test
    public void should_deleteByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        TeacherDto teacher = new TeacherDto();
        teacher.setFirstName("Pit");
        teacher.setLastName("Garrix");
        teacher.setFacultyId(3);

        teacherControllerMock.create(teacher, model);
        teacherControllerMock.delete(teacher);

        Mockito.verify(teacherServiceMock).deleteById(teacher.getId());
    }

}
