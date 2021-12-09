package ua.com.foxminded.classtimetable.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.foxminded.classtimetable.domain.dto.TeacherDto;
import ua.com.foxminded.classtimetable.service.TeacherService;
import ua.com.foxminded.classtimetable.validators.TeacherValidator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherRestController.class)
public class TeacherRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TeacherService serviceTeacher;

    @MockBean
    TeacherValidator validatorTeacher;

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowAllMethod() throws Exception {

        this.mockMvc.perform(get("/rest/v1/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowByIdMethod() throws Exception {

        String id = "3";

        this.mockMvc.perform(get("/rest/v1/teachers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnCreatedStatus_when_controllerCallsCreateMethod() throws Exception {

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(12);
        teacherDto.setFacultyId(1);
        teacherDto.setFirstName("First");
        teacherDto.setLastName("Last");

        this.mockMvc.perform(post("/rest/v1/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(teacherDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsUpdateMethod() throws Exception {

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(12);
        teacherDto.setFacultyId(3);
        teacherDto.setFirstName("First");
        teacherDto.setLastName("Last");

        this.mockMvc.perform(put("/rest/v1/teachers/" + teacherDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(teacherDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsDeleteMethod() throws Exception {

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(15);
        teacherDto.setFacultyId(2);
        teacherDto.setFirstName("firstName");
        teacherDto.setLastName("lastName");

        this.mockMvc.perform(delete("/rest/v1/teachers/" + teacherDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(teacherDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnBadRequestStatus_when_controllerCallsDeleteMethodWithInvalidData() throws Exception {

        TeacherDto teacherDto = serviceTeacher.getByIdAsDto(2);

        this.mockMvc.perform(delete("/rest/v1/teachers/" + teacherDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
