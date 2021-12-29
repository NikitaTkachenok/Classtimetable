package ua.com.foxminded.classtimetable.controllers.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.classtimetable.repository.entities.Building;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingRestControllerIntegrationTest {

    @Autowired
    private BuildingRestController buildingRestController;

    @Test
    public void should_createNewBuilding_when_controllerCallsAddToDBMethod() {

        Building building = new Building();
        building.setId(4);
        building.setBuildingName("The fourth building");

        assertThat(buildingRestController.showAll(), not(hasItem(building)));

        buildingRestController.addToDB(building);

        assertThat(buildingRestController.showAll(), hasItem(building));
        assertEquals(buildingRestController.showById(building.getId()), building);

    }

    @Test
    public void should_updateBuilding_when_controllerCallsUpdateMethod() {

        Building building = buildingRestController.showById(1);
        building.setBuildingName("Changed name");

        buildingRestController.update(building);

        assertEquals(buildingRestController.showById(1), building);

    }

    @Test
    public void should_deleteBuilding_when_controllerCallsDeleteMethod() {

        HttpServletRequest request = new MockHttpServletRequest();
        Building building = new Building();
        building.setId(4);
        building.setBuildingName("The fourth building");

        buildingRestController.addToDB(building);
        assertThat(buildingRestController.showAll(), hasItem(building));

        buildingRestController.delete(4, request);
        assertThat(buildingRestController.showAll(), not(hasItem(building)));
    }

}
