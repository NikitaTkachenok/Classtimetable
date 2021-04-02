package ua.com.foxminded.classtimetable.domain.dto;

import java.util.Objects;

public class ClassroomDto {

    private int id;

    private String roomName;

    private String roomType;

    private int roomCapacity;

    private int buildingId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomDto that = (ClassroomDto) o;
        return id == that.id && roomCapacity == that.roomCapacity && buildingId == that.buildingId && Objects.equals(roomName, that.roomName) && Objects.equals(roomType, that.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName, roomType, roomCapacity, buildingId);
    }

    @Override
    public String toString() {
        return "ClassroomDto{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomCapacity=" + roomCapacity +
                ", buildingId=" + buildingId +
                '}';
    }

}
