package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.repository.entities.Course;
import ua.com.foxminded.classtimetable.service.CourseService;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

    @InjectMocks
    private CourseController courseControllerMock;

    @Mock
    private CourseService courseServiceMock;

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        courseControllerMock.showAll(model);

        Mockito.verify(courseServiceMock).getAll();
    }

    @Test
    public void should_callShowByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        int id = 7;
        ModelMap model = new ModelMap();

        courseControllerMock.showById(id, model);

        Mockito.verify(courseServiceMock).getById(id);
    }

    @Test
    public void should_callAddToDBMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Course course = new Course();
        course.setCourseName("Course's name");

        courseControllerMock.addToDB(course);

        Mockito.verify(courseServiceMock).create(course);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Course course = new Course();
        course.setCourseName("Another course's name");

        courseControllerMock.update(course);

        Mockito.verify(courseServiceMock).update(course);

    }

    @Test
    public void should_deleteMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Course course = new Course();
        course.setCourseName("And another course's name");

        courseControllerMock.create(course);
        courseControllerMock.delete(course);

        Mockito.verify(courseServiceMock).delete(course);

    }

}
