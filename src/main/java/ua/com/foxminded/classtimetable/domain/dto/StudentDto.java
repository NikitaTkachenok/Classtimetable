package ua.com.foxminded.classtimetable.domain.dto;

import java.util.Objects;
import java.util.Set;

public class StudentDto {

    private int id;

    private String firstName;

    private String lastName;

    private int facultyId;

    private Set<Integer> coursesId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public Set<Integer> getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Set<Integer> coursesId) {
        this.coursesId = coursesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return id == that.id && facultyId == that.facultyId && Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) && Objects.equals(coursesId, that.coursesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, facultyId, coursesId);
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", facultyId=" + facultyId +
                ", coursesId=" + coursesId +
                '}';
    }

}
