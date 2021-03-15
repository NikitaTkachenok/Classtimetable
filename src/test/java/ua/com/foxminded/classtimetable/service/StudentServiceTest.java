package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.dao.LessonDao;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentServiceMock;

    @Mock
    private LessonDao lessonDaoMock;

    @Test
    public void should_callMethodInDaoClass_when_methodArgumentsIsCorrect() {

        String studentFirstName = "Ellie";
        String studentLastName = "Tucci";
        LocalDate beginDate = LocalDate.of(2021, 02, 15);
        LocalDate endDate = LocalDate.of(2021, 02, 21);

        studentServiceMock.receiveLessonsOnDateRange(studentFirstName, studentLastName, beginDate, endDate);

        Mockito.verify(lessonDaoMock).getLessonsForStudentOnDateRange(
                studentFirstName, studentLastName, beginDate, endDate);
    }
}
