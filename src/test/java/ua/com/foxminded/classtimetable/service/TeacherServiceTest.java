package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.domain.converters.LessonConverter;
import ua.com.foxminded.classtimetable.domain.converters.TeacherConverter;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.repository.dao.LessonDao;
import ua.com.foxminded.classtimetable.repository.dao.TeacherDao;
import ua.com.foxminded.classtimetable.repository.entities.Faculty;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherServiceMock;

    @Mock
    private LessonDao lessonDaoMock;

    @Mock
    private TeacherDao teacherDaoMock;

    @Mock
    private TeacherConverter teacherConverterMock;

    @Mock
    private LessonConverter lessonConverterMock;

    @Test
    public void should_callReceiveLessonsOnDateRangeMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        int id = 3;
        LocalDate beginDate = LocalDate.of(2021, 03, 01);
        LocalDate endDate = LocalDate.of(2021, 03, 31);

        teacherServiceMock.receiveLessonsOnDateRange(id, beginDate, endDate)
                .stream()
                .map(lessonConverterMock :: toEntity)
                .collect(Collectors.toList());

        Mockito.verify(lessonDaoMock).getLessonsForTeacherOnDateRange(id, beginDate, endDate);
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

        TeacherDto teacher = new TeacherDto();
        Faculty faculty = new Faculty();
        teacher.setFirstName("John");
        teacher.setLastName("Galt");
        teacher.setFacultyId(1);

        teacherServiceMock.create(teacher);

        Mockito.verify(teacherDaoMock).create(teacherConverterMock.toEntity(teacher));
    }

    @Test
    public void should_callUpdateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        TeacherDto teacher = new TeacherDto();
        Faculty faculty = new Faculty();
        teacher.setFirstName("Joachim");
        teacher.setLastName("Belt");
        teacher.setFacultyId(2);

        teacherServiceMock.update(teacher);

        Mockito.verify(teacherDaoMock).update(teacherConverterMock.toEntity(teacher));
    }

    @Test
    public void should_callDeleteByIdMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        TeacherDto teacher = new TeacherDto();
        Faculty faculty = new Faculty();
        teacher.setFirstName("Pet");
        teacher.setLastName("Peters");
        teacher.setFacultyId(3);

        teacherServiceMock.create(teacher);
        teacherServiceMock.deleteById(teacher.getId());

        Mockito.verify(teacherDaoMock).deleteById(teacher.getId());
    }

}
