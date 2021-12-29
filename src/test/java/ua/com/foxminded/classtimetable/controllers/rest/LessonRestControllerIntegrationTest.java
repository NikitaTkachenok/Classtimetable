package ua.com.foxminded.classtimetable.controllers.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonRestControllerIntegrationTest {

    @Autowired
    private LessonRestController lessonRestController;

    @Test
    public void should_createNewLesson_when_controllerCallsAddToDBMethod() {

        LessonDto lesson = new LessonDto();
        lesson.setId(496);
        lesson.setDate(LocalDate.of(2021, 12, 7));
        lesson.setStartTime(LocalTime.of(9, 20));
        lesson.setEndTime(LocalTime.of(10, 0));
        lesson.setClassroomId(1);
        lesson.setCourseId(1);
        lesson.setTeacherId(1);

        assertThat(lessonRestController.showAll(), not(hasItem(lesson)));

        lessonRestController.addToDB(lesson);

        assertThat(lessonRestController.showAll(), hasItem(lesson));
        assertEquals(lesson, lessonRestController.showById(lesson.getId()));

    }

    @Test
    public void should_updateLesson_when_controllerCallsAddToDBMethod() {

        LessonDto lesson = lessonRestController.showById(1);
        lesson.setTeacherId(3);
        lesson.setCourseId(3);
        lesson.setClassroomId(3);

        lessonRestController.update(lesson);

        assertEquals(lessonRestController.showById(1), lesson);
    }

    @Test
    public void should_deleteTeacher_when_controllerCallsDeleteMethod() {

        LessonDto lesson = lessonRestController.showById(495);

        assertThat(lessonRestController.showAll(), hasItem(lesson));

        lessonRestController.delete(lesson.getId());
        assertThat(lessonRestController.showAll(), not(hasItem(lesson)));
    }

    @Test
    public void should_returnAppropriatedListOfLessons_when_controllerCallsGetScheduleMethod() {
    }

}
