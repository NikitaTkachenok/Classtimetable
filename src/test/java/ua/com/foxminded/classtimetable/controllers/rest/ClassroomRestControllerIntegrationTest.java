package ua.com.foxminded.classtimetable.controllers.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassroomRestControllerIntegrationTest {

    @Autowired
    private ClassroomRestController classroomRestController;

    @Test
    public void should_createNewClassroom_when_controllerCallsAddToDBMethod() {

        ClassroomDto classroom = new ClassroomDto();
        classroom.setId(16);
        classroom.setBuildingId(1);
        classroom.setRoomCapacity(25);
        classroom.setRoomType("Lecture");
        classroom.setRoomName("A-3");

        assertThat(classroomRestController.showAll(), not(hasItem(classroom)));

        classroomRestController.addToDB(classroom);

        assertThat(classroomRestController.showAll(), hasItem(classroom));
        assertEquals(classroom, classroomRestController.showById(classroom.getId()));

    }

    @Test
    public void should_updateClassroom_when_controllerCallsUpdateMethod() {

        ClassroomDto classroom = classroomRestController.showById(9);
        classroom.setRoomName("New name");
        classroomRestController.update(classroom);

        assertEquals(classroomRestController.showById(9).getRoomName(), "New name");

    }

    @Test
    public void should_deleteClassroom_when_controllerCallsDeleteMethod() {

        HttpServletRequest request = new MockHttpServletRequest();
        ClassroomDto classroom = new ClassroomDto();
        classroom.setId(16);
        classroom.setBuildingId(2);
        classroom.setRoomCapacity(2);
        classroom.setRoomType("Lecture");
        classroom.setRoomName("С-1");

        classroomRestController.addToDB(classroom);
        assertThat(classroomRestController.showAll(), hasItem(classroom));

        classroomRestController.delete(classroom.getId(),request);
        assertThat(classroomRestController.showAll(), not(hasItem(classroom)));

    }

}
