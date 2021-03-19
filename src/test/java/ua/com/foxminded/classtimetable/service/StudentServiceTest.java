package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.dao.StudentDao;
import ua.com.foxminded.classtimetable.entities.Student;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentServiceMock;

    @Mock
    private LessonDao lessonDaoMock;

    @Mock
    private StudentDao studentDaoMock;

    @Test
    public void should_callReceiveLessonsOnDateRangeMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        String studentFirstName = "Ellie";
        String studentLastName = "Tucci";
        LocalDate beginDate = LocalDate.of(2021, 2, 15);
        LocalDate endDate = LocalDate.of(2021, 2, 21);

        studentServiceMock.receiveLessonsOnDateRange(studentFirstName, studentLastName, beginDate, endDate);

        Mockito.verify(lessonDaoMock).getLessonsForStudentOnDateRange(
                studentFirstName, studentLastName, beginDate, endDate);
    }

    @Test
    public void should_callGetAllMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        studentServiceMock.getAll();

        Mockito.verify(studentDaoMock).getAll();
    }

    @Test
    public void should_callGetByIdMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        int id = 16;

        studentServiceMock.getById(id);

        Mockito.verify(studentDaoMock).getById(id);
    }

    @Test
    public void should_callCreateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Student student = new Student();
        student.setFirstName("Leo");
        student.setLastName("Leonberg");
        student.setFacultyId(2);

        studentServiceMock.create(student);

        Mockito.verify(studentDaoMock).create(student);
    }

    @Test
    public void should_callUpdateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Student student = new Student();
        student.setFirstName("Johan");
        student.setLastName("Bit");
        student.setFacultyId(1);

        studentServiceMock.update(student);

        Mockito.verify(studentDaoMock).update(student);
    }

    @Test
    public void should_callDeleteMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Student teacher = new Student();
        teacher.setFirstName("Jack");
        teacher.setLastName("Jackson");
        teacher.setFacultyId(3);

        studentServiceMock.create(teacher);
        studentServiceMock.delete(teacher);

        Mockito.verify(studentDaoMock).delete(teacher);
    }
}
