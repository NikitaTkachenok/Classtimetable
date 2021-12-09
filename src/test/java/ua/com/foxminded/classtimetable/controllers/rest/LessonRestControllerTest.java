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
import ua.com.foxminded.classtimetable.domain.dto.LessonDto;
import ua.com.foxminded.classtimetable.service.LessonService;
import ua.com.foxminded.classtimetable.validators.LessonValidator;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LessonRestController.class)
public class LessonRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LessonService serviceLessonMock;

    @MockBean
    LessonValidator validatorLessonMock;

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowAllMethod() throws Exception {

        this.mockMvc.perform(get("/rest/v1/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowByIdMethod() throws Exception {

        String id = "5";

        this.mockMvc.perform(get("/rest/v1/lessons/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    //For Ievgenii
    //Seems like the test falls because mapToJson method cannot transform date and time. I'll fix it later.
    @Test
    public void should_returnCreatedStatus_when_controllerCallsCreateMethod() throws Exception {

        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(5);
        lessonDto.setDate(LocalDate.of(2021, 12, 7));
        lessonDto.setStartTime(LocalTime.of(9, 20));
        lessonDto.setEndTime(LocalTime.of(10, 0));
        lessonDto.setClassroomId(1);
        lessonDto.setCourseId(1);
        lessonDto.setTeacherId(1);

        this.mockMvc.perform(post("/rest/v1/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(lessonDto)))
                .andExpect(status().isCreated());
    }


    @Test
    public void should_returnBadRequestStatus_when_controllerCallsCreateMethodWithInvalidTeacher()
            throws Exception {

        LessonDto lessonDto = new LessonDto();
        lessonDto.setDate(LocalDate.of(2021, 2, 1));
        lessonDto.setStartTime(LocalTime.of(9, 20));
        lessonDto.setEndTime(LocalTime.of(10, 0));
        lessonDto.setClassroomId(1);
        lessonDto.setCourseId(1);
        lessonDto.setTeacherId(1);

        this.mockMvc.perform(post("/rest/v1/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(lessonDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_returnBadRequestStatus_when_controllerCallsCreateMethodWithInvalidTemporalConditions()
            throws Exception {

        LessonDto lessonDto = new LessonDto();
        lessonDto.setDate(LocalDate.of(2021, 2, 1));
        lessonDto.setStartTime(LocalTime.of(9, 20));
        lessonDto.setEndTime(LocalTime.of(10, 0));
        lessonDto.setClassroomId(1);
        lessonDto.setCourseId(1);
        lessonDto.setTeacherId(1);

        this.mockMvc.perform(post("/rest/v1/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(lessonDto)))
                .andExpect(status().isBadRequest());
    }

    //For Ievgenii
    //Seems like the test falls because mapToJson method cannot transform date and time. I'll fix it later.
    @Test
    public void should_returnIsOkStatus_when_controllerCallsUpdateMethod() throws Exception {

        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(5);
        lessonDto.setDate(LocalDate.of(2021, 12, 7));
        lessonDto.setStartTime(LocalTime.of(11, 10));
        lessonDto.setEndTime(LocalTime.of(12, 45));
        lessonDto.setClassroomId(5);
        lessonDto.setCourseId(1);
        lessonDto.setTeacherId(1);

        this.mockMvc.perform(put("/rest/v1/lessons/" + lessonDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(lessonDto)))
                .andExpect(status().isOk());
    }

    //For Ievgenii
    //The test has the same problem with "defective" mock like other tests.
    @Test
    public void should_returnIsOkStatus_when_controllerCallsDeleteMethod() throws Exception {

        LessonDto lessonDto = serviceLessonMock.getByIdAsDto(15);

        this.mockMvc.perform(delete("/rest/v1/lessons/" + lessonDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}