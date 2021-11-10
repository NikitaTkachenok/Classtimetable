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
import ua.com.foxminded.classtimetable.repository.entities.Building;
import ua.com.foxminded.classtimetable.service.BuildingService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BuildingControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BuildingService buildingService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void should_getListOfBuildings_when_controllerCallsShowAllMethod() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/buildings"))
                .andExpect(status().isOk())
                .andExpect(view().name("buildings/showAll"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("buildings", buildingService.getAll()))
                .andReturn();
    }

    @Test
    public void should_getOneBuilding_when_controllerCallsShowByIdMethod() throws Exception {

        int id = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/buildings/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("buildings/showById"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("building", buildingService.getById(id)))
                .andReturn();
    }

    @Test
    public void should_showCreationView_when_controllerCallsCreateMethod() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/buildings/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("buildings/create"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void should_addNewBuildingToDatabase_when_controllerCallsAddToDBMethod() throws Exception {

        Building building = new Building();
        building.setBuildingName("D");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/buildings")
                        .param("buildingName", building.getBuildingName()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/buildings"))
                .andExpect(redirectedUrl("/buildings"))
                .andReturn();
    }

    @Test
    public void should_updateBuildingInDatabase_when_controllerCallsUpdateMethod() throws Exception {

        Building building = new Building();
        building.setId(2);
        building.setBuildingName("E");

        this.mockMvc.perform(MockMvcRequestBuilders.put("/buildings/{id}", building.getId())
                        .param("id", Integer.toString(building.getId()))
                        .param("buildingName", building.getBuildingName()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/buildings"))
                .andExpect(redirectedUrl("/buildings"))
                .andReturn();
    }

//    @Test
//    public void should_deleteBuildingFromDatabase_when_controllerCallsDeleteMethod() throws Exception {
//
//        Building building = new Building();
//        building.setId(2);
//        building.setBuildingName("E");
//
//        this.mockMvc.perform(MockMvcRequestBuilders.delete("/buildings/{id}", building.getId())
//                        .param("id", Integer.toString(building.getId()))
//                        .param("buildingName", building.getBuildingName()))
//                .andExpect(status().isFound())
//                .andExpect(view().name("redirect:/buildings"))
//                .andExpect(redirectedUrl("/buildings"))
//                .andReturn();
//    }
}
