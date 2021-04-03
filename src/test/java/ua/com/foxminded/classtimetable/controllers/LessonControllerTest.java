package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.service.ClassroomService;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.LessonService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(MockitoJUnitRunner.class)
public class LessonControllerTest {

    @InjectMocks
    private LessonController lessonControllerMock;

    @Mock
    private LessonService lessonServiceMock;

    @Mock
    private ClassroomService classroomServiceMock;

    @Mock
    private CourseService courseServiceMock;

    @Mock
    private TeacherService teacherServiceMock;

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        lessonControllerMock.showAll(model);

        Mockito.verify(lessonServiceMock).getAll();
    }

    @Test
    public void should_callShowByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        int id = 135;
        ModelMap model = new ModelMap();

        lessonControllerMock.showById(id, model);

        Mockito.verify(lessonServiceMock).getById(id);
    }

    @Test
    public void should_callAddToDBMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        LessonDto lesson = new LessonDto();
        lesson.setDate(LocalDate.of(2021, 07, 01));
        lesson.setStartTime(LocalTime.of(9, 20));
        lesson.setEndTime(LocalTime.of(10, 00));
        lesson.setCourseId(1);
        lesson.setTeacherId(1);
        lesson.setClassroomId(5);

        lessonControllerMock.addToDB(lesson);

        Mockito.verify(lessonServiceMock).create(lesson);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        LessonDto lesson = new LessonDto();
        lesson.setDate(LocalDate.of(2021, 07, 02));
        lesson.setStartTime(LocalTime.of(10, 20));
        lesson.setEndTime(LocalTime.of(12, 00));
        lesson.setCourseId(2);
        lesson.setTeacherId(2);
        lesson.setClassroomId(6);

        lessonControllerMock.update(lesson);

        Mockito.verify(lessonServiceMock).update(lesson);
    }

    @Test
    public void should_deleteMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        LessonDto lesson = new LessonDto();
        lesson.setDate(LocalDate.of(2021, 7, 3));
        lesson.setStartTime(LocalTime.of(12, 20));
        lesson.setEndTime(LocalTime.of(14, 00));
        lesson.setCourseId(3);
        lesson.setTeacherId(3);
        lesson.setClassroomId(7);

        lessonControllerMock.create(lesson, model);
        lessonControllerMock.delete(lesson);

        Mockito.verify(lessonServiceMock).delete(lesson);
    }
}
