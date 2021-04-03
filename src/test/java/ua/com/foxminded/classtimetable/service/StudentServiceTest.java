package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.domain.converters.StudentConverter;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.repository.dao.LessonDao;
import ua.com.foxminded.classtimetable.repository.dao.StudentDao;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentServiceMock;

    @Mock
    private LessonDao lessonDaoMock;

    @Mock
    private StudentDao studentDaoMock;

    @Mock
    private StudentConverter studentConverterMock;

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

        StudentDto student = new StudentDto();
        student.setFirstName("Leo");
        student.setLastName("Leonberg");
        student.setFacultyId(1);

        studentServiceMock.create(student);

        Mockito.verify(studentDaoMock).create(studentConverterMock.toEntity(student));
    }

    @Test
    public void should_callUpdateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        StudentDto student = new StudentDto();
        student.setFirstName("Johan");
        student.setLastName("Bit");
        student.setFacultyId(2);

        studentServiceMock.update(student);

        Mockito.verify(studentDaoMock).update(studentConverterMock.toEntity(student));
    }

    @Test
    public void should_callDeleteByIdMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        StudentDto student = new StudentDto();
        student.setFirstName("Joe");
        student.setLastName("Dassin");
        student.setFacultyId(1);

        studentServiceMock.create(student);
        studentServiceMock.deleteById(student.getId());

        Mockito.verify(studentDaoMock).deleteById(student.getId());
    }

}
