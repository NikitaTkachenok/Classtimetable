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
import ua.com.foxminded.classtimetable.repository.entities.Faculty;
import ua.com.foxminded.classtimetable.service.FacultyService;
import ua.com.foxminded.classtimetable.validators.FacultyValidator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FacultyRestController.class)
public class FacultyRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FacultyService serviceFacultyMock;

    @MockBean
    FacultyValidator validatorFacultyMock;

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowAllMethod() throws Exception {

        this.mockMvc.perform(get("/rest/v1/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowByIdMethod() throws Exception {

        String id = "1";

        this.mockMvc.perform(get("/rest/v1/faculties/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnCreatedStatus_when_controllerCallsCreateMethod() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(5);
        faculty.setFacultyName("Faculty name");

        this.mockMvc.perform(post("/rest/v1/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(faculty)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsUpdateMethod() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(5);
        faculty.setFacultyName("Changed faculty name");

        this.mockMvc.perform(put("/rest/v1/faculties/" + faculty.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(faculty)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsDeleteMethod() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(5);
        faculty.setFacultyName("Changed faculty name");
        serviceFacultyMock.create(faculty);

        this.mockMvc.perform(delete("/rest/v1/faculties/" + faculty.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnInternalServerErrorStatus_when_controllerCallsDeleteMethodWithInvalidData()
            throws Exception {

        int facultyId = 1;

        this.mockMvc.perform(delete("/rest/v1/faculties/" + facultyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
