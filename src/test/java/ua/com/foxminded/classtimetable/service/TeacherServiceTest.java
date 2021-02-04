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
public class TeacherServiceTest {

	@InjectMocks
	private TeacherService teacherServiceMock;

	@Mock
	private LessonDao lessonDaoMock;

	@Test
	public void should_callMethodInDaoClass_when_methodArgumentsIsCorrect() {

		int teacherId = 1;
		LocalDate beginDate = LocalDate.of(2021, 01, 20);
		LocalDate endDate = LocalDate.of(2021, 01, 23);

		teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate);

		Mockito.verify(lessonDaoMock).getLessonsForTeacherOnDateRange(teacherId, beginDate, endDate);
	}

}
