package ua.com.foxminded.classtimetable.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.foxminded.classtimetable.domain.dto.StudentDto;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.service.StudentService;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private CourseService courseService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void should_getListOfStudents_when_controllerCallsShowAllMethod() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/showAll"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("students", studentService.getAllAsDto()))
                .andExpect(model().attribute("faculties", facultyService))
                .andExpect(model().attribute("courses", courseService))
                .andReturn();
    }

    @Test
    public void should_getOneStudent_when_controllerCallsShowByIdMethod() throws Exception {

        int id = 26;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/students/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("students/showById"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("student", studentService.getByIdAsDto(id)))
                .andExpect(model().attribute("faculties", facultyService.getAll()))
                .andExpect(model().attribute("allCourses", courseService.getAll()))
                .andReturn();
    }

    @Test
    public void should_showCreationView_when_controllerCallsCreateMethod() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/students/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/create"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("faculties", facultyService.getAll()))
                .andExpect(model().attribute("allCourses", courseService.getAll()))
                .andReturn();
    }

    @Test
    public void should_addNewStudentToDatabase_when_controllerCallsAddToDBMethod() throws Exception {

        Set<Integer> studentCourses = new HashSet<>();
        studentCourses.add(3);
        studentCourses.add(7);

        StudentDto student = new StudentDto();
        student.setFirstName("Jeffrey");
        student.setLastName("Coffee");
        student.setFacultyId(1);
        student.setCoursesId(studentCourses);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .param("firstName", student.getFirstName())
                        .param("lastName", student.getLastName())
                        .param("facultyId", Integer.toString(student.getFacultyId()))
                        .param("coursesId", createStringOfValues(studentCourses)))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/students"))
                .andExpect(redirectedUrl("/students"))
                .andReturn();
    }

    @Test
    public void should_updateStudentInDatabase_when_controllerCallsUpdateMethod() throws Exception {

        Set<Integer> studentCourses = new HashSet<>();
        studentCourses.add(2);
        studentCourses.add(5);
        studentCourses.add(8);

        StudentDto student = new StudentDto();
        student.setId(16);
        student.setFirstName("Fran");
        student.setLastName("Bow");
        student.setFacultyId(2);
        student.setCoursesId(studentCourses);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/students/{id}", student.getId())
                        .param("id", Integer.toString(student.getId()))
                        .param("firstName", student.getFirstName())
                        .param("lastName", student.getLastName())
                        .param("facultyId", Integer.toString(student.getFacultyId()))
                        .param("coursesId", createStringOfValues(studentCourses)))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/students"))
                .andExpect(redirectedUrl("/students"))
                .andReturn();
    }

    private String createStringOfValues(Set<Integer> setOfValues) {
        StringBuilder stringBuilder = new StringBuilder();
        setOfValues.forEach(integer -> stringBuilder.append(integer).append(","));
        return stringBuilder.substring(0, stringBuilder.toString().length() - 1);
    }

}
