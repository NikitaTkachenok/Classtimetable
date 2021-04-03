package ua.com.foxminded.classtimetable.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.classtimetable.repository.dao.BuildingDao;
import ua.com.foxminded.classtimetable.repository.entities.Building;

@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTest {

    @InjectMocks
    private BuildingService buildingServiceMock;

    @Mock
    private BuildingDao buildingDaoMock;

    @Test
    public void should_callGetAllMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        buildingServiceMock.getAll();

        Mockito.verify(buildingDaoMock).getAll();
    }

    @Test
    public void should_callGetByIdMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        int id = 1;

        buildingServiceMock.getById(id);

        Mockito.verify(buildingDaoMock).getById(id);
    }

    @Test
    public void should_callCreateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Building building = new Building();
        building.setBuildingName("D");

        buildingServiceMock.create(building);

        Mockito.verify(buildingDaoMock).create(building);
    }

    @Test
    public void should_callUpdateMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Building building = new Building();
        building.setBuildingName("S");

        buildingServiceMock.update(building);

        Mockito.verify(buildingDaoMock).update(building);
    }

    @Test
    public void should_callDeleteMethodInDaoClass_when_serviceClassCallsAppropriateMethod() {

        Building building = new Building();
        building.setBuildingName("P");

        buildingServiceMock.create(building);
        buildingServiceMock.delete(building);

        Mockito.verify(buildingDaoMock).delete(building);
    }

}
