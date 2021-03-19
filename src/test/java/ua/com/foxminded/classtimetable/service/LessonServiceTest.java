package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(MockitoJUnitRunner.class)
public class LessonServiceTest {

    @InjectMocks
    private LessonService lessonServiceMock;

    @Mock
    private LessonDao lessonDaoMock;

    @Test
    public void should_callGetAllMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        lessonServiceMock.getAll();

        Mockito.verify(lessonDaoMock).getAll();
    }

    @Test
    public void should_callGetByIdMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        int id = 164;

        lessonServiceMock.getById(id);

        Mockito.verify(lessonDaoMock).getById(id);
    }

    @Test
    public void should_callCreateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Lesson lesson = new Lesson();
        lesson.setDate(LocalDate.of(2021, 06, 01));
        lesson.setStartTime(LocalTime.of(9, 20));
        lesson.setEndTime(LocalTime.of(10, 00));
        lesson.setCourseId(2);
        lesson.setTeacherId(3);
        lesson.setClassroomId(15);

        lessonServiceMock.create(lesson);

        Mockito.verify(lessonDaoMock).create(lesson);
    }

    @Test
    public void should_callUpdateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Lesson lesson = new Lesson();
        lesson.setDate(LocalDate.of(2021, 06, 02));
        lesson.setStartTime(LocalTime.of(10, 10));
        lesson.setEndTime(LocalTime.of(11, 50));
        lesson.setCourseId(3);
        lesson.setTeacherId(4);
        lesson.setClassroomId(16);

        lessonServiceMock.update(lesson);

        Mockito.verify(lessonDaoMock).update(lesson);
    }

    @Test
    public void should_callDeleteMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Lesson lesson = new Lesson();
        lesson.setDate(LocalDate.of(2021, 06, 03));
        lesson.setStartTime(LocalTime.of(12, 00));
        lesson.setEndTime(LocalTime.of(13, 40));
        lesson.setCourseId(4);
        lesson.setTeacherId(6);
        lesson.setClassroomId(20);

        lessonServiceMock.create(lesson);
        lessonServiceMock.delete(lesson);

        Mockito.verify(lessonDaoMock).delete(lesson);
    }
}
