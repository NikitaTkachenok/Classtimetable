package ua.com.foxminded.classtimetable.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ua.com.foxminded.classtimetable.dao.LessonDao;
import ua.com.foxminded.classtimetable.entities.Lesson;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

	@Mock
	TeacherService teacherServiceMock;
	LessonDao lessonDaoMock;

	@Test
	public void should_returnEmptyList_when_teacherHasNoClassThisDate() {

		int teacherId = 1;
		LocalDate beginDate = LocalDate.of(2021, 01, 20);
		LocalDate endDate = LocalDate.of(2021, 01, 20);
		List<Lesson> emptyList = new ArrayList<>();

		Mockito.when(teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate)).thenReturn(emptyList);

		assertEquals(teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate), emptyList);
	}

	@Test
	public void should_returnEmptyList_when_TeacherWithThatIdIsNotExist() {

		int teacherId = 15;
		LocalDate beginDate = LocalDate.of(2021, 01, 20);
		LocalDate endDate = LocalDate.of(2021, 01, 20);
		List<Lesson> emptyList = new ArrayList<>();

		Mockito.when(teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate)).thenReturn(emptyList);

		assertEquals(teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate), emptyList);
	}

	@Test
	public void should_returnListWithLessons_when_TeacherIdAndDateRangeIsCorrect() {

		int teacherId = 3;
		LocalDate beginDate = LocalDate.of(2021, 06, 02);
		LocalDate endDate = LocalDate.of(2021, 06, 04);
		List<Lesson> listOfLessons = new LinkedList<>();

		listOfLessons.addAll(teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate));

		Mockito.when(teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate))
				.thenReturn(listOfLessons);

		assertEquals(teacherServiceMock.receiveLessonsOnDateRange(teacherId, beginDate, endDate), listOfLessons);

	}
}
