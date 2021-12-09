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
    public void should_returnIsOkStatus_when_controllerCallsGetSchedule() throws Exception {

        this.mockMvc.perform(get("/rest/v1/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnBadRequestStatus_when_controllerCallsGetScheduleWithIncompleteData() throws Exception {
    }
}
