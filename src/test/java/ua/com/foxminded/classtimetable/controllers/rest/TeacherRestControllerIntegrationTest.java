package ua.com.foxminded.classtimetable.controllers.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherRestControllerIntegrationTest {

    @Autowired
    private TeacherRestController teacherRestController;

    @Test
    public void should_createNewTeacher_when_controllerCallsAddToDBMethod() {

        Set<Integer> teacherCourses = new HashSet<>();
        teacherCourses.add(5);
        TeacherDto teacher = new TeacherDto();
        teacher.setId(13);
        teacher.setFirstName("Nick");
        teacher.setLastName("Jackson");
        teacher.setFacultyId(1);
        teacher.setCoursesId(teacherCourses);

        assertThat(teacherRestController.showAll(), not(hasItem(teacher)));

        teacherRestController.addToDB(teacher);

        assertThat(teacherRestController.showAll(), hasItem(teacher));
        assertEquals(teacher, teacherRestController.showById(teacher.getId()));

    }

    @Test
    public void should_updateTeacher_when_controllerCallsUpdateMethod() {

        TeacherDto teacher = teacherRestController.showById(1);
        teacher.setLastName("Johnson");
        teacherRestController.update(teacher);

        assertEquals(teacherRestController.showById(1).getLastName(), "Johnson");
    }

    @Test
    public void should_deleteTeacher_when_controllerCallsDeleteMethod() {

        HttpServletRequest request = new MockHttpServletRequest();
        Set<Integer> teacherCourses = new HashSet<>();
        TeacherDto teacher = new TeacherDto();
        teacher.setId(13);
        teacher.setFirstName("Peter");
        teacher.setLastName("Berg");
        teacher.setFacultyId(1);
        teacher.setCoursesId(teacherCourses);

        teacherRestController.addToDB(teacher);
        assertThat(teacherRestController.showAll(), hasItem(teacher));

        teacherRestController.delete(teacher.getId(), request);
        assertThat(teacherRestController.showAll(), not(hasItem(teacher)));
    }

}
