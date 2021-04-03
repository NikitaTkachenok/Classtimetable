package ua.com.foxminded.classtimetable.domain.converters;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.repository.dao.CourseDao;
import ua.com.foxminded.classtimetable.repository.dao.FacultyDao;
import ua.com.foxminded.classtimetable.repository.entities.Course;
import ua.com.foxminded.classtimetable.repository.entities.Faculty;
import ua.com.foxminded.classtimetable.repository.entities.Teacher;

import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class TeacherConverterTest {


    @InjectMocks
    private TeacherConverter teacherConverterMock;

    @Mock
    private FacultyDao facultyDaoMock;

    @Mock
    private CourseDao courseDaoMock;

    @Test
    public void should_returnDto_when_argumentOfMethodIsEntity() {

        Set<Course> courses = new HashSet<>();
        Course course1 = new Course();
        course1.setId(1);
        courses.add(course1);
        Course course2 = new Course();
        course2.setId(2);
        courses.add(course2);
        Faculty faculty = new Faculty();
        faculty.setId(1);
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setFirstName("firstName");
        teacher.setLastName("lastName");
        teacher.setFaculty(faculty);
        teacher.setTeacherCourses(courses);
        Set<Integer> coursesId = new HashSet<>();
        coursesId.add(1);
        coursesId.add(2);
        TeacherDto dto = new TeacherDto();
        dto.setId(1);
        dto.setFirstName("firstName");
        dto.setLastName("lastName");
        dto.setFacultyId(1);
        dto.setCoursesId(coursesId);

        Assert.assertEquals(dto, teacherConverterMock.toDto(teacher));
    }

    @Test
    public void should_returnEntity_when_argumentOfMethodIsDto() {

        Set<Integer> coursesId = new HashSet<>();
        coursesId.add(3);
        coursesId.add(4);
        TeacherDto dto = new TeacherDto();
        dto.setId(2);
        dto.setFirstName("firstName");
        dto.setLastName("lastName");
        dto.setFacultyId(2);
        dto.setCoursesId(coursesId);
        Set<Course> courses = new HashSet<>();
        courses.add(courseDaoMock.getById(3));
        courses.add(courseDaoMock.getById(4));
        Teacher teacher = new Teacher();
        teacher.setId(2);
        teacher.setFirstName("firstName");
        teacher.setLastName("lastName");
        teacher.setFaculty(facultyDaoMock.getById(2));
        teacher.setTeacherCourses(courses);

        Assert.assertEquals(teacher, teacherConverterMock.toEntity(dto));
    }

    @Test
    public void should_callToEntityMethodInDaoClass_when_converterClassCallAppropriateMethod() {

        Set<Integer> coursesId = new HashSet<>();
        coursesId.add(1);
        coursesId.add(4);
        TeacherDto dto = new TeacherDto();
        dto.setId(3);
        dto.setFirstName("firstName");
        dto.setLastName("lastName");
        dto.setFacultyId(3);
        dto.setCoursesId(coursesId);

        teacherConverterMock.toEntity(dto);

        Mockito.verify(facultyDaoMock).getById(dto.getFacultyId());
        Mockito.verify(courseDaoMock).getById(1);
        Mockito.verify(courseDaoMock).getById(4);
    }

}
