package ua.com.foxminded.classtimetable.service;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ua.com.foxminded.classtimetable.dao.LessonDao;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

	@InjectMocks
	private StudentService studentServiceMock;

	@Mock
	private LessonDao lessonDaoMock;

	@Test
	public void should_callMethodInDaoClass_when_methodArgumentsIsCorrect() {

		int studentId = 1;
		LocalDate beginDate = LocalDate.of(2021, 02, 15);
		LocalDate endDate = LocalDate.of(2021, 02, 21);

		studentServiceMock.receiveLessonsOnDateRange(studentId, beginDate, endDate);

		Mockito.verify(lessonDaoMock).getLessonsForStudentOnDateRange(studentId, beginDate, endDate);
	}
}
