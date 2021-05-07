package ua.com.foxminded.classtimetable.repository.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "faculties")
public class Faculty extends CommonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = getId();

    @Column(name = "faculty_name")
    private String facultyName;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && Objects.equals(facultyName, faculty.facultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyName);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", facultyName='" + facultyName + '\'' +
                '}';
    }

}
