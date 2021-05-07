package ua.com.foxminded.classtimetable.repository.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "classrooms")
public class Classroom extends CommonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = getId();

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_capacity")
    private int roomCapacity;

    @ManyToOne
    private Building building;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return id == classroom.id && roomCapacity == classroom.roomCapacity && Objects.equals(roomName, classroom.roomName) && Objects.equals(roomType, classroom.roomType) && Objects.equals(building, classroom.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName, roomType, roomCapacity, building);
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomCapacity=" + roomCapacity +
                ", building=" + building +
                '}';
    }

}
