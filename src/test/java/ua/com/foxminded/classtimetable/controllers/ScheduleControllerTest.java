package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.service.ClassroomService;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController scheduleControllerMock;

    @Mock
    private ClassroomService classroomServiceMock;

    @Mock
    private CourseService courseServiceMock;

    @Mock
    private TeacherService teacherServiceMock;

    @Mock
    private StudentService studentServiceMock;

    @Test
    public void should_callReceiveLessonsOnDateRangeInStudentServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        String role = "student";
        String firstName = "Ellie";
        String lastName = "Tucci";
        LocalDate beginDate = LocalDate.of(2021, 03, 01);
        LocalDate endDate = LocalDate.of(2021, 03, 31);

        scheduleControllerMock.showSchedule(role, firstName, lastName, beginDate, endDate, model);

        Mockito.verify(studentServiceMock).receiveLessonsOnDateRange(firstName, lastName, beginDate, endDate);
    }

    @Test
    public void should_callReceiveLessonsOnDateRangeInTeacherServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();
        String role = "teacher";
        String firstName = "Freya";
        String lastName = "Shepp";
        LocalDate beginDate = LocalDate.of(2021, 03, 01);
        LocalDate endDate = LocalDate.of(2021, 03, 31);

        scheduleControllerMock.showSchedule(role, firstName, lastName, beginDate, endDate, model);

        Mockito.verify(teacherServiceMock).receiveLessonsOnDateRange(firstName, lastName, beginDate, endDate);
    }

}
