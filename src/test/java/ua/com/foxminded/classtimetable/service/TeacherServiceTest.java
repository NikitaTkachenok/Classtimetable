package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.dao.TeacherDao;
import ua.com.foxminded.classtimetable.entities.Teacher;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherServiceMock;

    @Mock
    private LessonDao lessonDaoMock;

    @Mock
    private TeacherDao teacherDaoMock;

    @Test
    public void should_callReceiveLessonsOnDateRangeMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        String teacherFirstName = "Freya";
        String teacherLastName = "Shepp";
        LocalDate beginDate = LocalDate.of(2021, 03, 01);
        LocalDate endDate = LocalDate.of(2021, 03, 31);

        teacherServiceMock.receiveLessonsOnDateRange(teacherFirstName, teacherLastName, beginDate, endDate);

        Mockito.verify(lessonDaoMock).getLessonsForTeacherOnDateRange(teacherFirstName, teacherLastName, beginDate,
                endDate);
    }

    @Test
    public void should_callGetAllMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        teacherServiceMock.getAll();

        Mockito.verify(teacherDaoMock).getAll();
    }

    @Test
    public void should_callGetByIdMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        int id = 7;

        teacherServiceMock.getById(id);

        Mockito.verify(teacherDaoMock).getById(id);
    }

    @Test
    public void should_callCreateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("John");
        teacher.setLastName("Galt");
        teacher.setFacultyId(1);

        teacherServiceMock.create(teacher);

        Mockito.verify(teacherDaoMock).create(teacher);
    }

    @Test
    public void should_callUpdateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Joachim");
        teacher.setLastName("Belt");
        teacher.setFacultyId(2);

        teacherServiceMock.update(teacher);

        Mockito.verify(teacherDaoMock).update(teacher);
    }

    @Test
    public void should_callDeleteMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Peter");
        teacher.setLastName("Peterson");
        teacher.setFacultyId(3);

        teacherServiceMock.create(teacher);
        teacherServiceMock.delete(teacher);

        Mockito.verify(teacherDaoMock).delete(teacher);
    }
}
