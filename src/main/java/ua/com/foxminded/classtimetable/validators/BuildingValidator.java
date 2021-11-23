package ua.com.foxminded.classtimetable.validators;

import org.springframework.stereotype.Component;
import ua.com.foxminded.classtimetable.controllers.exceptions.IndelibleEntityException;
import ua.com.foxminded.classtimetable.repository.entities.Building;
import ua.com.foxminded.classtimetable.repository.entities.Classroom;
import ua.com.foxminded.classtimetable.service.ClassroomService;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Component
public class BuildingValidator {

    private final ClassroomService serviceClassroom;

    public BuildingValidator(ClassroomService serviceClassroom) {
        this.serviceClassroom = serviceClassroom;
    }

    public void checkForDeletion(Building building, HttpServletRequest request) {
        boolean undeletable = serviceClassroom.getAll()
                .stream()
                .map(Classroom::getBuilding)
                .map(Building::getId)
                .collect(Collectors.toList())
                .contains(building.getId());
        if (undeletable) {
            throw new IndelibleEntityException
                    ("The building cannot be deleted because it still contains classrooms that weren't deleted",
                            request.getHeader("Referer"));
        }
    }
}
