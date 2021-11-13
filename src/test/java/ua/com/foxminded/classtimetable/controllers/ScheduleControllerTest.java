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
import ua.com.foxminded.classtimetable.service.ClassroomService;
import ua.com.foxminded.classtimetable.service.CourseService;
import ua.com.foxminded.classtimetable.service.StudentService;
import ua.com.foxminded.classtimetable.service.TeacherService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ScheduleControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private CourseService courseService;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void should_showInputDataPage_when_controllerCallsShowFormMethod() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/schedule"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/fillData"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("teachers", teacherService.getAllAsDto()))
                .andExpect(model().attribute("students", studentService.getAllAsDto()))
                .andReturn();
    }

    @Test
    public void should_giveLessonsForTeacherOnDateRange_when_controllerCallsShowScheduleMethod() throws Exception {

        String role = "teacher";
        int id = 7;
        LocalDate beginDate = LocalDate.of(2021, 03, 01);
        LocalDate endDate = LocalDate.of(2021, 03, 31);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/schedule/show", id)
                        .param("role", role)
                        .param("id", Integer.toString(id))
                        .param("beginDate", convertDateToString(beginDate))
                        .param("endDate", convertDateToString(endDate)))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/show"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("classrooms", classroomService))
                .andExpect(model().attribute("courses", courseService))
                .andExpect(model().attribute("teachers", teacherService))
                .andReturn();
    }

    @Test
    public void should_giveLessonsForStudentOnDateRange_when_controllerCallsShowScheduleMethod() throws Exception {

        String role = "student";
        int id = 13;
        LocalDate beginDate = LocalDate.of(2021, 03, 01);
        LocalDate endDate = LocalDate.of(2021, 03, 31);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/schedule/show", id)
                        .param("role", role)
                        .param("id", Integer.toString(id))
                        .param("beginDate", convertDateToString(beginDate))
                        .param("endDate", convertDateToString(endDate)))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/show"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("classrooms", classroomService))
                .andExpect(model().attribute("courses", courseService))
                .andExpect(model().attribute("teachers", teacherService))
                .andReturn();
    }

    private String convertDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
