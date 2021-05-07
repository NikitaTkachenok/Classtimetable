package ua.com.foxminded.classtimetable.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.Application;
import ua.com.foxminded.classtimetable.repository.entities.Building;
import ua.com.foxminded.classtimetable.service.BuildingService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
public class BuildingControllerTest {

    @InjectMocks
    private BuildingController buildingControllerMock;

    @Mock
    private BuildingService buildingServiceMock;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MenuController()).build();
    }

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        buildingControllerMock.showAll(model);

        Mockito.verify(buildingServiceMock).getAll();
    }

//    @Test
//    public void should_getListOfBuildings_when_controllerClassCallsGetAllMethod() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders
//                .get("/buildings"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("create"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//                .andExpect(MockMvcResultMatchers.jsonPath("$.buildings").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.buildings[*].id").isNotEmpty());
//    }

    @Test
    public void should_callShowByIdMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        int id = 1;
        ModelMap model = new ModelMap();

        buildingControllerMock.showById(id, model);

        Mockito.verify(buildingServiceMock).getById(id);
    }

    @Test
    public void should_callAddToDBMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Building building = new Building();
        building.setBuildingName("D");

        buildingControllerMock.addToDB(building);

        Mockito.verify(buildingServiceMock).create(building);
    }

    @Test
    public void should_updateMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Building building = new Building();
        building.setBuildingName("P");

        buildingControllerMock.update(building);

        Mockito.verify(buildingServiceMock).update(building);

    }

    @Test
    public void should_deleteMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        Building building = new Building();
        building.setBuildingName("L");

        buildingControllerMock.create(building);
        buildingControllerMock.delete(building);

        Mockito.verify(buildingServiceMock).delete(building);

    }
}
