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
import ua.com.foxminded.classtimetable.domain.dto.ClassroomDto;
import ua.com.foxminded.classtimetable.service.BuildingService;
import ua.com.foxminded.classtimetable.service.ClassroomService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClassroomControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private BuildingService buildingService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void should_getListOfClassrooms_when_controllerCallsShowAllMethod() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/classrooms"))
                .andExpect(status().isOk())
                .andExpect(view().name("classrooms/showAll"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("classrooms", classroomService.getAllAsDto()))
                .andExpect(model().attribute("buildings", buildingService))
                .andReturn();
    }

    @Test
    public void should_getOneClassroom_when_controllerCallsShowByIdMethod() throws Exception {

        int id = 5;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/classrooms/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("classrooms/showById"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("classroom", classroomService.getByIdAsDto(id)))
                .andExpect(model().attribute("buildings", buildingService.getAll()))
                .andExpect(model().attribute("classroomTypes", getClassroomTypes()))
                .andReturn();
    }

    @Test
    public void should_showCreationView_when_controllerCallsCreateMethod() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/classrooms/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("classrooms/create"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void should_addNewClassroomToDatabase_when_controllerCallsAddToDBMethod() throws Exception {

        ClassroomDto classroom = new ClassroomDto();
        classroom.setRoomName("A-6");
        classroom.setRoomType("Lecture");
        classroom.setRoomCapacity(100);
        classroom.setBuildingId(1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/classrooms")
                        .param("roomName", classroom.getRoomName())
                        .param("roomType", classroom.getRoomType())
                        .param("roomCapacity", Integer.toString(classroom.getRoomCapacity()))
                        .param("buildingId", Integer.toString(classroom.getBuildingId())))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/classrooms"))
                .andExpect(redirectedUrl("/classrooms"))
                .andReturn();
    }

    @Test
    public void should_updateClassroomInDatabase_when_controllerCallsUpdateMethod() throws Exception {

        ClassroomDto classroom = new ClassroomDto();
        classroom.setId(3);
        classroom.setRoomName("B-10");
        classroom.setRoomType("Laboratory");
        classroom.setRoomCapacity(10);
        classroom.setBuildingId(2);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/classrooms/{id}", classroom.getId())
                        .param("roomName", classroom.getRoomName())
                        .param("roomType", classroom.getRoomType())
                        .param("roomCapacity", Integer.toString(classroom.getRoomCapacity()))
                        .param("buildingId", Integer.toString(classroom.getBuildingId())))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/classrooms"))
                .andExpect(redirectedUrl("/classrooms"))
                .andReturn();
    }

    private List<String> getClassroomTypes() {
        List<String> typesWithDuplicates = new ArrayList<>();
        classroomService.getAll().forEach(room -> typesWithDuplicates.add(room.getRoomType()));
        return typesWithDuplicates.stream().distinct().collect(Collectors.toList());
    }
}
