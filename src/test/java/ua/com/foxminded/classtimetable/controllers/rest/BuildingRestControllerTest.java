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
import ua.com.foxminded.classtimetable.repository.entities.Building;
import ua.com.foxminded.classtimetable.service.BuildingService;
import ua.com.foxminded.classtimetable.validators.BuildingValidator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BuildingRestController.class)
public class BuildingRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BuildingService serviceBuildingMock;

    @MockBean
    private BuildingValidator validatorBuildingMock;

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowAllMethod() throws Exception {

        this.mockMvc.perform(get("/rest/v1/buildings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsShowByIdMethod() throws Exception {

        String id = "1";

        this.mockMvc.perform(get("/rest/v1/buildings/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnCreatedStatus_when_controllerCallsCreateMethod() throws Exception {

        Building building = new Building();
        building.setId(5);
        building.setBuildingName("name");

        this.mockMvc.perform(post("/rest/v1/buildings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(building)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsUpdateMethod() throws Exception {

        Building building = new Building();
        building.setId(1);
        building.setBuildingName("changedName");

        this.mockMvc.perform(put("/rest/v1/buildings/" + building.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(building)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnIsOkStatus_when_controllerCallsDeleteMethod() throws Exception {

        Building building = new Building();
        building.setId(4);
        building.setBuildingName("D");
        serviceBuildingMock.create(building);

        this.mockMvc.perform(delete("/rest/v1/buildings/" + building.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnInternalServerErrorStatus_when_controllerCallsDeleteMethodWithInvalidData()
            throws Exception {

        int buildingId = 1;

        this.mockMvc.perform(delete("/rest/v1/buildings/" + buildingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
