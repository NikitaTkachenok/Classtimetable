package ua.com.foxminded.classtimetable.controllers.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleRestController.class)
public class ScheduleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService serviceStudent;

    @MockBean
    TeacherService serviceTeacher;

    @Test
    public void should_returnIsOkStatus_when_controllerCallsGetScheduleWithTeacherEntity() throws Exception {

        Object person = "TeacherDto{id=1, firstName='Albert', lastName='Sykes', facultyId=3, coursesId=[1]}";
        LocalDate beginDate = LocalDate.of(2021, 3, 1);
        LocalDate endDate = LocalDate.of(2021, 3, 30);

        this.mockMvc.perform(get("/rest/v1/schedule/show")
                        .param("person", person.toString())
                        .param("beginDate", beginDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsGetScheduleMethodWithStudentEntity() throws Exception {

        Object person = "StudentDto{id=1, firstName='Jacob', lastName='Rose', facultyId=1, coursesId=[6]}";
        LocalDate beginDate = LocalDate.of(2021, 4, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 30);

        this.mockMvc.perform(get("/rest/v1/schedule/show")
                        .param("person", person.toString())
                        .param("beginDate", beginDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
