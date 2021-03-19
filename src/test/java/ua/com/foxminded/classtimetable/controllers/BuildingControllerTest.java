package ua.com.foxminded.classtimetable.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import ua.com.foxminded.classtimetable.entities.Building;
import ua.com.foxminded.classtimetable.service.BuildingService;

@RunWith(MockitoJUnitRunner.class)
public class BuildingControllerTest {

    @InjectMocks
    private BuildingController buildingControllerMock;

    @Mock
    private BuildingService buildingServiceMock;

    @Test
    public void should_callGetAllMethodInServiceClass_when_controllerClassCallsAppropriateMethod() {

        ModelMap model = new ModelMap();

        buildingControllerMock.showAll(model);

        Mockito.verify(buildingServiceMock).getAll();
    }

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
